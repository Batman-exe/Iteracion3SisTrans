package uniandes.isis2304.parranderos.negocio;

import java.util.Date;

public class Reserva implements VOReserva{

	private String num_reserva;
	
	private Date fecha_inicio;

	private Date fecha_fin;
	
	private String id_oferta;
	
	private String doc_cliente;
	
	private String tipo_doc_cliente;
	
	private Date fecha_cancelacion;
	

	public Reserva() {

		this.num_reserva = "Defailt";
		this.fecha_inicio = null;
		this.fecha_fin = null;
		this.id_oferta = "Defailt";
		this.doc_cliente = "Defailt";
		this.tipo_doc_cliente = "Defailt";
		this.fecha_cancelacion = null;
	}


	public Reserva(String num_reserva, Date fecha_inicio, Date fecha_fin, String id_oferta, String doc_cliente,
			String tipo_doc_cliente, Date fecha_cancelacion) {
		this.num_reserva = num_reserva;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.id_oferta = id_oferta;
		this.doc_cliente = doc_cliente;
		this.tipo_doc_cliente = tipo_doc_cliente;
		this.fecha_cancelacion = fecha_cancelacion;
	}





	public String getNum_reserva() {
		return num_reserva;
	}


	public void setNum_reserva(String num_reserva) {
		this.num_reserva = num_reserva;
	}


	public Date getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public Date getFecha_fin() {
		return fecha_fin;
	}


	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}


	public String getId_oferta() {
		return id_oferta;
	}


	public void setId_oferta(String id_oferta) {
		this.id_oferta = id_oferta;
	}


	public String getDoc_cliente() {
		return doc_cliente;
	}


	public void setDoc_cliente(String doc_cliente) {
		this.doc_cliente = doc_cliente;
	}


	public String getTipo_doc_cliente() {
		return tipo_doc_cliente;
	}


	public void setTipo_doc_cliente(String tipo_doc_cliente) {
		this.tipo_doc_cliente = tipo_doc_cliente;
	}


	public Date getFecha_cancelacion() {
		return fecha_cancelacion;
	}


	public void setFecha_cancelacion(Date fecha_cancelacion) {
		this.fecha_cancelacion = fecha_cancelacion;
	}
	
	public String getCadenaFechaInicial(){
		String fecha =""+ fecha_inicio;
		
		String año = fecha.split(" ")[0].split("-")[0];
		String mes = fecha.split(" ")[0].split("-")[1];
		String dia = fecha.split(" ")[0].split("-")[2];
		

		return dia+"/"+mes+"/"+año;
	}
	
	public String getCadenaFechaFinal(){
		String fecha =""+ fecha_fin;
		
		String año = fecha.split(" ")[0].split("-")[0];
		String mes = fecha.split(" ")[0].split("-")[1];
		String dia = fecha.split(" ")[0].split("-")[2];

		return dia+"/"+mes+"/"+año;
	}
	
	public String getCadenaFechaCancelacion(){
		String fecha =""+ fecha_cancelacion;
		
		String año = fecha.split(" ")[0].split("-")[0];
		String mes = fecha.split(" ")[0].split("-")[1];
		String dia = fecha.split(" ")[0].split("-")[2];

		return dia+"/"+mes+"/"+año;
	}


	@Override
	public String toString() {
		return "Reserva [num_reserva=" + num_reserva + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin
				+ ", id_oferta=" + id_oferta + ", doc_cliente=" + doc_cliente + ", tipo_doc_cliente=" + tipo_doc_cliente
				+ ", fecha_cancelacion=" + fecha_cancelacion + "]";
	}
	
	


	



	
	
}
