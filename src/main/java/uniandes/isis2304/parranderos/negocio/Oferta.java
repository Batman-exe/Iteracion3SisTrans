package uniandes.isis2304.parranderos.negocio;

public  class Oferta implements VOOferta{

	private String id_oferta;
	
	private String tipo_oferta;
	
	private int disponible;
	
	private int precio;


	public Oferta() {
		this.id_oferta = "Default";
		this.tipo_oferta = "Default";
		this.disponible = 0;
		this.precio = 0;
		
	}




	public Oferta(String id_oferta, String tipo_oferta, int disponible, int precio) {
		this.id_oferta = id_oferta;
		this.tipo_oferta = tipo_oferta;
		this.disponible = disponible;
		this.precio = precio;
	}
	



	public String getId() {
		return id_oferta;
	}


	public void setId_oferta(String id_oferta) {
		this.id_oferta = id_oferta;
	}


	public String getTipo_oferta() {
		return tipo_oferta;
	}


	public void setTipo_oferta(String tipo_oferta) {
		this.tipo_oferta = tipo_oferta;
	}
	
	public int getDisponible() {
		return disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}


	public int getPrecio() {
		return precio;
	}


	public void setPrecio(int precio) {
		this.precio = precio;
	}

	
	

	@Override
	public String toString() {
		return "Oferta [id_oferta=" + id_oferta + ", tipo_oferta=" + tipo_oferta + ", disponible=" + disponible
				+ ", precio=" + precio + "]";
	}




	/**
	 * @param tipo - El TipoBebida a comparar
	 * @return True si tienen el mismo nombre
	 */
	public boolean equals(Object tipo) 
	{
		Oferta tb = (Oferta) tipo;
		return id_oferta == tb.id_oferta && ((String) tipo).equalsIgnoreCase (tb.tipo_oferta);
	}
}
