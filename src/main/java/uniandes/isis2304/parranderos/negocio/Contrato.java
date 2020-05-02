package uniandes.isis2304.parranderos.negocio;

public class Contrato implements VOContrato {


	private Long numContrato;

	private Integer duracion;
	
	private Long idReserva;

	

	

	public Contrato() {

	}

	public Contrato(Long numContrato, Integer duracion, Long numReserva) {

		this.numContrato = numContrato;
		this.duracion = duracion;
		this.idReserva = numReserva;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Long getNumReserva() {
		return idReserva;
	}

	public void setNumReserva(Long reserva) {
		this.idReserva = reserva;
	}

	public Long getNumContrato() {
		return numContrato;
	}

	public void setNumContrato(Long numContrato) {
		this.numContrato = numContrato;
	}


	@Override
	public String toString() {
		return "Contrato [duracion=" + duracion + ", numContrato=" + numContrato + ", reserva=" + idReserva + "]";
	}
	
}
