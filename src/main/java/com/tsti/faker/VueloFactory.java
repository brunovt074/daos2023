/**
 * 
 */
package com.tsti.faker;

import java.util.Locale;

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
	
	public void crearVueloNacionalVacio(/*int nroPasajeros, EstadoVuelo estado*/) {
		
		Vuelo vuelo = new Vuelo();
		
		Ciudad origen = ciudadFactory.getCiudadArgentina();
		Ciudad destino = ciudadFactory.getCiudadArgentina();
		ciudadDAO.save(origen);
		ciudadDAO.save(destino);
		
		vuelo.setAerolinea(faker.aviation().airline());
		vuelo.setAvion(faker.aviation().airplane());
		vuelo.setOrigen(origen);
		vuelo.setDestino(destino);
		vuelo.setTipoVuelo();
		vuelo.setEstadoVuelo(EstadoVuelo.REGISTRADO);
//		vuelo.setFechaPartida();
//		vuelo.setHoraPartida();
		//logica de clientes
		
		System.out.println(vuelo.getEstadoVuelo());		
		
	}

}
