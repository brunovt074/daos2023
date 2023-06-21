package com.tsti.rest;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.tsti.dto.VueloDisponibleDTO;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.servicios.IVueloService;

@RestController
public class VueloController {

    private final IVueloService vueloService;
    private final ModelMapper modelMapper;

    @Autowired
    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
        this.modelMapper = new ModelMapper();
    }
    
    @GetMapping("/vuelos")
    public ResponseEntity<List<VueloDisponibleDTO>> getVuelosByDestinoAndFechaPartida(
        @RequestParam("destino") String destino,
        @RequestParam("fechaPartida") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaPartida) {
            
        List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fechaPartida);
        
           //Forma alternativa y mas eficiente de realizar el mapeo implementado en el bucle for de abajo. Ideal para entornos de alta demanda de procesamiento.
          // Se debe reemplazar con el arraylist de ahi abajo. 
          
			
			  List<VueloDisponibleDTO> vuelosDTO = vuelos.stream() .map(vuelo ->
			  modelMapper.map(vuelo, VueloDisponibleDTO.class))
			  .collect(Collectors.toList());
			 
        
        
        //List<VueloDisponibleDTO> vuelosDTO = new ArrayList<VueloDisponibleDTO>();
        
        if (vuelos.isEmpty()) {
        	
            return ResponseEntity.noContent().build();
            
        } else {
        	
        	//Se utiliza esta clase para mapear de manera mas conscisa y eficiente cada entidad vuelo con el objeto DTO.
        	//ModelMapper modelMapper = new ModelMapper();
        	
//        	for (Vuelo vuelo : vuelos) {
//        		
//        		vuelosDTO.add(new VueloDisponibleDTO(vuelo));
//        	}
            
        	return ResponseEntity.ok(vuelosDTO);
            
        }
    }    
}

/*package com.tsti.rest;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.servicios.IVueloService;

@RestController
public class VueloController {

    private final IVueloService vueloService;

    @Autowired
    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }
    
    @GetMapping("/vuelos")
    public ResponseEntity<List<Vuelo>> getVuelosByDestinoAndFechaPartida(
        @RequestParam("destino") String destino,
        @RequestParam("fechaPartida") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaPartida) {
            
        List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fechaPartida);

        if (vuelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vuelos);
        }
    }    
}*/



