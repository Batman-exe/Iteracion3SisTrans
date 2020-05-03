package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Oferta;
import uniandes.isis2304.parranderos.negocio.OfertaApartamento;

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
        System.out.println(tipoDocOperador);
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfertaApartamento() + 
        	"(id, capacidad, descripcion, es_amoblado, ubicacion, doc_operador, tipo_doc_op, contrato) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, capacidad, descripcion, esAmoblado, ubicacion, docOperador, tipoDocOperador, contrato);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TIPOS DE BEBIDA de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos TIPOBEBIDA
	 */
	public List<OfertaApartamento> darOfertasApartamento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOfertas() );
		//q.setResultClass(Oferta.class);
		//System.out.println(q.executeList().size());
		return (List<OfertaApartamento>) q.executeResultList(OfertaApartamento.class);
	}

}
