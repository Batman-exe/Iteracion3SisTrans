package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOPersonaJuridica {

	public Long getNit();

	public String getNombre();
	
	public String getTipo();
	
	public String getHoraApertura();
	
	public String getHoraCierre();
	
	public String getUserName();
	
	public String getContrasenia();
	
	public List<OfertaHabitacionDiaria> getOfertasHabitacionDiaria();
	
	public List<OfertaViviendaUniversitaria> getOfertasViviendaUniversitaria();
}
