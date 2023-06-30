package tsti.servicios;

import java.time.LocalDate;
import java.util.List;

import tsti.entidades.Vuelo;
import tsti.entidades.Vuelo.TipoVuelo;

public interface IVueloService {

	List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha);
	List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo);
	

}
