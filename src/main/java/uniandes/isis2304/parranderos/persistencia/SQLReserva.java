package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLReserva {

	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLReserva (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarReserva (PersistenceManager pm, Long numeroReserva, String fechaInicio, String fechaFin, Long idOferta, 
			Long docCliente, String tipoDoc, String fechaCandelacion) 
	{
       Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservas() 
       + "(num_reserva, fecha_inicio, fecha_fin, id_oferta, doc_cliente, tipo_doc_cliente, fecha_cancelacion) values (?, ?, ?, ?, ?, ?, ?)");
       q.setParameters(numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc,fechaCandelacion);
       return (long) q.executeUnique();
	}
	
	public long eliminarReserva (PersistenceManager pm, Long idReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas() + " WHERE num_reserva = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
	}
}
