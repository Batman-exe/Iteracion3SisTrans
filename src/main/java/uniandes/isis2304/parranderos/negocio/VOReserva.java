package uniandes.isis2304.parranderos.negocio;

public interface VOReserva {
	
	public Long getNumReserva();

	public String getFechaInicio();

	public String getFechaFin();

	public Long getId_oferta();

	public Long getDocumentoCliente();
	
	public String getTipoDocCliente();

	public String getFechaCancelacion();
	
	public String toString();

}
