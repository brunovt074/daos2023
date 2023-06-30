package tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import tsti.entidades.Vuelo;
import tsti.entidades.Vuelo.TipoVuelo;
import org.springframework.stereotype.Service;

import tsti.dto.VueloDTO;
import tsti.entidades.Vuelo;
import tsti.entidades.Vuelo.EstadoVuelo;
import tsti.entidades.Vuelo.TipoVuelo;
import tsti.presentacion.CrearVueloForm;
import tsti.presentacion.EditarVueloForm;


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
