package com.tsti.rest;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.modelmapper.ModelMapper;

import com.tsti.dto.VueloDisponibleDTO;
import com.tsti.dto.VueloDTO;

import com.tsti.entidades.Vuelo;
import com.tsti.servicios.VueloServiceImpl;

@RestController
public class VueloController {

    private final VueloServiceImpl vueloService;
    private final ModelMapper modelMapper;
    private CollectionModel<VueloDisponibleDTO> vuelosCollectionModel;

    @Autowired
    public VueloController(VueloServiceImpl vueloService) {
        this.vueloService = vueloService;
        this.modelMapper = new ModelMapper();
    }
    
    @GetMapping("/vuelos")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> showVuelos() {
        List<Vuelo> vuelos = vueloService.getAll();
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            //Se mapea el DTO con la lista de vuelos
        	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        }

        CollectionModel<VueloDisponibleDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);
        
        //Link a si mismo
        vuelosCollectionModel.add(Link.of("/vuelos","self"));

        return ResponseEntity.ok(vuelosCollectionModel);
    }
    
    @GetMapping("/vuelos/filtrar")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosByDestinoAndFecha(
    		@RequestParam("destino")String destino,
    		@RequestParam("fecha")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
    	
    	List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fecha);
    	
    	if (vuelos.isEmpty()) {
        
    	return ResponseEntity.noContent().build();
    	
    	} else {
    	
	        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();
	        
	        for (Vuelo vuelo : vuelos) {
	            
	        	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
	            vuelosDTO.add(vueloDTO);
	        
	        } 
	        	
	        	vuelosCollectionModel = CollectionModel.of(vuelosDTO)
	        							.add(getLinks(destino, fecha, true));
	            
	        	return ResponseEntity.ok(vuelosCollectionModel);
        	
        }
    }
    
    @GetMapping("/vuelos/filtrar-por-destino")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosByDestino(
    		@RequestParam String destino) {
        
    	List<Vuelo> vuelos = vueloService.findByDestino(destino);
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        }
        
        vuelosCollectionModel = CollectionModel.of(vuelosDTO).add(showVuelosLink());

        return ResponseEntity.ok(vuelosCollectionModel);
    }    

	@GetMapping("/vuelos/filtrar-por-fecha")
    public ResponseEntity<CollectionModel<VueloDisponibleDTO>> getVuelosByFecha(
    		@RequestParam("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha){
        
		List<Vuelo> vuelos = vueloService.findByFechaPartida(fecha);
        List<VueloDisponibleDTO> vuelosDTO = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            
        	VueloDisponibleDTO vueloDTO = modelMapper.map(vuelo, VueloDisponibleDTO.class);
            vuelosDTO.add(vueloDTO);
        
        }
        
        vuelosCollectionModel = CollectionModel.of(vuelosDTO).add(showVuelosLink());                

        return ResponseEntity.ok(vuelosCollectionModel);
    }
    
    @PostMapping("/vuelos")    
    public ResponseEntity<EntityModel<Vuelo>> crearVuelo(
    											@RequestBody VueloDTO vueloDTO) {
        
    	Vuelo vuelo = vueloService.crearVuelo(vueloDTO);
            	   	
        return ResponseEntity.ok().body(EntityModel.of(vuelo, showVuelosLink()));
    }
    
    @PatchMapping("/vuelos/{id}")
    public ResponseEntity<EntityModel<Vuelo>> actualizarFechaHoraVuelo(
            									@PathVariable("id") Long vueloId,
            									@RequestBody VueloDTO vueloDTO) {
        
        Optional<Vuelo> vueloOptional = vueloService.findById(vueloId);
        
        if (vueloOptional.isPresent()) {
            Vuelo vuelo = vueloOptional.get();
            
            // Verificar si se permite cambiar la fecha y hora
            if (vueloDTO.getFechaPartida() != null && vueloDTO.getHoraPartida() 
            															!= null) {
                
                vueloService.reprogramarVuelo(vuelo, vueloDTO);
                                              

                return ResponseEntity.ok().body(EntityModel.of(vuelo, showVuelosLink()));
            
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
    
    public Link showVuelosLink(){    	    	    		
    		
    	Link showVuelosLink = WebMvcLinkBuilder.linkTo(
    									methodOn(VueloController.class).showVuelos())
    									.withRel("todos-los-vuelos");
    		
    	return showVuelosLink;  	
    	
    }
    
    public List<Link> getLinks(String destino, LocalDate fecha,
    														boolean showAllVuelos){
    	
    	List <Link> links = new ArrayList<>();
    	
    	links.add(showVuelosLink());
    		
		Link linkVuelosPorDestino = WebMvcLinkBuilder.linkTo(
				methodOn(VueloController.class)
				.getVuelosByDestino(destino))
				.withRel("vuelos-por-destino");
		
    	links.add(linkVuelosPorDestino);    	
		
		Link linkVuelosPorFecha = WebMvcLinkBuilder.linkTo(
				methodOn(VueloController.class)
				.getVuelosByFecha(fecha))
				.withRel("vuelos-por-fecha");    		
		
		links.add(linkVuelosPorFecha);    	
    	
    	return links;
    }
    
    
}
