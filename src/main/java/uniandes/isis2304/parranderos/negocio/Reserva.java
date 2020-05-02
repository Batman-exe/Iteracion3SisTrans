package uniandes.isis2304.parranderos.negocio;

public class Reserva implements VOReserva{

	private Long numReserva;
	
	private String fechaInicio;

	private String fechaFin;
	
	private Long id_oferta;
	
	private Long documentoCliente;
	
	private String tipoDocCliente;
	
	private String fechaCancelacion;
	

	public Reserva() {

	}
	
	


	public Reserva(Long numReserva, String fechaInicio, String fechaFin, Long id_oferta, Long documentoCliente,
			String tipoDocCliente, String fechaCancelacion) {
		this.numReserva = numReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.id_oferta = id_oferta;
		this.documentoCliente = documentoCliente;
		this.tipoDocCliente = tipoDocCliente;
		this.fechaCancelacion = fechaCancelacion;
	}




	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}


	public Long getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(Long numReserva) {
		this.numReserva = numReserva;
	}

	public Long getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(Long id_oferta) {
		this.id_oferta = id_oferta;
	}

	public Long getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(Long documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public String getTipoDocCliente() {
		return tipoDocCliente;
	}

	public void setTipoDocCliente(String tipoDocCliente) {
		this.tipoDocCliente = tipoDocCliente;
	}

	public String getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(String fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	@Override
	public String toString() {
		return "Reserva [numReserva=" + numReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", id_oferta=" + id_oferta + ", documentoCliente=" + documentoCliente + ", tipoDocCliente="
				+ tipoDocCliente + ", fechaCancelacion=" + fechaCancelacion + "]";
	}
	
}
