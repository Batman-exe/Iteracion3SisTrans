package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOCliente {

	public String getNacionalidad();
	
	public Long getNumeroDocumento();

	public String getTipo();
	
	public String getTipoDocumento();
	
	public List<Oferta> getInteresan();
	
	public List<Reserva> getReservas();
	
	public String toString();	
}
