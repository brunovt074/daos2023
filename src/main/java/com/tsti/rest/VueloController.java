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
}