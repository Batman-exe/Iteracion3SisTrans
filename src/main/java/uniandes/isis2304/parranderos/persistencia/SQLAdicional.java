package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Adicional;
import uniandes.isis2304.parranderos.negocio.Oferta;

public class SQLAdicional {

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
	public SQLAdicional (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarAdicional (PersistenceManager pm, Long id_oferta, String nombre, Integer precio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAdicionales()+ 
        		"(id_oferta, nombre, precio) values (?, ?, ?)");
        q.setParameters(id_oferta, nombre, precio );
        return (long) q.executeUnique();
	}
	
	public List<Adicional> darAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdicionales() );
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		return (List<Adicional>) q.executeResultList(Adicional.class);
	}
	
	public Adicional darAdicionalPorOfertaYNombre (PersistenceManager pm, long idOferta, String nombre) 
	{
		System.out.println(idOferta);
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAdicionales() + " WHERE id_oferta = ? AND nombre = ?");
		q.setResultClass(Adicional.class);
		q.setParameters(idOferta, nombre);
		return (Adicional) q.executeUnique();
	}
}
