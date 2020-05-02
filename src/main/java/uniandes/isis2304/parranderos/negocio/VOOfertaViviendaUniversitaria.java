package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOOfertaViviendaUniversitaria {

	public Integer getCapacidad();

	public String getDuracion();

	public Boolean getEsCompartida();

	public List<Adicional> getServiciosAdicionales();

	public List<Reserva> getReservas();
	
	public String toString();
}
