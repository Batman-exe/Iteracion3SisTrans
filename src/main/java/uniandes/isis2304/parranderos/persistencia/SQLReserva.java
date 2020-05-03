package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Oferta;
import uniandes.isis2304.parranderos.negocio.Reserva;

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
       System.out.println(docCliente);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReservas() 
       + "(num_reserva, fecha_inicio, fecha_fin, id_oferta, doc_cliente, tipo_doc_cliente, fecha_cancelacion) values (?, ?, ?, ?, ?, ?, ?)");
       q.setParameters(numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc,fechaCandelacion);
       return (long) q.executeUnique();
	}
	
	public long eliminarReserva (PersistenceManager pm, Long idReserva, Long idOferta)
	{
        System.out.println(idOferta);
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas() + " WHERE num_reserva = ? AND id_oferta = ? ");
        q.setParameters(idReserva, idOferta);
        
        return (long) q.executeUnique();
	}
	
	public List<Reserva> darReservas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReservas() );
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		return (List<Reserva>) q.executeResultList(Reserva.class);
	}
}
