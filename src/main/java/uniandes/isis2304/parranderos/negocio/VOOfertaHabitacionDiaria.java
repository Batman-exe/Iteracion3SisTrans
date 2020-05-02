package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOOfertaHabitacionDiaria {


	public Boolean getEsCompartida();

	public String getUbicacion();
	
	public Long getId_operador();

	public List<Reserva> getReservas();
	
	public String toString();
}
