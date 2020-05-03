package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaApartamento extends Oferta implements VOOfertaApartamento {

	private String id;
	
	private Integer capacidad;
	
	private String descripcion;
		
	private Integer es_amoblado;
	
	private String ubicacion;
	
	private String doc_operador;
	
	private String tipo_doc_op;
	
	private String contrato;
		


	
	public OfertaApartamento() {

		this.id = "Default";
		this.capacidad = 0;
		this.descripcion = "Default";
		this.es_amoblado = 0;
		this.ubicacion = "Default";
		this.doc_operador = "Default";
		this.tipo_doc_op = "Default";
		this.contrato = "Default";
	}
	
	

	public OfertaApartamento(String id, String tipo, Integer disponible, Integer precio, Integer capacidad, String descripcion, Integer es_amoblado, String ubicacion, String doc_operador,
			String tipo_doc_op, String contrato) {
		super.setId_oferta(id);
		super.setTipo_oferta(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.capacidad = capacidad;
		this.descripcion = descripcion;
		this.es_amoblado = es_amoblado;
		this.ubicacion = ubicacion;
		this.doc_operador = doc_operador;
		this.tipo_doc_op = tipo_doc_op;
		this.contrato=contrato;
	}

	


	public String getId(){
		return this.id;
	}
	
	public void setId(String id){
		this.id = id;
	}

	
	public Integer getCapacidad() {
		return capacidad;
	}



	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Integer getEs_amoblado() {
		return es_amoblado;
	}



	public void setEs_amoblado(Integer es_amoblado) {
		this.es_amoblado = es_amoblado;
	}



	public String getUbicacion() {
		return ubicacion;
	}



	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}



	public String getDoc_operador() {
		return doc_operador;
	}



	public void setDoc_operador(String doc_operador) {
		this.doc_operador = doc_operador;
	}



	public String getTipo_doc_op() {
		return tipo_doc_op;
	}



	public void setTipo_doc_op(String tipo_doc_op) {
		this.tipo_doc_op = tipo_doc_op;
	}



	public String getContrato() {
		return contrato;
	}



	public void setContrato(String contrato) {
		this.contrato = contrato;
	}





	@Override
	public String toString() {
		return super.toString() + " OfertaApartamento [capacidad=" + capacidad + ", descripcion=" + descripcion 
				+ ", esAmoblado=" + es_amoblado
				+ ", ubicacion="
				+ ubicacion + ", contrato=" + contrato + "]";
	}



	
}
