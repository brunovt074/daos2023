package com.tsti.rest;


import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
//import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
//import org.springframework.http.HttpStatus;
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
//import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
//import com.tsti.entidades.Vuelo.EstadoVuelo;
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
    public ResponseEntity<CollectionModel<EntityModel<VueloDisponibleDTO>>> getVuelosByDestinoAndFechaPartida(
            @RequestParam("destino") String destino,
            @RequestParam("fechaPartida") @DateTimeFormat(/*pattern = "dd-MM-yyyy"*/) LocalDate fechaPartida) {

        List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fechaPartida);

        if (vuelos.isEmpty()) {
            
        	return ResponseEntity.noContent().build();
        
        } else if (vuelos.size() == 1) {
            
        	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelos.get(0), VueloDisponibleDTO.class);
            
        	EntityModel<VueloDisponibleDTO> vueloEntityModel = EntityModel.of(vueloDTO);

            // Enlace a todos los vuelos con el mismo destino
            vueloEntityModel.add(WebMvcLinkBuilder
                    .linkTo(methodOn(VueloController.class)
                            .getVuelosByDestino(destino))
                    .withRel("vuelosPorDestino"));
         
            // Enlace a todos los vuelos con la misma fecha
            vueloEntityModel.add(WebMvcLinkBuilder
            	    .linkTo(methodOn(VueloController.class)
            	    .getVuelosByFechaPartida(fechaPartida))
            	    .withRel("vuelosPorFecha"));
            
         // Enlace a todos los vuelos disponibles
            vueloEntityModel.add(WebMvcLinkBuilder
                    .linkTo(methodOn(VueloController.class)
                            .getVuelosAll())
                    .withRel("todosLosVuelos"));
            
            

            return ResponseEntity.ok(CollectionModel.of(Collections.singletonList(vueloEntityModel)));
        
        } else {

        	List<EntityModel<VueloDisponibleDTO>> vuelosDTO = new ArrayList<>();

            for (Vuelo vuelo : vuelos) {
                
            	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
                EntityModel<VueloDisponibleDTO> vueloEntityModel = EntityModel.of(vueloDTO);
                
                //Enlace a todos los vuelos con el mismo destino
                vueloEntityModel.add(WebMvcLinkBuilder
                        .linkTo(methodOn(VueloController.class)
                                .getVuelosByDestino(destino))
                        .withRel("vuelosPorDestino"));
             
                //Enlace a todos los vuelos con la misma fecha
                vueloEntityModel.add(WebMvcLinkBuilder
                	    .linkTo(methodOn(VueloController.class)
                	    .getVuelosByFechaPartida(fechaPartida))
                	    .withRel("vuelosPorFecha"));
                
                //Enlace a todos los vuelos 
                vueloEntityModel.add(WebMvcLinkBuilder
                	    .linkTo(methodOn(VueloController.class)
                	    .getVuelosAll())
                	    .withRel("todosLosVuelos"));
                
                vuelosDTO.add(vueloEntityModel);
                
            }

            return ResponseEntity.ok(CollectionModel.of(vuelosDTO));
        }
    }

    
    @GetMapping("/vuelos/destinos/{destino}")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosByDestino(@PathVariable String destino) {
        List<Vuelo> vuelos = vueloService.findByDestino(destino);
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        }
        
        CollectionModel<VueloDisponibleDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);
        //Enlace a todos los vuelos 
        vuelosCollectionModel.add(WebMvcLinkBuilder
        	    .linkTo(methodOn(VueloController.class)
        	    .getVuelosAll())
        	    .withRel("todosLosVuelos"));
        

        return ResponseEntity.ok(vuelosCollectionModel);
    }
    
    @GetMapping("/vuelos/fechaPartida/{fechaPartida}")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosByFechaPartida(@PathVariable("fechaPartida") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fechaPartida) {
        List<Vuelo> vuelos = vueloService.findByFechaPartida(fechaPartida);
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            
        	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        
        }

        CollectionModel<VueloDisponibleDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);
        //Enlace a todos los vuelos 
        vuelosCollectionModel.add(WebMvcLinkBuilder
        	    .linkTo(methodOn(VueloController.class)
        	    .getVuelosAll())
        	    .withRel("todosLosVuelos"));

        return ResponseEntity.ok(vuelosCollectionModel);
    }
    
    @GetMapping("/vuelos/all")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosAll() {
        List<Vuelo> vuelos = vueloService.getAll();
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        }

        CollectionModel<VueloDisponibleDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);

        return ResponseEntity.ok(vuelosCollectionModel);
    }

    
    @PostMapping("/vuelos")    
    public ResponseEntity<EntityModel<Vuelo>> crearVuelo(@RequestBody VueloDTO vueloDTO) {
        
    	Vuelo vuelo = vueloService.crearVuelo(vueloDTO);
        
    	// Enlace a todos los vuelos
        Link allVuelosLink = WebMvcLinkBuilder.linkTo(methodOn(VueloController.class).getVuelosAll()).withRel("todosLosVuelos");
    	
        return ResponseEntity.ok().body(EntityModel.of(vuelo, allVuelosLink));
    }
    
    @PutMapping("/vuelos/{id}")
    public ResponseEntity<EntityModel<Vuelo>> actualizarFechaHoraVuelo(
            @PathVariable("id") Long vueloId,
            @RequestBody VueloDTO vueloDTO) {
        
        Optional<Vuelo> vueloOptional = vueloService.findById(vueloId);
        
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            
            // Verificar si se permite cambiar la fecha y hora
            if (vueloDTO.getFechaPartida() != null && vueloDTO.getHoraPartida() != null) {
                
                vueloService.reprogramarVuelo(vuelo, vueloDTO);
                
             // Enlace a todos los vuelos
                Link allVuelosLink = WebMvcLinkBuilder.linkTo(methodOn(VueloController.class).getVuelosAll()).withRel("todosLosVuelos");

                return ResponseEntity.ok().body(EntityModel.of(vuelo, allVuelosLink));
            
            } else {
                
            	return ResponseEntity.badRequest().build();
            
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
