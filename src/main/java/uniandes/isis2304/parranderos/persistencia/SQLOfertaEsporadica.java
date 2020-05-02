package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOfertaEsporadica {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
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
	public SQLOfertaEsporadica (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOfertaEsporadica (PersistenceManager pm, Long id, Integer duracion, String descripcion,
			String descripcion_seguro, Integer num_habitaciones, String ubicacion, Integer precio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaEsporadica() + "(id, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion, precio) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion, precio);
        return (long) q.executeUnique();
	}
}
