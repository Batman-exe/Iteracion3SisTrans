package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaHabitacionDiaria extends Oferta implements VOOfertaHabitacionDiaria{

	

	private Boolean esCompartida;
	
	private String ubicacion;
	
	private Long id_operador;
	
	private List<Reserva> reservas;

	public OfertaHabitacionDiaria() {

	}
	

	

	public OfertaHabitacionDiaria(String id, String tipo, Integer disponible, Integer precio, Boolean esCompartida, String ubicacion, Long id_operador) {
		super.setId_oferta(id);
		super.setTipo_oferta(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.esCompartida = esCompartida;
		this.ubicacion = ubicacion;
		this.id_operador = id_operador;
	}




	public Long getId_operador() {
		return id_operador;
	}

	public void setId_operador(Long id_operador) {
		this.id_operador = id_operador;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Boolean getEsCompartida() {
		return esCompartida;
	}

	public void setEsCompartida(Boolean esIndividual) {
		this.esCompartida = esIndividual;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "OfertaHabitacionDiaria [ubicacion=" + ubicacion + ", esIndividual=" + esCompartida
				+ ", reservas=" + reservas + "]";
	}
	
	
}
