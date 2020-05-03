package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaHabitacionMensual extends Oferta implements VOOfertaHabitacionMensual{

	private Integer capacidad;
	
	private String descripcion;
	
	private String ubicacion;
	
	private Long documentoOp;
	
	private String tipoDocOp;
	
	private Long contrato;
	
	private List<Reserva> reservas;

	public OfertaHabitacionMensual() {

	}
	

	public OfertaHabitacionMensual(String id, String tipo, Integer disponible, Integer precio, Integer capacidad, String descripcion,
			String ubicacion, Long documentoOp, String tipoDocOp) {
		super.setId_oferta(id);
		super.setTipo_oferta(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.capacidad = capacidad;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.documentoOp = documentoOp;
		this.tipoDocOp = tipoDocOp;

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
		return "OfertaHabitacionMensual [capacidad=" + capacidad + ", descripcion=" + descripcion
				 + ", ubicacion=" + ubicacion + ", contrato="
				+ contrato + ", reservas=" + reservas + "]";
	}
	
}
