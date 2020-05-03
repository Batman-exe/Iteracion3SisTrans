package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Oferta;


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
	
	public long adicionarOferta (PersistenceManager pm, Long id, String tipoOferta, Boolean disponible, Integer precio) 
	{
       Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertas() + "(id_oferta, tipo_oferta, disponible, precio) values (?, ?, ?, ?)");
       q.setParameters(id, tipoOferta, disponible, precio);
       return (long) q.executeUnique();
	}
	
	public long eliminarOferta (PersistenceManager pm, Long idoferta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOfertas() + " WHERE id_oferta = ?");
        q.setParameters(idoferta);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN TIPO DE BEBIDA de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idOferta - El identificador del tipo de bebida
	 * @return El objeto TIPOBEBIDA que tiene el identificador dado
	 */
	public Oferta darOfertaPorId (PersistenceManager pm, long idOferta) 
	{
		System.out.println(idOferta);
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOfertas() + " WHERE id_oferta = ?");
		q.setResultClass(Oferta.class);
		q.setParameters(idOferta);
		System.out.println();
		return (Oferta) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE BEBIDA de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOBEBIDA
	 */
	public List<Oferta> darOfertas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOfertas() );
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		return (List<Oferta>) q.executeResultList(Oferta.class);
	}
	
	public List<Oferta> darOfertasTipo (PersistenceManager pm, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOfertas() + " WHERE tipo_oferta = ?");
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		q.setParameters(tipo);
		return (List<Oferta>) q.executeResultList(Oferta.class);
	}
	
	public long cambiarDisponible (PersistenceManager pm,Long idOferta, Boolean disponible)
	{
		Query q = pm.newQuery(SQL, "UPDATE  " + pp.darTablaOfertas() +" SET disponible = ? "+ " WHERE id_oferta = ?");
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		q.setParameters(disponible, idOferta);
		return (long) q.executeUnique();
	}
}
