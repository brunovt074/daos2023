package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.VueloDAO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
/**
 * @author Bruno
 * Sugiero añadir el prefijo "I" Delante de las interfaces para mejorar 
 * la legibilidad en el arbol del proyecto
 * */
@Service
public class VueloServiceImpl implements IVueloService {
	@Autowired
	private final VueloDAO vueloDAO;	
	
	public VueloServiceImpl(VueloDAO vueloDAO, CiudadDAO ciudadDAO) {
		this.vueloDAO = vueloDAO;		
	}	
	
	public Optional<Vuelo> findById(Long id){
		return vueloDAO.findById(id);
	}
	
	public List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha) {
        return vueloDAO.findByDestinoAndFechaPartida(destino, fecha);
    }
	
	@Override
	public List<Vuelo> findByDestino(String nombreDestino) {
			
		return vueloDAO.findByDestino(nombreDestino);
		
	}
	public List<Vuelo> findByFechaPartida(LocalDate fecha) {
        return vueloDAO.findByFechaPartida(fecha);
    }
	
	public List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo) {
        return vueloDAO.findByTipoVuelo(tipoVuelo);
    }

	public List<Vuelo> getAll() {
		return (List<Vuelo>) vueloDAO.findAll();
	}	
	

}
