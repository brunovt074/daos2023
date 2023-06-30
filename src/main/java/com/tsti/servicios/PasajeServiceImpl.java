package com.tsti.servicios;

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
	public Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero, Integer nroAsiento) {
		/*
		 * METODO DISEÑADO PARA SER CONTENIDO EN UN BLOQUE TRY-CATCH A LA HORA 
		 * DE LLAMARLO, ESTO PARA MANEJO DE ERRORES DE UNA FORMA MAS SENCILLA. :D
		*/
		
		//VALIDAMOS QUE EXISTAN EL CLIENTE Y EL VUELO, VIENDO SI ESTAN ALMACENADOS EN LA BASE DE DATOS
		if (!this.existeCliente(pasajero)) {
	        throw new ValidacionFallidaEnPasajeException("El cliente con DNI " + pasajero.getDni() + " no existe.");
	    }
	    if (!this.existeVuelo(vuelo)) {
	        throw new ValidacionFallidaEnPasajeException("El vuelo con número " + vuelo.getNroVuelo() + " no existe.");
	    }
	    
	    //VALIDACIONES DEL TP
	    	//SOBRE PASAJERO
	    if(vuelo.getTipoVuelo()== Vuelo.TipoVuelo.NACIONAL) {
	    	if(!pasajero.tieneDatosBasicos())
	    		throw new ValidacionFallidaEnPasajeException("El cliente no cuenta con los datos basicos!");
	    }else if(vuelo.getTipoVuelo()== Vuelo.TipoVuelo.INTERNACIONAL) {
	    	if(!pasajero.tieneDatosBasicos())
	    		throw new ValidacionFallidaEnPasajeException("El cliente no cuenta con los datos basicos!");
	    	if(!pasajero.tienePasaporte())
	    		throw new ValidacionFallidaEnPasajeException("El cliente no cuenta con pasaporte!");	
	    }
	    
	    	//SOBRE VUELO
	    		//EL VUELO ES UN VUELO FUTURO
	    if(vuelo.getEstadoVuelo() == Vuelo.EstadoVuelo.CANCELADO) {
	    	throw new ValidacionFallidaEnPasajeException("El vuelo ingresado no es un vuelo futuro, fue cancelado!");
	    }else if(vuelo.getEstadoVuelo() == Vuelo.EstadoVuelo.TERMINADO) {
	    	throw new ValidacionFallidaEnPasajeException("El vuelo ingresado no es un vuelo futuro, fue finalizado!");
	    }
	    
	    		//EL ASIENTO ELEJIDO ESTÁ DISPONIBLE
	    if(vuelo.getNroAsientos() < nroAsiento) {
	    	throw new ValidacionFallidaEnPasajeException("Ese asiento no existe para ese vuelo!");
	    }
	    if(vuelo.asientoOcupado(nroAsiento)) {
	    	throw new ValidacionFallidaEnPasajeException("Ese asiento esta ocupado!");
	    }

	    //POSTING
	    Pasaje pasaje = new Pasaje(vuelo, pasajero, nroAsiento);
	    
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