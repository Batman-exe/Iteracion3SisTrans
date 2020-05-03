package uniandes.isis2304.parranderos.negocio;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohAndes;




public class AlohAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(AlohAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohAndes pA;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public AlohAndes ()
	{
		pA = PersistenciaAlohAndes.getInstance ();
	}

	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public AlohAndes (JsonObject tableConfig)
	{
		pA = PersistenciaAlohAndes.getInstance (tableConfig);
	}


	public List<VOReserva> RF7(String tipoOferta,Long numReserva, String fechaI, String fechaF, Long docCliente, 
			String tipoDoc, String fechaCancelacion, int cantidad, String[] adicionales) throws Exception
	{
		List<VOReserva> res = new LinkedList<VOReserva>();

		List<Oferta> libres = ofertasLibres(tipoOferta, fechaI, fechaF);

		if(libres.size()>= cantidad){

			int cont = 0;
			Iterator<Oferta> it = libres.iterator();
			while(cont < cantidad && it.hasNext())
			{
				Oferta o = it.next();
				if(ofertaTieneAdicionales(Long.parseLong(o.getId()), adicionales)){	

					log.info ("Adicionando reserva " + numReserva);
					Reserva nueva = adicionarReserva(numReserva, fechaI, fechaF, Long.parseLong(o.getId()), docCliente, tipoDoc, fechaCancelacion);
					log.info ("Adicionando reserva: " + nueva);
					res.add(nueva);
					cont++;

				}
				else
				{
					throw new Exception("No hay suficientes ofertan que cumplan con los adicionales esperados");
				}

			}

		}
		else
		{
			throw new Exception("No hay suficientes ofertas libres");
		}



		return res;
	}

	public void RF8(Long numReserva){
		pA.eliminarReservaPorNumero(numReserva);
	}

	public long RF9(Long idOferta){
		long res= 0;
		List<Reserva> reservas = pA.darReservasOferta(idOferta);
		if(reservas.size() > 0){

			Oferta o = pA.darOfertaPorId(idOferta);

			for(Reserva r: reservas){
				String fechaI = r.getCadenaFechaInicial();
				String fechaF = r.getCadenaFechaFinal();


				Oferta primera = primeraOfertaDisponible(o.getTipo_oferta(), fechaI, fechaF);

				if(primera != null){
					Long nuevoNumReserva = Long.parseLong(pA.darUltimaReserva().getNum_reserva()) + 1;

					Reserva nueva = adicionarReserva(nuevoNumReserva, fechaI, fechaF, Long.parseLong(primera.getId()),
							Long.parseLong(r.getDoc_cliente()), r.getTipo_doc_cliente(), r.getCadenaFechaCancelacion());

					if(nueva != null){
						res += eliminarReserva(Long.parseLong(r.getNum_reserva()), idOferta);
						res += 1;
					}
				}

			}
			
			res += pA.cambiarOfertaDisponible(idOferta, false);
			
			
		}

		return res;
	}

	public long RF10(Long idOferta){

		return pA.cambiarOfertaDisponible(idOferta, true);
	}

	private List<Oferta> ofertasLibres( String tipoOferta, String fechaI, String fechaF){
		List<Oferta> res = new LinkedList<Oferta>();
		List<Oferta> ofertas = pA.darOfertasPorTipo(tipoOferta);
		for(Oferta o: ofertas){

			List<Reserva> reservasEnfecha = pA.darReservasOfertaEnFecha(Long.parseLong(o.getId()), fechaI, fechaF);
			if(reservasEnfecha.size()== 0){

				res.add(o);
			}
		}

		return res;
	}

	private Oferta primeraOfertaDisponible(String tipoOferta,String fechaI, String fechaF){

		Iterator<Oferta> it = ofertasLibres(tipoOferta, fechaI, fechaF).iterator();
		return it.next();
	}



	private boolean ofertaTieneAdicionales(Long idOferta, String[]adicionales){

		boolean res =  false;
		if(adicionales.length == 1 && adicionales[0].equals(""))
			res = true;
		for(String a: adicionales){

			a = a.trim();
			Adicional adi= pA.darAdicionalesPorOfertaYNombre(idOferta, a);
			if(adi != null){
				System.out.println(adi.toString());
				res = true;
			}

		}

		return res;

	}

	/**
	 * Cierra la conexi�n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pA.cerrarUnidadPersistencia ();
	}

	public Cliente adicionarCliente (Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
		log.info ("Adicionando Cliente " + nombre);
		Cliente cliente = pA.adicionarCliente(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
		log.info ("Adicionando Cliente: " + cliente);
		return cliente;
	}


	public PersonaNatural adicionarPersonaNatural(Long numeroDocumento, String tipoDocumento, String nombre, String nacionalidad, 
			String tipo, String userName, String contrasena)
	{
		log.info ("Adicionando persona natural " + nombre);
		PersonaNatural pN = pA.adicionarPersonaNatural(numeroDocumento, tipoDocumento, nombre, nacionalidad, tipo, userName, contrasena);
		log.info ("Adicionando persona natural: " + pN);
		return pN;
	}


	public PersonaJuridica adicionarPersonaJuridica (Long nit, String nombre, String tipo, String horaApertura, String horaCierre,
			String userName, String contrasena)
	{
		log.info ("Adicionando pj " + nombre);
		PersonaJuridica pj = pA.adicionarPersonaJuridica(nit, nombre, tipo, horaApertura, horaCierre, userName, contrasena);
		log.info ("Adicionando pj: " + pj);
		return pj;
	}

	public Oferta adicionarOferta(Long id, String tipoOferta, Boolean disponible, Integer precio)
	{
		log.info ("Adicionando oferta " + id);
		Oferta o = pA.adicionarOferta(id, tipoOferta, disponible, precio);
		log.info ("Adicionando oferta: " + o);
		return o;
	}

	public Adicional adicionarAdicional(Long id_oferta, String nombre, Integer precio){
		log.info ("Adicionando Adicional " + id_oferta +","+ nombre);
		Adicional a = pA.adicionarAdicional(id_oferta, nombre, precio);
		log.info ("Adicionando adicional: " + a);
		return a;
	}

	public Reserva adicionarReserva(Long numeroReserva, String fechaInicio, String fechaFin, Long idOferta, 
			Long docCliente, String tipoDoc, String fechaCandelacion)
	{
		log.info ("Adicionando reserva " + numeroReserva);
		Reserva r = pA.adicionarReservas(numeroReserva, fechaInicio, fechaFin, idOferta, docCliente, tipoDoc, fechaCandelacion);
		log.info ("Adicionando reserva: " +r);
		return r;
	}

	public long eliminarReserva(Long numReserva, Long idOferta)
	{
		log.info ("Eliminando reserva por id: " + numReserva);
		long resp = pA.eliminarReserva(numReserva, idOferta);
		log.info ("Eliminando reserva: " + resp);
		return resp;
	}

	public long eliminarReservaNumReserva(Long numReserva)
	{
		log.info ("Eliminando reserva por id: " + numReserva);
		long resp = pA.eliminarReservaPorNumero(numReserva);
		log.info ("Eliminando reserva: " + resp);
		return resp;
	}

	public Contrato adicionarContrato(Long numContrato, Integer duracion, Long numReserva)
	{
		log.info ("Adicionando contrato " + numContrato);
		Contrato c = pA.adicionarContrato(numContrato, duracion, numReserva);
		log.info ("Adicionando contrato: " +c);
		return c;
	}

	public OfertaApartamento adicionarOfertaApartamento(Long id, String tipo, Boolean disponible, Integer precio, Integer capacidad, 
			String descripcion, Boolean esAmoblado, String ubicacion, Long documentoOp, String tipoDocOp, Long contrato)
	{
		log.info ("Adicionando oferta apartamento " + id);
		OfertaApartamento pN = pA.adicionarOfertaApartamento(id, tipo, disponible, precio, capacidad, descripcion, esAmoblado, ubicacion, documentoOp, tipoDocOp, contrato);
		log.info ("Adicionando oferta apartamento: " + pN);
		return pN;
	}

	public OfertaEsporadica adicionarOfertaEsporadica(Long id, String tipo, Boolean disponible, Integer precio, Integer duracion, 
			String descripcion, String descripcion_seguro, Integer num_habitaciones, String ubicacion)
	{
		log.info ("Adicionando oferta esporadica " + id);
		OfertaEsporadica oE = pA.adicionarOfertaEsporadica(id, tipo, disponible, precio, duracion, descripcion, descripcion_seguro, num_habitaciones, ubicacion);
		log.info ("Adicionando oferta esporadica: " + oE);
		return oE;
	}

	public OfertaHabitacionDiaria adicionarOfertaHabitacionDiaria(Long id, String tipo, Boolean disponible, Integer precio, Boolean esCompartida, String ubicacion, Long id_operador)
	{
		log.info ("Adicionando oferta esporadica " + id);
		OfertaHabitacionDiaria oHD = pA.adicionarOfertaHabitacionDiaria(id, tipo, disponible, precio, esCompartida, ubicacion, id_operador);
		log.info ("Adicionando oferta esporadica: " + oHD);
		return oHD;
	}

	public OfertaHabitacionMensual adicionarOfertaHabitacionMensual(Long id, String tipo, Boolean disponible, Integer precio, 
			Integer capacidad, String descripcion, String ubicacion, Long documentoOp, String tipoDocOp)
	{
		log.info ("Adicionando oferta habitacion mensual " + id);
		OfertaHabitacionMensual oHM = pA.adicionarOfertaHabitacionMensual(id, tipo, disponible, precio, capacidad, descripcion, ubicacion, documentoOp, tipoDocOp);
		log.info ("Adicionando oferta habitacion mensual: " +oHM);
		return oHM;
	}

	public OfertaViviendaUniversitaria adicionarOfertaViviendaUniversitaria(Long id, String tipo, Boolean disponible, 
			Integer precio, Integer capacidad, String duracion, Boolean esCompartida, Long id_operador)
	{
		log.info ("Adicionando oferta vivienda universitaria " + id);
		OfertaViviendaUniversitaria oVU = pA.adicionarOfertaViviendaUniversitaria(id, tipo, disponible, precio, capacidad, duracion, esCompartida, id_operador);
		log.info ("Adicionando oferta vivienda universitaria: " +oVU);
		return oVU;
	}

	public List<Adicional> darAdicionales()
	{
		log.info ("Consultando Tipos de bebida");
		List<Adicional> tiposBebida = pA.darAdicionales();	
		log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + " existentes");
		return tiposBebida;
	}
	/**
	 * Encuentra todos los tipos de bebida en Parranderos y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VOAdicional> darVOAdicionales()
	{
		log.info ("Generando los VO de Tipos de bebida");        
		List<VOAdicional> voTipos = new LinkedList<VOAdicional> ();
		for (Adicional tb : pA.darAdicionales())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
		return voTipos;
	}

	public List<Reserva> darReservas()
	{
		log.info ("Consultando Tipos de bebida");
		List<Reserva> tiposBebida = pA.darReservas();	
		log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + " existentes");
		return tiposBebida;
	}
	/**
	 * Encuentra todos los tipos de bebida en Parranderos y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VOReserva> darVOReservas()
	{
		log.info ("Generando los VO de Tipos de bebida");        
		List<VOReserva> voTipos = new LinkedList<VOReserva> ();
		for (Reserva tb : pA.darReservas())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
		return voTipos;
	}

	public List<Reserva> darReservasOfertaEnFecha(Long idReserva, String fechaI, String fechaF)
	{
		log.info ("Consultando Tipos de bebida");
		List<Reserva> tiposBebida = pA.darReservasOfertaEnFecha(idReserva, fechaI, fechaF);	
		log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + " existentes");
		return tiposBebida;
	}
	/**
	 * Encuentra todos los tipos de bebida en Parranderos y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param idReserva 
	 * @param fechaI 
	 * @param fechaF 
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VOReserva> darVOReservasOfertaEnFecha(Long idReserva, String fechaI, String fechaF)
	{
		log.info ("Generando los VO de Tipos de bebida");        
		List<VOReserva> voTipos = new LinkedList<VOReserva> ();
		for (Reserva tb : pA.darReservasOfertaEnFecha(idReserva, fechaI, fechaF))
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
		return voTipos;
	}


	/**
	 * Encuentra todos los tipos de bebida en Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos TipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<Oferta> darOfertas()
	{
		log.info ("Consultando Tipos de bebida");
		List<Oferta> tiposBebida = pA.darOfertas ();	
		log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + " existentes");
		return tiposBebida;
	}
	/**
	 * Encuentra todos los tipos de bebida en Parranderos y los devuelve como una lista de VOTipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @return Una lista de objetos VOTipoBebida con todos los tipos de bebida que conoce la aplicación, llenos con su información básica
	 */
	public List<VOOferta> darVOOfertas()
	{
		log.info ("Generando los VO de Tipos de bebida");        
		List<VOOferta> voTipos = new LinkedList<VOOferta> ();
		for (Oferta tb : pA.darOfertas())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
		return voTipos;
	}
	/**
	 * Encuentra el tipos de bebida en Parranderos con el nombre solicitado
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre de la bebida
	 * @return Un objeto TipoBebida con el tipos de bebida de ese nombre que conoce la aplicación, 
	 * lleno con su información básica
	 */
	public Oferta darOfertaPorId (Long id)
	{
		log.info ("Buscando Oferta por id: " + id);
		Oferta tb = pA.darOfertaPorId(id);
		return tb;
	}

	public List<OfertaApartamento> darOfertasApartamento()
	{
		log.info ("Consultando Tipos de bebida");
		List<OfertaApartamento> tiposBebida = pA.darOfertasApartamento();	
		log.info ("Consultando Tipos de bebida: " + tiposBebida.size() + " existentes");
		return tiposBebida;
	}

	public List<VOOfertaApartamento> darVOOfertasApartamento()
	{
		log.info ("Generando los VO de Tipos de bebida");        
		List<VOOfertaApartamento> voTipos = new LinkedList<VOOfertaApartamento> ();
		for (OfertaApartamento tb : pA.darOfertasApartamento())
		{
			voTipos.add (tb);
		}
		log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
		return voTipos;
	}
}
