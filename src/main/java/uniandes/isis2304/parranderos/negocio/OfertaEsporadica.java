package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaEsporadica extends Oferta implements VOOfertaEsporadica {

	private Integer duracion;
	
	private String descripcion;
	
	private String descripcion_seguro;
	
	private Integer num_habitaciones;
	
	private String ubicacion;
	
	private List<Reserva> reservas;
	
	public OfertaEsporadica() {

	}

	public OfertaEsporadica(String id, String tipo, Integer disponible, Integer precio, Integer duracion, String descripcion, String descripcion_seguro, Integer num_habitaciones,
			String ubicacion) {
		super.setId_oferta(id);
		super.setTipo_oferta(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.duracion = duracion;
		this.descripcion = descripcion;
		this.descripcion_seguro = descripcion_seguro;
		this.num_habitaciones = num_habitaciones;
		this.ubicacion = ubicacion;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion_seguro() {
		return descripcion_seguro;
	}

	public void setDescripcion_seguro(String descripcion_seguro) {
		this.descripcion_seguro = descripcion_seguro;
	}

	public Integer getNum_habitaciones() {
		return num_habitaciones;
	}

	public void setNum_habitaciones(Integer num_habitaciones) {
		this.num_habitaciones = num_habitaciones;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
}
