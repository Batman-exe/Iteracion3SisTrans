package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLPersonaNatural {

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
	public SQLPersonaNatural (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarPersonaNatural (PersistenceManager pm, Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena) 
	{
        System.out.println("{"+numeroDocumento+", "+tipoDocumento+", "+nombre+", "+nacionalidad+", "+tipo+", "+userName+", "+contrasena+"}");
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonasNaturales() + "(numero_documento, tipo_documento, nombre, nacionalidad, tipo, username, contrasena) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena );
        return (long) q.executeUnique();
	}
}
