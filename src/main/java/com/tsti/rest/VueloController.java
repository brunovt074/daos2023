package com.tsti.rest;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.entidades.Vuelo;
import com.tsti.servicios.IVueloService;

@RestController
public class VueloController {

    private final IVueloService vueloService;

    @Autowired
    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }

//    @GetMapping("/vuelos")
//    public ResponseEntity<List<Vuelo>> obtenerVuelosPorFechaPartida(
//            @RequestParam("fecha") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaPartida) {
//        List<Vuelo> vuelos = vueloService.findByFechaPartida(fechaPartida);
//        if (vuelos.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(vuelos, HttpStatus.OK);
//        }
//    }
           
        
               
        // Método para encontrar un vuelo por ID
    @GetMapping("/vuelos/{id}")
    public ResponseEntity<Optional<Vuelo>> encontrarVueloPorId(@PathVariable Long id) {
        Optional<Vuelo> vuelo = vueloService.findById(id);

        if (vuelo.isPresent()) {
            return ResponseEntity.ok(vuelo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
}



//import java.time.LocalDate;
//import java.util.List;
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
////import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.tsti.dto.VueloDisponibleDTO;
//import com.tsti.entidades.Vuelo;
//import com.tsti.entidades.Ciudad;
//import com.tsti.servicios.VueloServiceImpl;
//
//@RestController
//@RequestMapping("/vuelos")
//public class VueloController {
//	@Autowired
//	private VueloServiceImpl vueloService;
//	
//	//Request recibido desde el momento que se busca un vuelo.
//
//	@GetMapping
//	public ResponseEntity<List<VueloDisponibleDTO>> obtenerVuelosDisponibles(/*@RequestParam("destino")String destino,*/ 
//		  													@RequestParam("fecha") 
//															@DateTimeFormat(pattern = "dd-MM-yyyy")
//		  													LocalDate fecha){
//		
//		//Usamos el VueloService para buscar en la BDD los vuelos por destino y fecha. 
//		//List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fecha);//		//List<Vuelo> vuelos = vueloService.findByDestino(destino);
//		List<Vuelo> vuelos = vueloService.findByFechaPartida(fecha);//		//Instanciamos el DTO para nuestra respuesta//		List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();//		
//		//Iteramos sobre el array de vuelos con esa fecha y destino.//		for(Vuelo vuelo : vuelos) {
//			//			VueloDisponibleDTO vueloDTO = new VueloDisponibleDTO(vuelo);//			//			vueloDTO.setNroVuelo(vuelo.getNroVuelo());
//			vueloDTO.setAerolinea(vuelo.getAerolinea());//			vueloDTO.setDestino(vuelo.getDestino());
//			vueloDTO.setTipoVuelo(vuelo.getTipoVuelo());//			vueloDTO.setFechaPartida(vuelo.getFechaPartida());//			vueloDTO.setHoraPartida(vuelo.getHoraPartida());//			//			//			vuelosDTO.add(vueloDTO);
//		}//		
//		//Se crea la response de acuerdo a si el arraylist esta vacio o no.//		if(vuelos.isEmpty()) {//			return ResponseEntity.noContent().build(); //ERROR 204 No Content//		}//		else {//			return ResponseEntity.ok(vuelosDTO); //Mensaje 200 OK.//		}		//  //  }
////
//////	@GetMapping
//////	public ResponseEntity<List<VueloDisponibleDTO>> obtenerVuelosDisponibles(@RequestParam("destino")String destino, 
//////		  													@RequestParam("fecha") 
//////  															@DateTimeFormat(pattern = "dd-MM-yyyy")
//////		  													LocalDate fecha){
//////		
//////		/*
//////		 * //Usamos el VueloService para buscar en la BDD los vuelos por destino y
//////		 * fecha. List<Vuelo> vuelos =
//////		 * vueloService.findByDestinoAndFechaPartida(destino, fecha);
//////		 */
//////		
//////		//Instanciamos el DTO para nuestra respuesta
//////		List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();
////		
////		Ciudad ciudad = new Ciudad();
////		ciudad.setCodPostal("3230");
////		ciudad.setNombreCiudad("Buenos Aires");
////		ciudad.setPais("Argentina");
////		ciudad.setProvincia("Buenos Aires");		
////		
////		for(int i = 0; i < 10; i++) {
////			
////			Vuelo vuelo = new Vuelo();
////			vuelo.setNroVuelo((long)i);
////			
////			VueloDisponibleDTO vueloDTO = new VueloDisponibleDTO(vuelo);
////			 
////			 vueloDTO.setNroVuelo((long) i);
////			 vueloDTO.setAerolinea("aero" + i);
////			 vueloDTO.setDestino(ciudad);
////			 vueloDTO.setFechaPartida(LocalDate.parse("2022-12-12"));
////			 vueloDTO.setHoraPartida(LocalTime.parse("22:22:00"));			 
////			 
////			 vuelosDTO.add(vueloDTO);
////			
////		}
////		
////		
////		//Iteramos sobre el array de vuelos con esa fecha y destino.
////		/*
////		 * for(Vuelo vuelo : vuelos) {
////		 * 
////		 * VueloDisponibleDTO vueloDTO = new VueloDisponibleDTO(vuelo);
////		 * 
////		 * vueloDTO.setNroVuelo(vuelo.getNroVuelo());
////		 * vueloDTO.setAerolinea(vuelo.getAerolinea());
////		 * vueloDTO.setDestino(vuelo.getDestino());
////		 * vueloDTO.setFechaPartida(vuelo.getFechaPartida());
////		 * vueloDTO.setHoraPartida(vuelo.getHoraPartida());
////		 * 
////		 * 
////		 * vuelosDTO.add(vueloDTO); }
////		 */
//		
//		//Se crea la response de acuerdo a si el arraylist esta vacio o no.
//		if(vuelosDTO.isEmpty()) {
//			return ResponseEntity.noContent().build(); //ERROR 204 No Content
//		}
//		else {
//			return ResponseEntity.ok(vuelosDTO); //Mensaje 200 OK.
//		}		
//  
//  }

	 
//}
