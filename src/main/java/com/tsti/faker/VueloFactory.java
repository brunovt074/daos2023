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
import com.tsti.entidades.Vuelo.EstadoVuelo;
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
	@Autowired
	private VueloDAO vueloDAO;
	@Autowired
	private ClienteDAO clienteDAO;	
	@Autowired
	private CiudadDAO ciudadDAO;
		
	public VueloFactory() {
		this.faker = new Faker(new Locale("es") );
		this.ciudadFactory = new CiudadFactory();
	}
	
	public void crearVueloNacionalOrigenLocal(/*int nroPasajeros, EstadoVuelo estado*/) {
		
		Vuelo vuelo = new Vuelo();
		
		Ciudad origen = ciudadFactory.getCiudadSauceViejo();
		Ciudad destino = ciudadFactory.getCiudadArgentina();
		ciudadDAO.save(origen);
		ciudadDAO.save(destino);
		
		//Obtener fecha.
		LocalDateTime fechaPartidaTimestamp = (faker.date().future(60,0,TimeUnit.DAYS)).toLocalDateTime();
		LocalDate fechaPartida = fechaPartidaTimestamp.toLocalDate();
		//Obtener hora.
		LocalDateTime horaPartidaTimestamp = (faker.date().future(12,0,TimeUnit.HOURS)).toLocalDateTime();
		LocalTime horaPartida = horaPartidaTimestamp.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
		
		vuelo.setAerolinea(faker.aviation().airline());
		vuelo.setAvion(faker.aviation().airplane());
		vuelo.setOrigen(origen);
		vuelo.setDestino(destino);
		vuelo.setTipoVuelo();
		vuelo.setEstadoVuelo(EstadoVuelo.REGISTRADO);
		vuelo.setFechaPartida(fechaPartida);
		vuelo.setHoraPartida(horaPartida);
		//logica de clientes
		
		System.out.println("Fecha de partida: " + vuelo.getFechaPartida().toString());
		System.out.println("Fecha de partida: " + vuelo.getHoraPartida().toString());		
		
	}

}
