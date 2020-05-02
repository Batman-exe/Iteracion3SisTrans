package uniandes.isis2304.parranderos.negocio;

public  class Oferta {

	private Long id;
	
	private String tipo;
	
	private Boolean disponible;
	
	private Integer precio;


	public Oferta() {
	}


	public Oferta(Long id, String tipo, Boolean disponible, Integer precio) {
		this.id = id;
		this.tipo = tipo;
		this.disponible= disponible;
		this.precio= precio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}


	public Integer getPrecio() {
		return precio;
	}


	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	
	
}
