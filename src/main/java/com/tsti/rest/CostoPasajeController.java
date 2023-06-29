package com.tsti.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.PasajeDTO;
import com.tsti.servicios.VueloServiceImpl;
import com.tsti.servicios.PasajeServiceImpl;
/**
 * @author Bruno
 *
 *Clase encargada de comunicarse con la api-dolar y cotizacion para 
 *calcular el precio final del pasaje.
 *
 */
@RestController
public class CostoPasajeController {
	
	private final PasajeServiceImpl pasajeService;
	
	@Autowired
	public CostoPasajeController(PasajeServiceImpl pasajeService, VueloServiceImpl vueloService) {
		this.pasajeService = pasajeService;
		//this.vueloService = vueloService;
		
	}
	
	
	@GetMapping("/pasaje/costo")
	public ResponseEntity<EntityModel<PasajeDTO>> getCostoPasaje
								(@RequestParam("nro-vuelo") Long nroVuelo, 
									@RequestParam("dni") Long dni){
		if(nroVuelo == null || dni == null ){
			
			return ResponseEntity.badRequest().build();
			
		}
		
		//PasajeServiceImpl pasajeService = new PasajeServiceImpl();
		PasajeDTO pasajeDTO = pasajeService.getCostoPasaje(nroVuelo,dni);
		
		if(pasajeDTO.getNroVuelo() != null) {
			//Se crea el objeto entidad y se aniade el DTO
			EntityModel<PasajeDTO> pasajeEntityModel =  EntityModel.of(pasajeDTO)
													.add(AppLinks.getLinksCostoPasaje(nroVuelo, dni))
													.add(AppLinks.getVueloPorId(nroVuelo))
													.add(AppLinks.showVuelosLink());														
					
		
			return ResponseEntity.ok(pasajeEntityModel);			
		
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
			
			
			
		
	}
}

