package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class PersonaJuridica extends Usuario implements VOPersonaJuridica {

	private Long nit;
	
	private String tipo;
	
	private String horaApertura;

	private String horaCierre;

	
	private List<OfertaViviendaUniversitaria> ofertasViviendaUniversitaria; 
	
	private List<OfertaHabitacionDiaria> ofertasHabitacionDiaria;

	public PersonaJuridica() {

	} 
	
	

	public PersonaJuridica(Long nit, String nombre, String tipo, String horaApertura, String horaCierre, String userName, 
			String contrasena) {
		super();
		this.nit = nit;
		super.setNombre(nombre);
		this.tipo = tipo;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		super.setUserName(userName);
		super.setContrasenia(contrasena);
	}



	public Long getNit() {
		return nit;
	}

	public void setNit(Long nit) {
		this.nit = nit;
	}

	public String getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<OfertaViviendaUniversitaria> getOfertasViviendaUniversitaria() {
		return ofertasViviendaUniversitaria;
	}

	public void setOfertasViviendaUniversitaria(List<OfertaViviendaUniversitaria> ofertasViviendaUniversitaria) {
		this.ofertasViviendaUniversitaria = ofertasViviendaUniversitaria;
	}

	public List<OfertaHabitacionDiaria> getOfertasHabitacionDiaria() {
		return ofertasHabitacionDiaria;
	}

	public void setOfertasHabitacionDiaria(List<OfertaHabitacionDiaria> ofertasHabitacionDiaria) {
		this.ofertasHabitacionDiaria = ofertasHabitacionDiaria;
	}

	@Override
	public String toString() {
		return "PersonaJuridica [nit=" + nit + ", horaApertura=" + horaApertura + ", horaCierre=" + horaCierre
				+ ", tipo=" + tipo + ", ofertasViviendaUniversitaria=" + ofertasViviendaUniversitaria
				+ ", ofertasHabitacionDiaria=" + ofertasHabitacionDiaria + "]";
	} 
	
	
}
