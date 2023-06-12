package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

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
	
	private final VueloDAO vueloDAO;
	
	public VueloServiceImpl(VueloDAO vueloDAO) {
		this.vueloDAO = vueloDAO;
	}
	
	public List<Vuelo> buscarVuelosPorDestinoYFechaPartida(String destino, LocalDate fecha) {
        return vueloDAO.findByDestinoAndFechaPartida(destino, fecha);
    }
	
	public List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo) {
        return vueloDAO.findByTipoVuelo(tipoVuelo);
    }

	public List<Vuelo> getAll() {
		return (List<Vuelo>) vueloDAO.findAll();
	}

}
