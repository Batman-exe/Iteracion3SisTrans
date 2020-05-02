package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class PersonaNatural extends Usuario implements VOPersonaNatural{

	private Long numeroDocumento;
	
	private String tipoDocumento;
	
	private String nacionalidad;
	
	private String tipo;
	
	private List<OfertaApartamento> ofertasApartamento;
	
	private List<OfertaHabitacionMensual> ofertasHabitacionMensual;

	private List<OfertaEsporadica> ofertasEsporadicas;
	
	
	public PersonaNatural() {

	}
	
	public PersonaNatural(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumento = tipoDocumento;
		super.setNombre(nombre);
		this.nacionalidad = nacionalidad;
		this.tipo = tipo;
		super.setUserName(userName);
		super.setContrasenia(contrasena);
	}




	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Long getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public List<OfertaApartamento> getOfertasApartamento() {
		return ofertasApartamento;
	}

	public void setOfertasApartamento(List<OfertaApartamento> ofertasApartamento) {
		this.ofertasApartamento = ofertasApartamento;
	}

	public List<OfertaHabitacionMensual> getOfertasHabitacionMensual() {
		return ofertasHabitacionMensual;
	}

	public void setOfertasHabitacionMensual(List<OfertaHabitacionMensual> ofertasHabitacionMensual) {
		this.ofertasHabitacionMensual = ofertasHabitacionMensual;
	}
	
	public List<OfertaEsporadica> getOfertasEsporadicas() {
		return ofertasEsporadicas;
	}

	public void setOfertasEsporadicas(List<OfertaEsporadica> ofertasEsporadicas) {
		this.ofertasEsporadicas = ofertasEsporadicas;
	}

	@Override
	public String toString() {
		return "PersonaNatural [nacionalidad=" + nacionalidad + ", numeroDocumento=" + numeroDocumento + ", tipo="
				+ tipo + ", tipoDocumento=" + tipoDocumento + ", ofertasApartamento=" + ofertasApartamento
				+ ", ofertasHabitacionMensual=" + ofertasHabitacionMensual + "]";
	}
	
}
