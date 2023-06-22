package com.tsti.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.VueloDAO;
import com.tsti.dto.VueloDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.entidades.Ciudad;
import com.tsti.faker.CiudadFactory;
/**
 * @author Bruno
 * Sugiero añadir el prefijo "I" Delante de las interfaces para mejorar 
 * la legibilidad en el arbol del proyecto
 * */
@Service
public class VueloServiceImpl implements IVueloService {
	@Autowired
	private final VueloDAO vueloDAO;	
	@Autowired
	private final CiudadDAO ciudadDAO;
	
	public VueloServiceImpl(VueloDAO vueloDAO, CiudadDAO ciudadDAO) {
		this.vueloDAO = vueloDAO;	
		this.ciudadDAO = ciudadDAO;
	}
	
	public Vuelo crearVuelo(VueloDTO vueloDTO) {
		
		Vuelo vuelo = new Vuelo();
		Ciudad origen;
		
//		if(ciudadDAO.existsByCodAeropuerto(CiudadFactory.getCiudadSauceViejo().getCodAeropuerto())){
//			
//			origen = ciudadDAO.findByCodAeropuertoAndNombreCiudad(CiudadFactory.getCiudadSauceViejo().getCodAeropuerto()
//					, CiudadFactory.getCiudadSauceViejo().getNombreCiudad());
//		
//		}
//		else {
			origen = CiudadFactory.getCiudadSauceViejo();
		//}
		
		Ciudad destino = new Ciudad();
		
		
		
		//Lo ideal seria usar nombre ciudad y CP pero a fines practicos se utilizand estos parametros.
//		if(vueloDTO.getDestino().getId() != null &&  ciudadDAO.existsById(vueloDTO.getDestino().getId()) 
//			|| ciudadDAO.existsByNombreCiudadAndProvinciaAndPais(vueloDTO.getDestino().getNombreCiudad()
//					,vueloDTO.getDestino().getProvincia()
//					,vueloDTO.getDestino().getPais())){
//			
//			destino = vueloDTO.getDestino();	
//			
//		}
//		
//		else {
			
			destino.setCodAeropuerto(vueloDTO.getDestino().getCodAeropuerto());
			destino.setCodPostal(vueloDTO.getDestino().getCodPostal());
			destino.setNombreCiudad(vueloDTO.getDestino().getNombreCiudad());
			destino.setProvincia(vueloDTO.getDestino().getProvincia());
			destino.setPais(vueloDTO.getDestino().getPais());
		//}
		
		ciudadDAO.save(origen);	
		ciudadDAO.save(destino);
		
		
				
		vuelo.setAerolinea(vueloDTO.getAerolinea());
		vuelo.setFechaPartida(vueloDTO.getFechaPartida());
		vuelo.setHoraPartida(vueloDTO.getHoraPartida());
		vuelo.setNroFilas(vueloDTO.getNroFilas());
		vuelo.setNroColumnas(vueloDTO.getNroColumnas());
		vuelo.setOrigen(origen);
		vuelo.setDestino(destino);
		vuelo.setTipoVuelo();
		
		vuelo.setEstadoVuelo(EstadoVuelo.REGISTRADO);
		
		vueloDAO.save(vuelo);
		
		System.out.println(vuelo);
		return vuelo;
	}
	
	public void cancelarVuelo(Vuelo vuelo){		
				
		vuelo.setEstadoVuelo(EstadoVuelo.CANCELADO);
		vueloDAO.save(vuelo);
		
	}
	
	public void reprogramarVuelo(Vuelo vuelo, VueloDTO vueloDTO){
		// Actualizar fecha y hora del vuelo
        vuelo.setFechaPartida(vueloDTO.getFechaPartida());
        vuelo.setHoraPartida(vueloDTO.getHoraPartida());
        
        // Cambiar estado a reprogramado
        vuelo.setEstadoVuelo(EstadoVuelo.REPROGRAMADO);
        vueloDAO.save(vuelo);
        
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
