package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOfertaHabitacionDiaria {

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
	public SQLOfertaHabitacionDiaria (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOfertaHabitacionDiaria (PersistenceManager pm, Long id,Boolean esCompartida, String ubicacion, Long id_operador) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaHabitacionDiaria() + 
        		"(id, es_compartida, ubicacion, id_operador) values (?, ?, ?, ?)");
        q.setParameters(id, esCompartida, ubicacion, id_operador);
        return (long) q.executeUnique();
	}
}
