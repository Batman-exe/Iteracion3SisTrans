package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOfertaHabitacionMensual {

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
	public SQLOfertaHabitacionMensual (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarHabitacionMensual(PersistenceManager pm, Long id, Integer capacidad, String descripcion,
			String ubicacion, Long docOperador, String tipoDocOperador) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaHabitacionMensual() 
        	+ "(id, capacidad, descripcion, ubicacion, doc_operador, tipo_doc_op) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad, descripcion, ubicacion, docOperador, tipoDocOperador);
        return (long) q.executeUnique();
	}
	
}