/**
 * 
 */
package com.tsti.faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.ClienteDAO;
import com.tsti.dao.DomicilioDAO;
import com.tsti.dao.VueloDAO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Clientes;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.entidades.Ciudad;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 */
@Component
public class VueloFactory {
	
	private Faker faker;	
	private CiudadFactory ciudadFactory;
	private ClienteFactory clienteFactory;
	private Ciudad origen;
	private Ciudad destino;
	
	@Autowired
	private VueloDAO vueloDAO;
	@Autowired
	private ClienteDAO clienteDAO;	
	@Autowired
	private CiudadDAO ciudadDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	//private EstadoVuelo estadoVuelo;
	//private TipoVuelo tipoVuelo;
	
	@Autowired
	public VueloFactory() {
		this.faker = new Faker(new Locale("es") );
		this.ciudadFactory = new CiudadFactory();
		this.clienteFactory = new ClienteFactory();
		
	}
	
	public void crearVueloPorDTO(){
		
	}	
	
	public void crearVueloOrigenLocal(int nroPasajeros, EstadoVuelo estadoVuelo, TipoVuelo tipoVuelo) {
		Vuelo vuelo = new Vuelo();			
		
		if(!ciudadDAO.existsByCodAeropuerto("SAAV")){
			origen = ciudadFactory.getCiudadSauceViejo();
		
		}else {
			
			origen = ciudadDAO.findByCodAeropuertoAndNombreCiudad("SAAV", "Sauce Viejo");
					
		}
		
		if(tipoVuelo.equals(TipoVuelo.NACIONAL)) {
						
			destino = ciudadFactory.getCiudadArgentina();
			vuelo.setPrecioNeto(GenerarPrecioNeto.generarPrecioNetoPesos());
		
		}else {
			destino = ciudadFactory.getCiudadAleatoria();
			vuelo.setPrecioNeto(GenerarPrecioNeto.generarPrecioNetoDolares());
		}
		
		ciudadDAO.save(origen);
		ciudadDAO.save(destino);
		
		
		
		//Metodo mejorado para obtener fecha y hora en un array y evitar repetir codigo.
		//1er parametro dias de partida hacia adelante, 2do: horas +
		Object[] fechaHoraPartida = fechaHora(10,24);
				
		vuelo.setAerolinea(faker.aviation().airline());
		vuelo.setAvion(faker.aviation().airplane());
		vuelo.setNroFilas(6);
		vuelo.setNroColumnas(15);
		//Clientes [][] plazas  = new Clientes[vuelo.getNroFilas()][vuelo.getNroColumnas()];
		//vuelo.setPlazas(plazas); 
		vuelo.setOrigen(origen);
		vuelo.setDestino(destino);
		vuelo.setTipoVuelo();
		vuelo.setEstadoVuelo(estadoVuelo);
		vuelo.setFechaPartida((LocalDate) fechaHoraPartida[0]);
		vuelo.setHoraPartida((LocalTime) fechaHoraPartida[1]);
		
		
		cargarPasajeros(vuelo, clienteDAO, ciudadDAO, domicilioDAO, nroPasajeros);
		
		System.out.println(vuelo.getPasajeros().toString());
		System.out.println(vuelo.toString());				
		
		vueloDAO.save(vuelo);
	}
	

	private Object[] fechaHora(int diasFechaPartida, int horasPartida) {
		
		Object[] fechaHoraPartida = new Object[2];		
		
		//Obtener fecha.
		LocalDateTime fechaPartidaTimestamp = (faker.date().future(diasFechaPartida,0,TimeUnit.DAYS)).toLocalDateTime();
		LocalDate fechaPartida = fechaPartidaTimestamp.toLocalDate();
		//Obtener hora.
		LocalDateTime horaPartidaTimestamp = (faker.date().future(horasPartida,0,TimeUnit.HOURS)).toLocalDateTime();
		LocalTime horaPartida = horaPartidaTimestamp.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
		
		fechaHoraPartida[0] = fechaPartida; //Elemento de tipo LocalDate
		fechaHoraPartida[1] = horaPartida; //Elemento de tipo LocalTime
		
		return fechaHoraPartida;
		
	}
	
	private void cargarPasajeros(Vuelo vuelo, ClienteDAO clienteDAO, CiudadDAO ciudadDAO, DomicilioDAO domicilioDAO, int nroPasajeros) {
		int asientosDisponibles = vuelo.getNroAsientos();
				
		if(nroPasajeros <= asientosDisponibles) {
			
			if(vuelo.getTipoVuelo().equals(TipoVuelo.NACIONAL)) {
				for (int i = 0; i < nroPasajeros; i++) {
					Clientes pasajero = clienteFactory.getUnPasajeroNacional(ciudadDAO, domicilioDAO);
					vuelo.addPasajero(pasajero);
					asientosDisponibles--;
					
					System.out.println(pasajero.toString());
					System.out.println(vuelo.getPasajeros().toString());
					System.out.println(vuelo.toString());		
				}
			}else{
				for (int i = 0; i < nroPasajeros; i++) {
					Clientes pasajero = clienteFactory.getUnPasajeroInternacional(ciudadDAO, domicilioDAO);
					vuelo.addPasajero(pasajero);
					asientosDisponibles--;
					
					System.out.println(pasajero.toString());
					System.out.println(vuelo.getPasajeros().toString());
					System.out.println(vuelo.toString());	
				}
			}
						
		}		
		
	}

}
