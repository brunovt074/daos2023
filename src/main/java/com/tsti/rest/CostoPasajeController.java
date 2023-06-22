/**
 * 
 */
package com.tsti.rest;

import java.util.Collections;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.PasajeBaseDTO;
import com.tsti.dto.VueloDisponibleDTO;
import com.tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author Bruno
 *
 */
@RestController
public class CostoPasajeController {
	
	private double precioNeto;
	private double cotizacionDolar;
	private double tasaAeroportuaria;
	private double precioFinal;
	
	public CostoPasajeController(double precioFinal) {
		this.precioFinal = precioFinal;
	}
	
//	@GetMapping("/pasaje/costo")
//	public ResponseEntity<CollectionModel<EntityModel<VueloDisponibleDTO>>> obtenerCostoPasaje(PasajeBaseDTO pasajeDTO){
//		
//		precioNeto = pasajeDTO.getPrecioNeto();
//		
//		if(pasajeDTO.getTipoVuelo() == TipoVuelo.NACIONAL){
//			//llamar tasa aeroportuaria.
//			
//		} else {
//			//llamar tasa aeroportuaria + cotizacion del dolar.			
//		}
//		
//		
//		return ResponseEntity.ok(/*CollectionModel.of(Collections.singletonList(vueloEntityModel))*/);
//		
//		
//	}
	

}
