package com.tsti.rest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.PasajeDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.servicios.TasaServiceImpl;
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
	private TasaServiceImpl tasaService;
	private CotizacionServiceImpl cotizacionService;
	private Vuelo vuelo;
	private VueloServiceImpl vueloService;
	
	@Autowired
	public CostoPasajeController(VueloServiceImpl vueloServiceImpl, TasaServiceImpl tasaService, CotizacionServiceImpl cotizacionService) {
		this.vueloService = vueloServiceImpl;
		this.tasaService = tasaService;
		this.cotizacionService = cotizacionService;
	}
	
	@GetMapping("/pasaje/costo")
	public ResponseEntity<EntityModel<PasajeDTO>> getCostoPasaje
								(@RequestParam("nro-vuelo") Long nroVuelo, 
											@RequestParam("dni") Long dni){
		
		//Creamos el DTO en base al numero que nos pasaron
		PasajeDTO pasajeDTO = new PasajeDTO();
		
		pasajeDTO.setNroVuelo(nroVuelo);
		pasajeDTO.setDni(dni);
		
		//chequeamos que exista en la BD
		Optional<Vuelo> vueloOptional = vueloService.findById(
														pasajeDTO.getNroVuelo());
		
		if(vueloOptional.isPresent()){
			
			vuelo = vueloOptional.get();			
			
			//Se obtiene el precio neto del pasajeDTO y se deduce tasa segun tipo de vuelo.
			precioNeto = vuelo.getPrecioNeto();			
			tasa = tasaService.getTasa(vuelo.getTipoVuelo());			
			precioFinal = precioNeto.add(tasa);
			
			if(vuelo.getTipoVuelo() == TipoVuelo.INTERNACIONAL){
			
				//multiplicamos y redondeamos a un numero de dos cifras despues de la coma
				precioFinal =  precioFinal.multiply(
						cotizacionService.getCotizacionDolarOficial())
									.setScale(2, RoundingMode.HALF_DOWN);			
				
			}
			
			//setting del DTO
			pasajeDTO.setPrecioNeto(precioNeto);
			System.out.println("Precio Inicial: " + precioNeto);
			pasajeDTO.setTasa(tasa);
			System.out.println("tasa: " + tasa);
			pasajeDTO.setTipoVuelo(vuelo.getTipoVuelo());
			pasajeDTO.setPrecioFinal(precioFinal);
			System.out.println("Precio Final: " + precioFinal);		
			
			
			//Se crea el objeto entidad y se aniade el DTO
			EntityModel<PasajeDTO> pasajeEntityModel = 
												EntityModel.of(pasajeDTO);
				
			//creando el link a busqueda de vuelo
			List <Link> links = AppLinks.getLinksVuelos(2, vuelo.getDestino().getNombreCiudad(),
														vuelo.getFechaPartida());
			
			links.addAll(AppLinks.getLinksCostoPasaje(vuelo.getNroVuelo(), dni));
//			Link linkSelf = Link.of("/pasaje/costo", "self").withRel("pasaje-costo");
//			Link linkDolar = Link.of("https://www.dolarsi.com/api/api.php?type=valoresprincipales").withRel("api-dolar");
//			
//			links.add(linkSelf);
//			links.add(linkDolar);
//			
			
			for (Link link : links) {
			    pasajeEntityModel.add(link);
			}
			//			Link link = WebMvcLinkBuilder
//					.linkTo(methodOn(VueloController.class)
//							.getVuelosByDestinoAndFecha(vuelo.getDestino().getNombreCiudad(), vuelo.getFechaPartida()))
//					.withRel("getVueloPorFechaYDestino");
//				
			//pasajeEntityModel.add(links);
			
//			//creando link a cliente por dni
//			link = WebMvcLinkBuilder
//					.linkTo(methodOn(ClienteController.class)
//							.getClientePorDni(long dni))
//					.withRel("getClientePorDni");
//			pasajeEntityModel.add(link);
			
			//creando link a api dolar.
			
			
			
			
			//pasajeEntityModel.add(linkDolar);
		
			return ResponseEntity.ok(pasajeEntityModel);
		
		} else {
			
			return ResponseEntity.noContent().build();
			
		}			
	}
}
