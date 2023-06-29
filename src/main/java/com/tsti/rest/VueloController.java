package com.tsti.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.VueloDTO;

import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.presentacion.CrearVueloForm;
import com.tsti.presentacion.EditarVueloForm;
import com.tsti.servicios.VueloServiceImpl;
/**
 *
 *Controlador del Servicio de Gestion de Vuelos
 *Posee los metodos para: Proporciona métodos para buscar vuelos,
 *crear nuevos vuelos, actualizar vuelos existentes y cancelar vuelos. *
 *   
 **/
@RestController
public class VueloController {

    private final VueloServiceImpl vueloService;
    //Coleccion de entidades Response Entity
    private CollectionModel<VueloDTO> vuelosCollectionModel;

    @Autowired
    public VueloController(VueloServiceImpl vueloService) {
        this.vueloService = vueloService;        
    }
    
    /**
     * Obtener todo los vuelos
     * 
     * @return Response entitiy con CollectionModel<VueloDTO> de todos los vuelos
     *  
     **/
    @GetMapping("/vuelos")
    public ResponseEntity<CollectionModel<VueloDTO>> showVuelos() {
           	
    	List<VueloDTO> vuelosDTO = vueloService.getAll();
    	
    	if(vuelosDTO == null){
    		
    		return ResponseEntity.notFound().build();
    		
    	}else{
    		
    		//Coleccion que agrupa varias Entities
            CollectionModel<VueloDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);
            
            //Link a si mismo
            vuelosCollectionModel.add(Link.of("/vuelos","self"));

            return ResponseEntity.ok(vuelosCollectionModel);    		
    	}   
        
    }
    
    /**
     * Obtener vuelos por estado del vuelo
     * 
     * @param estado de tipo {@link String}: "registrado" - "reprogramado" - "demorado" - "cancelado". Las cadenas pueden ser en mayuscula o minuscula.     *  
     * 
     * @return Response Entity con CollectionModel<VueloDTO> de todos los vuelos y links relacionados. 
    **/
    @GetMapping("/vuelos/estado-vuelo")
    public ResponseEntity<CollectionModel<VueloDTO>> showVuelosPorEstadoVuelo(
    							@RequestParam("estado") String estado){
    	
    	List<VueloDTO> vuelosDTO;
    	
    	if(estado.equalsIgnoreCase("registrado")){
    		vuelosDTO = vueloService.findAllByEstadoVuelo(EstadoVuelo.REGISTRADO);
    	
    	}else if(estado.equalsIgnoreCase("reprogramado")) {
    		
    		vuelosDTO = vueloService.findAllByEstadoVuelo(EstadoVuelo.REPROGRAMADO);
    	
    	}else if(estado.equalsIgnoreCase("demorado")){
    		
    		vuelosDTO = vueloService.findAllByEstadoVuelo(EstadoVuelo.DEMORADO);
    	
    	}else if(estado.equalsIgnoreCase("cancelado")) { 
    	
    		vuelosDTO = vueloService.findAllByEstadoVuelo(EstadoVuelo.CANCELADO);
    	
    	} else {
    		
    		return ResponseEntity.badRequest().build();
    		
    	}  		
    	
    	if(vuelosDTO == null){
    		
    		return ResponseEntity.notFound().build();
    	
    	}else{
    		
    		//Coleccion que agrupa varias Entities
            CollectionModel<VueloDTO> vuelosCollectionModel = CollectionModel.of(vuelosDTO);
            
            //Link a si mismo
            vuelosCollectionModel.add(Link.of("/vuelos/estado-vuelo?estado="+estado,"self"))
            					 .add(AppLinks.showVuelosLink());

            return ResponseEntity.ok(vuelosCollectionModel);    		
    	}
    }    
    /**
     * Obtener vuelo por ID
     * 
     * @param id con nro_vuelo de tipo {@link Long}
     * 
     * @return ResponseEntity con EntityModel<VueloDTO> que contiene el vuelo buscado.
     **/
    @GetMapping("/vuelos/{id}")
    public ResponseEntity<EntityModel<VueloDTO>> getVueloById(@PathVariable Long id){
		
    	if(id == null){
    		
    		return ResponseEntity.badRequest().build();
    		
    	}else{
    		
    		Optional<Vuelo> vueloOptional = vueloService.findById(id);
    		
    		if(vueloOptional.isPresent()){
    			
    			VueloDTO vueloDTO = new VueloDTO(vueloOptional.get());  
    		
    			EntityModel<VueloDTO> vueloEntityModel = EntityModel.of(vueloDTO)
    										.add(Link.of("/vuelos/"+ id,"self"))
    										.add(AppLinks.showVuelosLink());
    			
    			return ResponseEntity.ok().body(vueloEntityModel);
    		
    		}else {
    			
    			return ResponseEntity.notFound().build();
    		}
    		
    	}
    }
    
    /**
     *Obtener vuelos por destino y fecha. 
     *Considerando que es una consulta frecuente obtiene todos los vuelos 
     *que coincidan con los parametros
     *
     *@param destino de tipo {@link String} correspondiente al nombre_ciudad de destino
     *@param fecha de tipo {@link LocalDate} correspondiente a fecha de partida en formato (yyyy-MM-dd)
     *
     *@return Response Entity con CollectionModel<VueloDTO> de todos los vuelos que satisfacen los parametros, y links relacionados
     **/
    @GetMapping("/vuelos/filtrar-destino-fecha")
    public ResponseEntity<CollectionModel<VueloDTO>> getVuelosByDestinoAndFecha(
    		@RequestParam("destino")String destino,
    		@RequestParam("fecha")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
    	
    	List<VueloDTO> vuelosDTO = vueloService.findByDestinoAndFechaPartida(destino, fecha);
    	
    	if (vuelosDTO.isEmpty()) {
        
    		return ResponseEntity.notFound().build();
    	
    	} else {	        
	        
	        vuelosCollectionModel = CollectionModel.of(vuelosDTO)
	        						.add(AppLinks.getLinksVuelos(2, destino, fecha));
	                    
	        return ResponseEntity.ok(vuelosCollectionModel);        	
        }
    }
    
    
    /**
     *Obtener vuelos por destino. 
     *
     *@param destino de tipo {@link String} correspondiente al nombre_ciudad de destino     
     *
     *@return Response Entity con CollectionModel<VueloDTO> de todos los vuelos que satisfacen los parametros.
     **/
    @GetMapping("/vuelos/filtrar-por-destino")
    public ResponseEntity<CollectionModel<VueloDTO>> getVuelosByDestino(
    												@RequestParam String destino) {
    	
    	List<VueloDTO> vuelosDTO = vueloService.findByDestino(destino);
        
        if(vuelosDTO.isEmpty()){
        	
        	return ResponseEntity.notFound().build();
        	
        } else {
	        	        
	        vuelosCollectionModel = CollectionModel.of(vuelosDTO)
	        						.add(AppLinks.getLinksVuelos(3, destino, null));
	
	        return ResponseEntity.ok(vuelosCollectionModel);
        }
    }    
    
    /**
     *Obtener vuelos fecha. 
     *
     *@param fecha de tipo {@link LocalDate}correspondiente a fecha de partida en formato (yyyy-MM-dd)
     *
     *@return Response Entity con CollectionModel<VueloDTO> de todos los vuelos que satisfacen los parametros.
     **/
	@GetMapping("/vuelos/filtrar-por-fecha")
    public ResponseEntity<CollectionModel<VueloDTO>> getVuelosByFecha(
    		@RequestParam("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate fecha){
        
		
        List<VueloDTO> vuelosDTO = vueloService.findByFechaPartida(fecha);
    	
        if(vuelosDTO.isEmpty()){
        	
        	return ResponseEntity.notFound().build();
        	
        } else {
	                
	        vuelosCollectionModel = CollectionModel.of(vuelosDTO)
	        							.add(AppLinks.getLinksVuelos(4, null, fecha));
	        
	        return ResponseEntity.ok(vuelosCollectionModel);
        }
    }
	
    /**
     *Crear un vuelo. 
     *Recibe un formulario con todos los datos necesarios. 
     *La ciudad de origen se setea como Sauce Viejo por defecto. 
     *La ciudad de destino se busca en la BD 
     *y si no existe se crea una nueva con los datos del formulario.
     *
     * @param vueloForm formulario de tipo {@link CrearVueloForm}
     * @return ResponseEntity<EntityModel<VueloDTO>> con la informacion del nuevo vuelo creado
     **/
    @PostMapping("/vuelos")    
    public ResponseEntity<EntityModel<VueloDTO>> crearVuelo(
    											@RequestBody CrearVueloForm vueloForm) {
        //retornar id para el DTO
    	VueloDTO vueloDTO = vueloService.crearVuelo(vueloForm);
    	   	
    	EntityModel<VueloDTO> nuevoVueloEntity = EntityModel.of(vueloDTO)
												.add(WebMvcLinkBuilder.linkTo(
								    					methodOn(VueloController.class)
								    					.getVueloById(vueloDTO.getNroVuelo()))
														.withSelfRel())
												.add(AppLinks.showVuelosLink());
        
     	
        return ResponseEntity.ok().body(nuevoVueloEntity);
    }
    
    /**
     * Actualizar fecha y hora de vuelo.
     * Se recibe un formulario con la fecha, hora de vuelo o ambas.
     * 
     * @param id como PathVariable correspondiente al nroVuelo de tipo {@link Long}
     * @param vueloForm del tipo {@link EditarVueloForm} con los datos necesarios para la actualizacion 
     * 
     * @return ResponseEntity<EntityModel<VueloDTO>> con los datos del vuelo actualizados. 
     ***/
    @PatchMapping("/vuelos/{id}")
    public ResponseEntity<EntityModel<VueloDTO>> actualizarFechaHoraVuelo(
            									@PathVariable("id") Long vueloId,
            									@RequestBody EditarVueloForm vueloForm) {
    	
    	VueloDTO vueloDTO;
    	
    	if (vueloForm.getFechaPartida() == null && vueloForm.getHoraPartida() == null) {
    		
    		return ResponseEntity.badRequest().build();
    		
    	} else {
    		
    		vueloDTO = vueloService.reprogramarVuelo(vueloId, vueloForm);
    		
    		if (vueloDTO.getNroVuelo() == null) {
    	    	
        		return ResponseEntity.notFound().build();
        	}else {
    			EntityModel<VueloDTO> vueloReprogramadoEntity = EntityModel.of(vueloDTO)
											    					.add(WebMvcLinkBuilder.linkTo(
													    					methodOn(VueloController.class)
													    					.getVueloById(vueloDTO.getNroVuelo()))
													    					.withSelfRel())							
											    					.add(AppLinks.showVuelosLink());																	
                
                return ResponseEntity.ok().body(vueloReprogramadoEntity);
    			
    		}
    	}	
    } 
    
    /**
     *Cancelar vuelo.
     *Consiste en realizar un soft delete cambiando el estado del vuelo a {@link EstadoVuelo.CANCELADO}
     *y colocando el atributo {@link BigDecimal} precioNeto = 0.00
     * 
     *@param id en PathVariable correspondiente al nro_vuelo
     *
     *@return ResponseEntity<EntityModel<VueloDTO>> con los datos del vuelo actualizado y links relacionados. 
     **/
    @DeleteMapping("/vuelos/{id}")
    public ResponseEntity<EntityModel<VueloDTO>> cancelarVuelo(
            							@PathVariable("id") Long vueloId) {
        
        // Realizar soft delete (cambiar estado a CANCELADO)            
        VueloDTO vueloCanceladoDTO = vueloService.cancelarVuelo(vueloId);
        
        if(vueloCanceladoDTO.getNroVuelo() == null){
        	
        	return ResponseEntity.notFound().build();
        	
        }else {
        	EntityModel<VueloDTO> vueloCanceladoEntity = EntityModel.of(vueloCanceladoDTO)
								        			.add(WebMvcLinkBuilder.linkTo(
									    					methodOn(VueloController.class)
									    					.getVueloById(vueloCanceladoDTO.getNroVuelo()))
									    					.withSelfRel())
													.add(Link.of("/vuelos/estado-vuelo?estado=CANCELADO","self"))
													.add(AppLinks.showVuelosLink());	    				

        	return ResponseEntity.ok().body(vueloCanceladoEntity);
        	
        }
    }   
    
}
 