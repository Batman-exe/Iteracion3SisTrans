package uniandes.isis2304.parranderos.negocio;

public class Adicional implements VOAdicional{

	private String id_oferta;
	
	private String nombre;

	private int precio;

	public Adicional()
	{
		this.id_oferta = "Default";
		this.nombre = "Default";
		this.precio = 0;
		
	}

	public Adicional(String id_oferta, String nombre, Integer precio)
	{
		this.id_oferta = id_oferta;
		this.nombre=nombre;
		this.precio=precio;
	}
	
	

	public String getId_oferta() {
		return id_oferta;
	}

	public void setId_oferta(String id_oferta) {
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
		return "Adicional [id_oferta=" + id_oferta + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

	


}
