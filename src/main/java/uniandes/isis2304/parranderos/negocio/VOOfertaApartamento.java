package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOOfertaApartamento {

	public Integer getCapacidad();

	public String getDescripcion();

	public Boolean getEsAmoblado();
	
	public String getUbicacion();

	public Long getDocumentoOp();
	
	public String getTipoDocOp();
	
	public Long getContrato();
	
	public List<Reserva> getReservas();

	public String toString();
}
