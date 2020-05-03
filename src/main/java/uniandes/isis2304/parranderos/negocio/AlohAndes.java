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
	
	
	public PersonaNatural adicionarPersonaNatural(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
        log.info ("Adicionando persona natural " + nombre);
        PersonaNatural pN = pA.adicionarPersonaNatural(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
        log.info ("Adicionando persona natural: " + pN);
        return pN;
	}
	
	
	public PersonaJuridica adicionarPersonaJuridica (Long nit, String nombre, String tipo, String horaApertura, String horaCierre,
			String userName, String contrasena)
	{
        log.info ("Adicionando pj " + nombre);
        PersonaJuridica pj = pA.adicionarPersonaJuridica(nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
        log.info ("Adicionando pj: " + pj);
        return pj;
	}
	
	public Oferta adicionarOferta(Long id, String tipoOferta, Boolean disponible, Integer precio)
	{
        log.info ("Adicionando oferta " + id);
        Oferta o = pA.adicionarOferta(id, tipoOferta, disponible, precio);
        log.info ("Adicionando oferta: " + o);
        return o;
	}
	
	public Adicional adicionarAdicional(Long id_oferta, String nombre, Integer precio){
		log.info ("Adicionando Adicional " + id_oferta +","+ nombre);
        Adicional a = pA.adicionarAdicional(id_oferta, nombre, precio);
        log.info ("Adicionando adicional: " + a);
        return a;
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
	
	public Contrato adicionarContrato(Long numContrato, Integer duracion, Long numReserva)
	{
		log.info ("Adicionando contrato " + numContrato);
		Contrato c = pA.adicionarContrato(numContrato, duracion, numReserva);
		log.info ("Adicionando contrato: " +c);
		return c;
	}
	
	public OfertaApartamento adicionarOfertaApartamento(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad, 
			String descripcion, Boolean esAmoblado, String ubicacion, Long documentoOp, String tipoDocOp, Long contrato)
	{
        log.info ("Adicionando oferta apartamento " + id);
        OfertaApartamento pN = pA.adicionarOfertaApartamento(id, tipo, disponible, precio, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
        log.info ("Adicionando oferta apartamento: " + pN);
        return pN;
	}
	
	public OfertaEsporadica adicionarOfertaEsporadica(Long id, String tipo, Boolean disponible, Integer precio, Integer duracion, 
			String descripcion, String descripcion_seguro, Integer num_habitaciones, String ubicacion)
	{
		log.info ("Adicionando oferta esporadica " + id);
        OfertaEsporadica oE = pA.adicionarOfertaEsporadica(id, tipo, disponible, precio, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion);
        log.info ("Adicionando oferta esporadica: " + oE);
        return oE;
	}
	
	public OfertaHabitacionDiaria adicionarOfertaHabitacionDiaria(Long id, String tipo, Boolean disponible, Integer precio, Boolean esCompartida, String ubicacion, Long id_operador)
	{
		log.info ("Adicionando oferta esporadica " + id);
        OfertaHabitacionDiaria oHD = pA.adicionarOfertaHabitacionDiaria(id, tipo, disponible, precio, esCompartida, ubicacion, id_operador);
        log.info ("Adicionando oferta esporadica: " + oHD);
        return oHD;
	}
	
	public OfertaHabitacionMensual adicionarOfertaHabitacionMensual(Long id, String tipo, Boolean disponible, Integer precio, 
			Integer capacidad, String descripcion, String ubicacion, Long documentoOp, String tipoDocOp, Long contrato)
	{
        log.info ("Adicionando oferta habitacion mensual " + id);
        OfertaHabitacionMensual oHM = pA.adicionarOfertaHabitacionMensual(id, tipo, disponible, precio, capacidad, descripcion, ubicacion, documentoOp, tipoDocOp, contrato);
        log.info ("Adicionando oferta habitacion mensual: " +oHM);
        return oHM;
	}

	public OfertaViviendaUniversitaria adicionarOfertaViviendaUniversitaria(Long id, String tipo, Boolean disponible, 
			Integer precio, Integer capacidad, String duracion, Boolean esCompartida, Long id_operador)
	{
		log.info ("Adicionando oferta vivienda universitaria " + id);
		OfertaViviendaUniversitaria oVU = pA.adicionarOfertaViviendaUniversitaria(id, tipo, disponible, precio, capacidad, duracion, esCompartida, id_operador);
		log.info ("Adicionando oferta vivienda universitaria: " +oVU);
		return oVU;
	}
}
