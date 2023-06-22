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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.tsti.dto.VueloDisponibleDTO;
import com.tsti.dto.VueloDTO;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.servicios.VueloServiceImpl;

@RestController
public class VueloController {

    private final VueloServiceImpl vueloService;
    private final ModelMapper modelMapper;

    @Autowired
    public VueloController(VueloServiceImpl vueloService) {
        this.vueloService = vueloService;
        this.modelMapper = new ModelMapper();
    }
    
    @GetMapping("/vuelos")
    public ResponseEntity<List<VueloDisponibleDTO>> getVuelosByDestinoAndFechaPartida(
        @RequestParam("destino") String destino,
        @RequestParam("fechaPartida") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaPartida) {
            
        List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fechaPartida);
        
           //Forma alternativa y mas eficiente de realizar el mapeo implementado 
        	//Ideal para entornos de alta demanda de procesamiento.
          		
			  List<VueloDisponibleDTO> vuelosDTO = vuelos.stream() .map(vuelo ->
			  modelMapper.map(vuelo, VueloDisponibleDTO.class))
			  .collect(Collectors.toList());       
        
        if (vuelos.isEmpty()) {
        	
            return ResponseEntity.noContent().build();
            
        } else {        	
            
        	return ResponseEntity.ok(vuelosDTO);
            
        }
    }
    
    @PostMapping("/vuelos")    
    public ResponseEntity<Vuelo> crearVuelo(@RequestBody VueloDTO vueloDTO) {
        
    	Vuelo vuelo = vueloService.crearVuelo(vueloDTO);
        
    	return ResponseEntity.ok().body(vuelo);
    }
    
    @PutMapping("/vuelos/{id}")
    public ResponseEntity<String> actualizarFechaHoraVuelo(
            @PathVariable("id") Long vueloId,
            @RequestBody VueloDTO vueloDTO) {
        
        Optional<Vuelo> vueloOptional = vueloService.findById(vueloId);
        
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            
            // Verificar si se permite cambiar la fecha y hora
            if (vueloDTO.getFechaPartida() != null && vueloDTO.getHoraPartida() != null) {
                
                vueloService.reprogramarVuelo(vuelo, vueloDTO);
                
                return ResponseEntity.ok("Fecha y hora del vuelo actualizadas correctamente.");
            
            } else {
                
            	return ResponseEntity.badRequest().body("La fecha y hora de partida no pueden ser nulas.");
            
            }
        
        } else {
            
        	return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/vuelos/{id}")
    public ResponseEntity<String> cancelarVuelo(
            @PathVariable("id") Long vueloId) {
        
        Optional<Vuelo> vueloOptional = vueloService.findById(vueloId);
        
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            
            // Realizar soft delete (cambiar estado a CANCELADO)
            
            vueloService.cancelarVuelo(vuelo);
            
            return ResponseEntity.ok("El vuelo ha sido cancelado correctamente.");
        
        } else {
            
        	return ResponseEntity.notFound().build();
        
        }
    }
}
