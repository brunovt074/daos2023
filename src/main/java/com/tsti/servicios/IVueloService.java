package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tsti.dto.VueloDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

@Service
public interface IVueloService {
	
	public List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha);
	public Optional<Vuelo> findById(Long id);	
	public List<Vuelo> findByDestino(String destino);
	public List<Vuelo> findByFechaPartida(LocalDate fecha);
	public List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo);
	//public List<Vuelo> findByEstado(EstadoVuelo estadoVuelo);
	public Vuelo crearVuelo(VueloDTO vueloDTO);
	

}
