package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;

import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

public interface IVueloService {

	List<Vuelo> buscarVuelosPorDestinoYFechaPartida(String destino, LocalDate fecha);
	List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo);
	

}
