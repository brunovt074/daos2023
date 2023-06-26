/**
 * 
 */
package com.tsti.rest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.PasajeBaseDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.servicios.TasaAeroportuariaServiceImpl;
import com.tsti.servicios.VueloServiceImpl;
import com.tsti.servicios.CotizacionServiceImpl;

/**
 * @author Bruno
 *
 */
@RestController
public class CostoPasajeController {
	
	private BigDecimal precioNeto;
	private BigDecimal tasa;
	private BigDecimal precioFinal;
	private TasaAeroportuariaServiceImpl tasaService;
	private CotizacionServiceImpl cotizacionService;
	private Vuelo vuelo;
	private VueloServiceImpl vueloService;
	
	@Autowired
	public CostoPasajeController(VueloServiceImpl vueloServiceImpl, TasaAeroportuariaServiceImpl tasaService) {
		this.vueloService = vueloServiceImpl;
		this.tasaService = tasaService;
	}
	
	@GetMapping("/pasaje")
	public ResponseEntity<EntityModel<PasajeBaseDTO>> obtenerCostoPasaje
								(@RequestParam("nroVuelo") Long nroVuelo, 
											@RequestParam("dni") Long dni){
		
		
		PasajeBaseDTO pasajeDTO = new PasajeBaseDTO();
		
		pasajeDTO.setNroVuelo(nroVuelo);
		pasajeDTO.setDni(dni);
		 
		Optional<Vuelo> vueloOptional = vueloService.findById(pasajeDTO.getNroVuelo());
		
		if(vueloOptional.isPresent()){
			vuelo = vueloOptional.get();
		
			//Se crea el objeto entidad
			EntityModel<PasajeBaseDTO> pasajeEntityModel;
			
			//Se obtiene el precio neto del pasajeDTO y se deduce tasa segun tipo de vuelo.
			precioNeto = vuelo.getPrecioNeto();
			pasajeDTO.setPrecioNeto(precioNeto);
			System.out.println(precioNeto);
			tasa = tasaService.getTasa(pasajeDTO.getTipoVuelo());
			System.out.println(tasa);
			precioFinal = precioNeto.add(tasa);			
			
			if(pasajeDTO.getTipoVuelo() == TipoVuelo.INTERNACIONAL){
				
				precioFinal = precioNeto.multiply(cotizacionService.
						getCotizacionDolarOficial());
			
			}
					
			System.out.println(precioFinal);
			pasajeDTO.setTipoVuelo(vuelo.getTipoVuelo());
			pasajeDTO.setPrecioFinal(precioFinal);
			
			//se instancia el entity model de acuerdo a los cambios realizados al pasajeDTO
			pasajeEntityModel = EntityModel.of(pasajeDTO);
				
			//creando el link a busqueda de vuelo 
			Link link = WebMvcLinkBuilder
					.linkTo(methodOn(VueloController.class)
							.getVuelosByDestinoAndFecha(vuelo.getDestino().getNombreCiudad(), vuelo.getFechaPartida()))
					.withRel("getVueloPorFechaYDestino");
				
			pasajeEntityModel.add(link);
			
//			//creando link a cliente por dni
//			link = WebMvcLinkBuilder
//					.linkTo(methodOn(ClienteController.class)
//							.getClientePorDni(long dni))
//					.withRel("getClientePorDni");
//			pasajeEntityModel.add(link);
			
			//creando link a api dolar.			
			link = Link.of("https://www.dolarsi.com/api/api.php?type=valoresprincipales");
			pasajeEntityModel.add(link);
		
			return ResponseEntity.ok(pasajeEntityModel);
		
		} else {
			
			return ResponseEntity.noContent().build();
			
		}			
	}
}
