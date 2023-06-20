package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

public interface IVueloService {
	
	Optional<Vuelo> findById(Long id);
	List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha);
	List<Vuelo> findByDestino(String destino);
	List<Vuelo> findByFechaPartida(LocalDate fecha);
	List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo);
	

}
