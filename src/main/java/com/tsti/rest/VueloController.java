package com.tsti.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.VueloDisponibleDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.servicios.VueloServiceImpl;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
	
	@Autowired
	private VueloServiceImpl vueloService;
	
	//Request recibido desde el momento que se busca un vuelo.
	@GetMapping
	public ResponseEntity<List<VueloDisponibleDTO>> obtenerVuelosDisponibles(@RequestParam("destino")String destino, 
		  													@RequestParam("fecha") 
  															@DateTimeFormat(pattern = "dd-MM-yyyy")
		  													LocalDate fecha){
		
		//Usamos el VueloService para buscar en la BDD los vuelos por destino y fecha. 
		List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fecha);
		
		//Instanciamos el DTO para nuestra respuesta
		List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();
		
		//Iteramos sobre el array de vuelos con esa fecha y destino.
		for(Vuelo vuelo : vuelos) {
			
			VueloDisponibleDTO vueloDTO = new VueloDisponibleDTO(vuelo);
			
			vueloDTO.setNroVuelo(vuelo.getNroVuelo());
			vueloDTO.setAerolinea(vuelo.getAerolinea());
			vueloDTO.setDestino(vuelo.getDestino());
			vueloDTO.setFechaPartida(vuelo.getFechaPartida());
			vueloDTO.setHoraPartida(vuelo.getHoraPartida());
			
			
			vuelosDTO.add(vueloDTO);
		}
		
		//Se crea la response de acuerdo a si el arraylist esta vacio o no.
		if(vuelos.isEmpty()) {
			return ResponseEntity.noContent().build(); //ERROR 204 No Content
		}
		else {
			return ResponseEntity.ok(vuelosDTO); //Mensaje 200 OK.
		}		
  
  }
	 
}
