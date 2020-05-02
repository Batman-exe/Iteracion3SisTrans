package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOferta {


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
	public SQLOferta (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOferta (PersistenceManager pm, Long id, String tipoOferta) 
	{
       Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertas() + "(id_oferta, tipo_oferta) values (?, ?)");
       q.setParameters(id, tipoOferta);
       return (long) q.executeUnique();
	}
	
	public long eliminarOferta (PersistenceManager pm, long idoferta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOfertas() + " WHERE id_oferta = ?");
        q.setParameters(idoferta);
        return (long) q.executeUnique();
	}
}
