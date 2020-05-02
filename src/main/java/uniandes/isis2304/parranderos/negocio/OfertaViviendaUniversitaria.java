package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaViviendaUniversitaria extends Oferta implements VOOfertaViviendaUniversitaria {

	
	private Integer capacidad;
	
	private String duracion;

	private Boolean esCompartida;
	
	private List<Reserva> reservas;
	
	private List<Adicional> serviciosAdicionales;

	

	public OfertaViviendaUniversitaria() {
		
	}
	
	
	
	
	public OfertaViviendaUniversitaria(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad, String duracion,
			Boolean esCompartida) {
		super.setId(id);
		super.setTipo(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.capacidad = capacidad;
		this.duracion = duracion;
		this.esCompartida = esCompartida;
	}





	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Boolean getEsCompartida() {
		return esCompartida;
	}

	public void setEsCompartida(Boolean esIndividual) {
		this.esCompartida = esIndividual;
	}


	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	
	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	
	public List<Adicional> getServiciosAdicionales() {
		return serviciosAdicionales;
	}

	public void setServiciosAdicionales(List<Adicional> serviciosAdicionales) {
		this.serviciosAdicionales = serviciosAdicionales;
	}

	@Override
	public String toString() {
		return "OfertaViviendaUniversitaria [duracion=" + duracion + ", esCompartida=" + esCompartida
				+  ", capacidad=" + capacidad + ", reservas=" + reservas + "]";
	}
}
