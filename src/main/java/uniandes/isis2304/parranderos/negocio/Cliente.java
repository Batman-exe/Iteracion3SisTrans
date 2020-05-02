package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class Cliente extends Usuario implements VOCliente{

	private Long numeroDocumento;
	
	private String tipoDocumento;
	
	private String nacionalidad;
	
	private String tipo;
		
	private List<Oferta> interesan;
	
	private List<Reserva> reservas;
	

	
	public Cliente() {
		
	}

	public Cliente(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena) {
		
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

	public List<Oferta> getInteresan() {
		return interesan;
	}


	
	public void setOfertas(List<Oferta> ofertas) {
		this.interesan = ofertas;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "Cliente [nacionalidad=" + nacionalidad + ", numeroDocumento=" + numeroDocumento + ", tipo=" + tipo
				+ ", tipoDocumento=" + tipoDocumento + ", interesan=" + interesan
				+ ", reservas=" + reservas + "]";
	}
	
	
}
