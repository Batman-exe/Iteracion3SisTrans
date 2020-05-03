package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class OfertaViviendaUniversitaria extends Oferta implements VOOfertaViviendaUniversitaria {

	
	private Integer capacidad;
	
	private String duracion;

	private Boolean esCompartida;
	
	private Long id_operador;
	
	private List<Reserva> reservas;
	
	private List<Adicional> serviciosAdicionales;

	

	public OfertaViviendaUniversitaria() {
		
	}
	
	
	
	
	public OfertaViviendaUniversitaria(String id, String tipo, Integer disponible, Integer precio, Integer capacidad, String duracion,
			Boolean esCompartida, Long id_operador) {
		super.setId_oferta(id);
		super.setTipo_oferta(tipo);
		super.setDisponible(disponible);
		super.setPrecio(precio);
		this.capacidad = capacidad;
		this.duracion = duracion;
		this.esCompartida = esCompartida;
		this.id_operador=id_operador;
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

	public Long getId_operador() {
		return id_operador;
	}

	public void setId_operador(Long id_operador) {
		this.id_operador = id_operador;
	}

	@Override
	public String toString() {
		return "OfertaViviendaUniversitaria [duracion=" + duracion + ", esCompartida=" + esCompartida
				+  ", capacidad=" + capacidad + ", reservas=" + reservas + "]";
	}
}
