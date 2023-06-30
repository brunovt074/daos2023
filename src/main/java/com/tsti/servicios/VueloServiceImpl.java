package tsti.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import tsti.dao.VueloDAO;
import tsti.entidades.Vuelo;
import tsti.entidades.Vuelo.TipoVuelo;
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

	public List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha) {
        return vueloDAO.findByDestinoAndFechaPartida(destino, fecha);
    }
	
	public List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo) {
        return vueloDAO.findByTipoVuelo(tipoVuelo);
    }

	public List<Vuelo> getAll() {
		return (List<Vuelo>) vueloDAO.findAll();
	}

}
