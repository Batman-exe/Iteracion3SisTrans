package uniandes.isis2304.parranderos.negocio;

public class Adicional implements VOAdicional{

	private Long id_oferta;
	
	private String nombre;

	private Integer precio;

	public Adicional()
	{

	}

	public Adicional(Long id_oferta, String nombre, Integer precio)
	{
		this.id_oferta = id_oferta;
		this.nombre=nombre;
		this.precio=precio;
	}
	
	

	public Long getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(Long id_oferta) {
		this.id_oferta = id_oferta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Adicional [nombre=" + nombre + ", precio=" + precio + "]";
	}


}
