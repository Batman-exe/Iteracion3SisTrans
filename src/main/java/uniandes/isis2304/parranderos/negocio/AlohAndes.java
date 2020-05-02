package uniandes.isis2304.parranderos.negocio;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohAndes;




public class AlohAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(AlohAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohAndes pA;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public AlohAndes ()
	{
		pA = PersistenciaAlohAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public AlohAndes (JsonObject tableConfig)
	{
		pA = PersistenciaAlohAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexi�n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pA.cerrarUnidadPersistencia ();
	}
	
	public Cliente adicionarCliente (Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
        log.info ("Adicionando Cliente " + nombre);
        Cliente cliente = pA.adicionarCliente(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
        log.info ("Adicionando Cliente: " + cliente);
        return cliente;
	}
	
	public PersonaJuridica adicionarPersonaJuridica (Long nit, String nombre, String tipo, String horaApertura,
			String horaCierre, String userName, String contrasena)
	{
        log.info ("Adicionando pj " + nombre);
        PersonaJuridica pj = new PersonaJuridica(nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
        log.info ("Adicionando pj: " + pj);
        return pj;
	}
	
	public PersonaNatural adicionarPersonaNatural(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
        log.info ("Adicionando persona natural " + nombre);
        PersonaNatural pN = pA.adicionarPersonaNatural(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
        log.info ("Adicionando persona natural: " + pN);
        return pN;
	}
	
	public Oferta adicionarOferta(Long id, String tipoOferta)
	{
        log.info ("Adicionando oferta " + id);
        Oferta o = pA.adicionarOferta(id, tipoOferta);
        log.info ("Adicionando oferta: " + o);
        return o;
	}
	
	public OfertaApartamento adicionarOfertaApartamento(Long id, Integer capacidad, String descripcion, Boolean esAmoblado,
			String ubicacion, Long docOperador, String tipoDocOperador, Integer precio)
	{
        log.info ("Adicionando oferta apartamento " + id);
        OfertaApartamento pN = pA.adicionarOfertaApartamento(id, capacidad, descripcion, esAmoblado, ubicacion, docOperador, tipoDocOperador, precio);
        log.info ("Adicionando oferta apartamento: " + pN);
        return pN;
	}
	
	public OfertaHabitacionMensual adicionarOfertaHabitacionMensual(Long id, Integer capacidad, String descripcion,
			String ubicacion, Long docOperador, String tipoDocOperador, Integer precio)
	{
        log.info ("Adicionando oferta habitacion mensual " + id);
        OfertaHabitacionMensual oHM = pA.adicionarOfertaHabitacionMensual(id, capacidad, descripcion, ubicacion, docOperador, tipoDocOperador, precio);
        log.info ("Adicionando oferta habitacion mensual: " +oHM);
        return oHM;
	}
	
	public Reserva adicionarReserva(Long numeroReserva, String fechaInicio, String fechaFin, Long idOferta, 
			Long docCliente, String tipoDoc, String fechaCandelacion)
	{
        log.info ("Adicionando reserva " + numeroReserva);
        Reserva r = pA.adicionarReservas(numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc, fechaCandelacion);
        log.info ("Adicionando reserva: " +r);
        return r;
	}
	
	public long eliminarReservaPorNumero (Long numReserva)
	{
        log.info ("Eliminando reserva por id: " + numReserva);
        long resp = pA.eliminarReservaPorNumero(numReserva);
        log.info ("Eliminando reserva: " + resp);
        return resp;
	}
	
}
