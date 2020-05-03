package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLOfertaApartamento {
	
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
	public SQLOfertaApartamento (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarOfertaApartamento(PersistenceManager pm, Long id, Integer capacidad, String descripcion, Boolean esAmoblado,
			String ubicacion, Long docOperador, String tipoDocOperador, Long contrato) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaApartamento() + "(id, capacidad, descripcion, es_amoblado, ubicacion, doc_operador, tipo_doc_op, contrato) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad, descripcion, esAmoblado, ubicacion, docOperador, tipoDocOperador, contrato);
        return (long) q.executeUnique();
	}

}
