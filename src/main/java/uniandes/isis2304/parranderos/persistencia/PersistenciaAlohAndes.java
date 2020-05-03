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

import uniandes.isis2304.parranderos.negocio.Adicional;
import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Contrato;
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

	private SQLPersonaNatural sqlPersonaNatural;

	private SQLPersonaJuridica sqlPersonaJuridica;

	private SQLOferta sqlOferta;

	private SQLAdicional sqlAdicional;

	private SQLReserva sqlReserva;

	private SQLContrato sqlContrato;

	private SQLOfertaApartamento sqlOfertaApartamento;

	private SQLOfertaHabitacionMensual sqlOfertaHabitacionMensual;

	private SQLOfertaHabitacionDiaria sqlOfertaHabitacionDiaria;

	private SQLOfertaViviendaUniversitaria sqlOfertaViviendaUniversitaria;

	private SQLOfertaEsporadica sqlOfertaEsporadica;

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
		tablas.add("PERSONASNATURALES");
		tablas.add("PERSONASJURIDICAS");
		tablas.add("OFERTAS");
		tablas.add("ADICIONALES");
		tablas.add("INTERSEAN");
		tablas.add("RESERVAS");
		tablas.add("CONTRATOS");
		tablas.add("OFERTAAPARTAMENTO");
		tablas.add("OFERTAESPORADICA");
		tablas.add("OFERTAHABITACIONDIARIA");
		tablas.add("OFERTAHABITACIONMENSUAL");
		tablas.add("OFERTAVIVIENDAUNIVERSITARIA");
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
		sqlPersonaNatural = new SQLPersonaNatural(this);
		sqlPersonaJuridica = new SQLPersonaJuridica(this);
		sqlOferta= new SQLOferta(this);
		sqlAdicional= new SQLAdicional(this);

		sqlReserva= new SQLReserva(this);
		sqlContrato= new SQLContrato(this);
		sqlOfertaApartamento= new SQLOfertaApartamento(this);
		sqlOfertaEsporadica= new SQLOfertaEsporadica(this);
		sqlOfertaHabitacionDiaria= new SQLOfertaHabitacionDiaria(this);
		sqlOfertaHabitacionMensual= new SQLOfertaHabitacionMensual(this);
		sqlOfertaViviendaUniversitaria= new SQLOfertaViviendaUniversitaria(this);
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

	public String darTablaPersonasNaturales ()
	{
		return tablas.get (2);
	}

	public String darTablaPersonasJuridicas ()
	{
		return tablas.get (3);
	}

	public String darTablaOfertas()
	{
		return tablas.get (4);
	}

	public String darTablaAdicionales()
	{
		return tablas.get (5);
	}

	public String darTablaInteresan()
	{
		return tablas.get (6);
	}

	public String darTablaReservas()
	{
		return tablas.get (7);
	}

	public String darTablaContratos()
	{
		return tablas.get (8);
	}

	public String darTablaOfertaApartamento()
	{
		return tablas.get (9);
	}

	public String darTablaOfertaEsporadica()
	{
		return tablas.get (10);
	}

	public String darTablaOfertaHabitacionDiaria()
	{
		return tablas.get(11);
	}

	public String darTablaOfertaHabitacionMensual()
	{
		return tablas.get (12);
	}

	public String darTablaOfertaViviendaUniversitaria()
	{
		return tablas.get (13);
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
				System.out.println("gdiogaiudogaiogjidasjg");
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
			long tuplasInsertadas = sqlPersonaNatural.adicionarPersonaNatural(pm, numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);

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


	public PersonaJuridica adicionarPersonaJuridica(Long nit, String nombre, String tipo, String horaApertura,
			String horaCierre, String userName, String contrasena) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPersonaJuridica.adicionarPersonaJuridica(pm, nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
			System.out.println("fdfdasgasga");
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
				System.out.println("no funcionó");
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
			long tuplasInsertadas = sqlOferta.adicionarOferta(pm, id, tipo_oferta, disponible, precio);
			tx.commit();

			log.trace ("InserciÃ³n de oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Oferta(id, tipo_oferta, disponible, precio);
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


	public Adicional adicionarAdicional(Long id_oferta, String nombre, Integer precio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlAdicional.adicionarAdicional(pm, id_oferta, nombre, precio);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de Adicional: " + id_oferta + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Adicional(id_oferta, nombre, precio);
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


	public Contrato adicionarContrato(Long numContrato, Integer duracion, Long numReserva) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlContrato.adicionarContrato(pm, numContrato, duracion, numReserva);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de Contrato: " + numContrato + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Contrato(numContrato, duracion, numReserva);
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


	public OfertaApartamento adicionarOfertaApartamento(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad,
			String descripcion, Boolean esAmoblado, String ubicacion, Long documentoOp, String tipoDocOp, Long contrato ) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfertaApartamento.adicionarOfertaApartamento(pm, id, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de oferta apartamento: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new OfertaApartamento(id, tipo, disponible, precio, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
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


	public OfertaEsporadica adicionarOfertaEsporadica(Long id, String tipo, Boolean disponible, Integer precio, Integer duracion, 
			String descripcion, String descripcion_seguro, Integer num_habitaciones, String ubicacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfertaEsporadica.adicionarOfertaEsporadica(pm, id, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("Inserción de Oferta Esporadica: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new OfertaEsporadica(id, tipo, disponible, precio, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion);
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


	public OfertaHabitacionDiaria adicionarOfertaHabitacionDiaria(Long id, String tipo, Boolean disponible, Integer precio, 
			Boolean esCompartida, String ubicacion, Long id_operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfertaHabitacionDiaria.adicionarOfertaHabitacionDiaria(pm, id, esCompartida, ubicacion, id_operador);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de oferta habitacion diaria: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new OfertaHabitacionDiaria(id, tipo, disponible, precio, esCompartida, ubicacion, id_operador);
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


	public OfertaHabitacionMensual adicionarOfertaHabitacionMensual(Long id, String tipo, Boolean disponible, Integer precio, 
			Integer capacidad, String descripcion, String ubicacion, Long documentoOp, String tipoDocOp, Long contrato) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfertaHabitacionMensual.adicionarHabitacionMensual(pm, id, capacidad, descripcion, ubicacion, documentoOp, tipoDocOp, contrato);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de oferta habitacion mensual: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new OfertaHabitacionMensual(id, tipo, disponible, precio, capacidad, descripcion, ubicacion, documentoOp, tipoDocOp, contrato);
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

	public OfertaViviendaUniversitaria adicionarOfertaViviendaUniversitaria(Long id, String tipo, Boolean disponible, Integer precio, 
			Integer capacidad, String duracion, Boolean esCompartida, Long id_operador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlOfertaViviendaUniversitaria.adicionarOfertaViviendaUniversitaria(pm, id, capacidad, duracion, esCompartida, id_operador);
			System.out.println(tuplasInsertadas);
			tx.commit();

			log.trace ("InserciÃ³n de cliente: " + id + ": " + tuplasInsertadas + " tuplas insertadas");

			return new OfertaViviendaUniversitaria(id, tipo, disponible, precio, capacidad, duracion, esCompartida, id_operador);
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

}
