package com.tsti.servicios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tsti.dao.ClienteDAO;
import com.tsti.dao.PasajeDAO;
import com.tsti.dao.VueloDAO;
import com.tsti.entidades.Clientes;
import com.tsti.entidades.Pasaje;
import com.tsti.entidades.Vuelo;
import com.tsti.excepcion.ValidacionFallidaEnPasajeException;

/**
 * @author JOA
 *
 */
@Service
public class PasajeServiceImpl implements IPasajeService {
	private final PasajeDAO pasajeDAO;
	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private VueloDAO vueloDAO;

    public PasajeServiceImpl(PasajeDAO pasajeDAO) {
        this.pasajeDAO = pasajeDAO;
    }
	@Override
	public Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero) {
		
		//METODO DISEÑADO PARA SER CONTENIDO EN UN BLOQUE TRY-CATCH A LA HORA DE LLAMARLO, ESTO PARA MANEJO DE ERRORES. :D
		
		//VALIDAMOS QUE EXISTAN EL CLIENTE Y EL VUELO, VIENDO SI ESTAN ALMACENADOS EN LA BASE DE DATOS
		if (!this.existeCliente(pasajero)) {
	        throw new ValidacionFallidaEnPasajeException("El cliente con DNI " + pasajero.getDni() + " no existe.");
	    }
	    if (!this.existeVuelo(vuelo)) {
	        throw new ValidacionFallidaEnPasajeException("El vuelo con número " + vuelo.getNroVuelo() + " no existe.");
	    }
	    System.out.println(this.existeCliente(pasajero));
	    System.out.println(this.existeVuelo(vuelo));
	    
	    //POSTING
	    Pasaje pasaje = new Pasaje(vuelo, pasajero);
	    System.out.println(pasaje.toString());
	    System.out.println(pasaje.getVuelo().toString());
		System.out.println(pasaje.getPasajero().toString());
	    return pasajeDAO.save(pasaje);
	}
	
    public Boolean existeCliente(Clientes cliente) {
    	return clienteDAO.findById(cliente.getId()).isPresent();
    }
    public Boolean existeVuelo(Vuelo vuelo) {
    	return vueloDAO.findById(vuelo.getNroVuelo()).isPresent();
    }
	@Override
	public Optional<Pasaje> findById(Long id){
		
		return pasajeDAO.findById(id);
	
	}
    
}