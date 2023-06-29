package com.tsti.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.dto.PasajeDTO;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

@Service
public class PasajeServiceImpl {

	//@Autowired
	private final VueloServiceImpl vueloService;
	
	@Autowired 
	public PasajeServiceImpl(VueloServiceImpl vueloService){
		this.vueloService = vueloService;
		
	} 
	
	public PasajeDTO getCostoPasaje(Long nroVuelo, Long dni){
		
		//Creamos el DTO en base al numero que nos pasaron
		PasajeDTO pasajeDTO = new PasajeDTO();
		//VueloServiceImpl vueloService = new VueloServiceImpl();
		
		//chequeamos que el vuelo exista en la BD		
		Optional<Vuelo> vueloOptional = vueloService.findById(nroVuelo);
		if(vueloOptional.isPresent()){
			
			Vuelo vuelo = vueloOptional.get();
			
			if(vuelo.getEstadoVuelo() != EstadoVuelo.CANCELADO) {
				TasaServiceImpl tasaService = new TasaServiceImpl();
				
				pasajeDTO.setNroVuelo(nroVuelo);
				pasajeDTO.setDni(dni);
				//Se obtiene el precio neto del pasajeDTO y se deduce tasa segun tipo de vuelo.
				BigDecimal precioNeto = vuelo.getPrecioNeto();			
				BigDecimal tasa = tasaService.getTasa(vuelo.getTipoVuelo());			
				BigDecimal precioFinal = precioNeto.add(tasa);
				
				//Chequeamos tipo de vuelo para realizar cotizacion dado el caso
				if(vuelo.getTipoVuelo() == TipoVuelo.INTERNACIONAL){
					
					CotizacionServiceImpl cotizacionService = new CotizacionServiceImpl();
					
					//multiplicamos y redondeamos a un numero de dos cifras despues de la coma
					precioFinal =  precioFinal.multiply(cotizacionService.getCotizacionDolarOficial())
															.setScale(2, RoundingMode.HALF_DOWN);			
					
				}
				
				pasajeDTO = new PasajeDTO(vuelo.getNroVuelo(), dni, precioNeto, tasa, 
											precioFinal, vuelo.getTipoVuelo());				
			}
		}
		
		return pasajeDTO;
	}
	
}
	

