/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(BogotÃ¡	- Colombia)
 * Departamento	de	IngenierÃ­a	de	Sistemas	y	ComputaciÃ³n
 * Licenciado	bajo	el	esquema	Academic Free License versiÃ³n 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author GermÃ¡n Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia JimÃ©nez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Oferta;
import uniandes.isis2304.parranderos.negocio.OfertaApartamento;
import uniandes.isis2304.parranderos.negocio.OfertaEsporadica;
import uniandes.isis2304.parranderos.negocio.OfertaHabitacionDiaria;
import uniandes.isis2304.parranderos.negocio.OfertaHabitacionMensual;
import uniandes.isis2304.parranderos.negocio.OfertaViviendaUniversitaria;
import uniandes.isis2304.parranderos.negocio.PersonaJuridica;
import uniandes.isis2304.parranderos.negocio.PersonaNatural;
import uniandes.isis2304.parranderos.negocio.Reserva;




/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la informaciÃ³n entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrÃ³n SINGLETON (SÃ³lo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author GermÃ¡n Bravo
 */
public class PersistenciaAlohAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuciÃ³n
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el Ãºnico objeto de la clase - PatrÃ³n SINGLETON
	 */
	private static PersistenciaAlohAndes instance;
	
	/**
	 * FÃ¡brica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	
	private SQLCliente sqlCliente;
	
	private SQLPersonaJuridica sqlPJ;
	
	private SQLPersonaNatural sqlPN;
	
	private SQLOferta sqlO;
	
	private SQLReserva sqlReserva;
	
	private SQLOfertaApartamento sqlOA;
	
	private SQLOfertaHabitacionMensual sqlOHM;
	
	private SQLOfertaHabitacionDiaria sqlOHD;
	
	private SQLOfertaViviendaUniversitaria sqlOVU;
	
	private SQLOfertaEsporadica sqlOE;
	
	/* ****************************************************************
	 * 			MÃ©todos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - PatrÃ³n SINGLETON
	 */
	private PersistenciaAlohAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("AlohAndes_sequence");
		tablas.add ("CLIENTES");
		tablas.add("PERSONASJURIDICAS");
		tablas.add("PERSONASNATURALES");
		tablas.add("OFERTAS");
		tablas.add("RESERVAS");

}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - PatrÃ³n SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el Ãºnico objeto PersistenciaParranderos existente - PatrÃ³n SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el Ãºnico objeto PersistenciaParranderos existente - PatrÃ³n SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexiÃ³n con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{

		
		sqlCliente = new SQLCliente(this);
		sqlPJ = new SQLPersonaJuridica(this);
		
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqAlohAndes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaClientes ()
	{
		return tablas.get (1);
	}
	
	public String darTablaPersonasJuridicas ()
	{
		return tablas.get (2);
	}
	
	public String darTablaPersonasNaturales ()
	{
		return tablas.get (3);
	}
	
	public String darTablaOfertas()
	{
		return tablas.get (4);
	}
	
	public String darTablaReservas()
	{
		return tablas.get (5);
	}
	
	public String darTablaOfertaApartamento()
	{
		return tablas.get (5);
	}
	
	public String darTablaOfertaHabitacionMensual()
	{
		return tablas.get (5);
	}

	public String darTablaOfertaHabitacionDiaria()
	{
		return tablas.get (5);
	}
	
	public String darTablaOfertaEsporadica()
	{
		return tablas.get (5);
	}
	
	public String darTablaOfertaViviendaUniversitaria()
	{
		return tablas.get (5);
	}

	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle especÃ­fico del problema encontrado
	 * @param e - La excepciÃ³n que ocurrio
	 * @return El mensaje de la excepciÃ³n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			MÃ©todos para manejar los Clientes
	 *****************************************************************/

	public Cliente adicionarCliente(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, numeroDocumento, tipoDocumento, nombre, nacionalidad,
            		tipo, userName, contrasena);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de cliente: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public PersonaJuridica adicionarPersonaJuridica(Long nit, String nombre, String tipo, String horaApertura,
			String horaCierre, String userName, String contrasena) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPJ.adicionarPersonaJuridica(pm, nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de persona juridica: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PersonaJuridica(nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public PersonaNatural adicionarPersonaNatural(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlPN.adicionarPersonaNatural(pm, numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);

            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de persona juridica: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PersonaNatural(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Oferta adicionarOferta(Long id, String tipo_oferta, Boolean disponible, Integer precio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlO.adicionarOferta(pm, id, tipo_oferta, disponible,precio);
            tx.commit();

            log.trace ("InserciÃ³n de oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Oferta(id, tipo_oferta, disponible,precio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Reserva adicionarReservas(Long numeroReserva, String fechaInicio, String fechaFin, Long idOferta, 
			Long docCliente, String tipoDoc, String fechaCandelacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc, fechaCandelacion);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de reserva: " + numeroReserva + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Reserva(numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc, fechaCandelacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaPorNumero (Long numReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReserva(pm, numReserva);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public OfertaApartamento adicionarOfertaApartamento(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad, String descripcion,
			Boolean esAmoblado, String ubicacion, Long documentoOp,String tipoDocOp, Long contrato) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOA.adicionarOfertaApartamento(pm, id, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de oferta apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OfertaApartamento(id, tipo, disponible, precio, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public OfertaHabitacionMensual adicionarOfertaHabitacionMensual(Long id, Integer capacidad, String descripcion,
			String ubicacion, Long docOperador, String tipoDocOperador, Integer precio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOHM.adicionarHabitacionMensual(pm, id, capacidad, descripcion, ubicacion, docOperador, tipoDocOperador, precio);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de oferta apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OfertaHabitacionMensual(id, capacidad, descripcion, ubicacion, docOperador, tipoDocOperador, precio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public OfertaViviendaUniversitaria adicionarOfertaViviendaUniversitaria(Long id,Integer capacidad, String duracion, 
			Boolean esCompartida, Integer precio, Long id_operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOVU.adicionarOfertaViviendaUniversitaria(pm, id, capacidad,duracion,esCompartida, precio, id_operador);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de cliente: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OfertaViviendaUniversitaria(id, capacidad, duracion, esCompartida, precio, id_operador);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public OfertaHabitacionDiaria adicionarOfertaHabitacionDiaria(Long id,Boolean esCompartida, Integer precio, 
			String ubicacion, Long id_operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOHD.adicionarOfertaHabitacionDiaria(pm, id, esCompartida, precio, ubicacion, id_operador);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("InserciÃ³n de cliente: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OfertaHabitacionDiaria(id, esCompartida, precio, ubicacion, id_operador);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public OfertaEsporadica adicionarOfertaEsporadica(Long id, Integer duracion, String descripcion, String descripcion_seguro,
			Integer num_habitaciones, String ubicacion, Integer precio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlOE.adicionarOfertaEsporadica(pm, id, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion, precio);
            System.out.println(tuplasInsertadas);
            tx.commit();

            log.trace ("Inserción de cliente: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new OfertaEsporadica(id, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion, precio);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	e.printStackTrace();
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	

 }
