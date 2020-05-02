package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOPersonaNatural {


	public Long getNumeroDocumento();
	
	public String getTipoDocumento();
	
	public String getNombre();

	public String getNacionalidad();
	
	public String getTipo();

	public String getUserName();
	
	public String getContrasenia();
	
	public List<OfertaHabitacionMensual> getOfertasHabitacionMensual();

	public List<OfertaApartamento> getOfertasApartamento();
	
	public List<OfertaEsporadica> getOfertasEsporadicas();

	public String toString();
}
