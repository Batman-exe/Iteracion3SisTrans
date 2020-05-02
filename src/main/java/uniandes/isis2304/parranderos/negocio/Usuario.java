package uniandes.isis2304.parranderos.negocio;

public abstract class Usuario {

	private String contrasenia;
	
	private String nombre;
	
	private String userName;
	
	
	public String getContrasenia(){
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia){
		this.contrasenia=contrasenia;
	}
	
	public  String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
