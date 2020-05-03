package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOfertaViviendaUniversitaria {

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
	public SQLOfertaViviendaUniversitaria (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOfertaViviendaUniversitaria (PersistenceManager pm, Long id,Integer capacidad, String duracion, 
			Boolean esCompartida, Long id_operador) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaViviendaUniversitaria() 
        + "(id, capacidad, duracion, es_compartida, id_operador) values (?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad,duracion,esCompartida, id_operador);
        return (long) q.executeUnique();
	}
}
