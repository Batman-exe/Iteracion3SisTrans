package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPersonaJuridica {

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
	public SQLPersonaJuridica (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPersonaJuridica(PersistenceManager pm, Long nit, String nombre, String tipo, String horaApertura,
			String horaCierre, String userName, String contrasena) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonasJuridicas() + "(nit, nombre, tipo, hora_apertura, hora_cierre, username, contrasena) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena );
        return (long) q.executeUnique();
	}
}
