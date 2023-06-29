package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tsti.dto.VueloDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.presentacion.CrearVueloForm;
import com.tsti.presentacion.EditarVueloForm;

@Service
public interface IVueloService{
	
	public VueloDTO crearVuelo(CrearVueloForm vueloForm);
	public VueloDTO cancelarVuelo(Long nroVuelo);
	public VueloDTO reprogramarVuelo(Long nroVuelo, EditarVueloForm vueloForm);
	public Optional<Vuelo> findById(Long nroVuelo);
	public List<VueloDTO> findByDestinoAndFechaPartida(String destino, LocalDate fecha);		
	public List<VueloDTO> findByDestino(String destino);
	public List<VueloDTO> findByFechaPartida(LocalDate fecha);
	public List<Vuelo> obtenerVuelosPorTipo(TipoVuelo tipoVuelo);
	public List<VueloDTO> getAll();
	public List<VueloDTO> findAllByEstadoVuelo(EstadoVuelo estadoVuelo);	

}
