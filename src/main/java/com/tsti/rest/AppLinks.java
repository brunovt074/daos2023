/**
 * 
 */
package com.tsti.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import io.micrometer.common.lang.Nullable;

/**
 * @author Bruno
 *
 *Clase creada para reunir toda la logica referida a los links.
 *
 */

public class AppLinks {
	
		
	public static List<Link> getLinksCostoPasaje(Long nro_vuelo,
														Long dni){
		List <Link> links = new ArrayList<>();
		
		Link linkCostoPasaje= WebMvcLinkBuilder.linkTo(
				methodOn(CostoPasajeController.class)
				.getCostoPasaje(nro_vuelo, dni))
				.withSelfRel();
		
		links.add(linkCostoPasaje);
		links.add(getLinkDolar());
		
		return links;
		
	}
	
	public static Link getLinkDolar() {
		
		return Link.of("https://www.dolarsi.com/api/api.php?type=valoresprincipales")
							.withRel("api-dolar");
		
	}
	
	/*
     * Obtiene los links de los vuelos por: 
     *
     * @param opcion  La opción seleccionada. Valores posibles: 1 (VUELOS), 2 (DESTINO_FECHA), 3 (DESTINO), 4 (FECHA), 5 (TIPO).
     * @param destino El destino de los vuelos (opcional).
     * @param fecha   La fecha de los vuelos (opcional).
     * 
     * @return Una lista de enlaces relacionados con los vuelos.
     *      
     */
     
    public static List<Link> getLinksVuelos(int opcion, @Nullable String destino,
    										 @Nullable LocalDate fecha){
    	    	
    	final int VUELOS = 1 ;
    	final int DESTINO_FECHA = 2;
    	final int DESTINO = 3;
    	final int FECHA = 4;
    	final int TIPO = 5;
    	
    	List <Link> links = new ArrayList<>();
    	Link link;
    	
    	switch(opcion){
    		
	    	case VUELOS:
	    		
	    		break;
	    	
	    	case DESTINO_FECHA:
    		
	    		link = WebMvcLinkBuilder.linkTo(
    					methodOn(VueloController.class)
    					.getVuelosByDestinoAndFecha(destino, fecha))
    					.withRel("filtrar-vuelos-destino-fecha");
	    		links.add(link);
	    		
	    		link = WebMvcLinkBuilder.linkTo(
			    					methodOn(VueloController.class)
			    					.getVuelosByDestino(destino))
			    					.withRel("vuelos-destino");
	    		links.add(link);
	    		
	    		link = WebMvcLinkBuilder.linkTo(
				    				methodOn(VueloController.class)
				    				.getVuelosByFecha(fecha))
				    				.withRel("vuelos-fecha");   		
	    		links.add(link);    			    		
    		
	    		break;    	
    	
    		case DESTINO:
    		
    			link = WebMvcLinkBuilder.linkTo(
    					methodOn(VueloController.class)
    					.getVuelosByDestino(destino))
    					.withRel("self");
    			
    			links.add(link);
    			
    			break;
    		
    		case FECHA:
    		
    			link = WebMvcLinkBuilder.linkTo(
	    				methodOn(VueloController.class)
	    				.getVuelosByFecha(fecha))
	    				.withRel("self");
    			
    			links.add(link);
    			break;
    	
    		case TIPO:
    			
    			link =  Link.of("/vuelos/filtrar-por-tipo", "self")
    						.withRel("vuelos-filtrar-por-tipo");
    			
    			links.add(link);
    			
    			break;    	
    	}
    	
    	
    	
    	links.add(showVuelosLink());   	    	
    	
    	return links;
    }
    
    public static Link getVueloPorId(Long nroVuelo){
    	Link link = WebMvcLinkBuilder.linkTo(methodOn(VueloController.class)
									.getVueloById(nroVuelo))
    								.withRel("nro-vuelo");
    	return link ; 
    }
    
    /*
     * Muestra todos los vuelos
     */
    public static Link showVuelosLink(){		
		
    	Link showLinkVuelos = WebMvcLinkBuilder.linkTo(
    									methodOn(VueloController.class).showVuelos())
    									.withRel("index");
    		
    	return showLinkVuelos;  	
    	
    }
    
    /*
	 * Clase para los links self. En construccion. 
	 */
	public static Link getLinkSelf(String uri){
		
		Link linkSelf = Link.of(uri, "self");
		
		return linkSelf;
	}
    
}
