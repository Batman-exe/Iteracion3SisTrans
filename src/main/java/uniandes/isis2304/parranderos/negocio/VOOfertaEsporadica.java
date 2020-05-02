package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOOfertaEsporadica {

	public Integer getDuracion();

	public String getDescripcion();
	
	public String getDescripcion_seguro();
	
	public Integer getNum_habitaciones();

	public String getUbicacion();
	
	public List<Reserva> getReservas();
	
	public String toString();
}
