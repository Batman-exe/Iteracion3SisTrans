package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaApartamento extends Oferta implements VOOfertaApartamento {

	private Integer capacidad;
	
	private String descripcion;
		
	private Boolean esAmoblado;
	
	private String ubicacion;
	
	private Long documentoOp;
	
	private String tipoDocOp;
	
	private Long contrato;
		
	private List<Reserva> reservas;

	
	public OfertaApartamento() {

		
	}
	
	

	public OfertaApartamento(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad, String descripcion, Boolean esAmoblado, String ubicacion, Long documentoOp,
			String tipoDocOp, Long contrato) {
		super.setId(id);
		super.setTipo(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.capacidad = capacidad;
		this.descripcion = descripcion;
		this.esAmoblado = esAmoblado;
		this.ubicacion = ubicacion;
		this.documentoOp = documentoOp;
		this.tipoDocOp = tipoDocOp;
		this.contrato=contrato;
	}

	


	public Long getDocumentoOp() {
		return documentoOp;
	}



	public void setDocumentoOp(Long documentoOp) {
		this.documentoOp = documentoOp;
	}



	public String getTipoDocOp() {
		return tipoDocOp;
	}



	public void setTipoDocOp(String tipoDocOp) {
		this.tipoDocOp = tipoDocOp;
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


	public Boolean getEsAmoblado() {
		return esAmoblado;
	}

	public void setEsAmoblado(Boolean esAmoblado) {
		this.esAmoblado = esAmoblado;
	}



	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Long getContrato() {
		return contrato;
	}

	public void setContrato(Long contrato) {
		this.contrato = contrato;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "OfertaApartamento [capacidad=" + capacidad + ", descripcion=" + descripcion 
				+ ", esAmoblado=" + esAmoblado
				+ ", ubicacion="
				+ ubicacion + ", contrato=" + contrato + ", reservas=" + reservas + "]";
	}
	
}
