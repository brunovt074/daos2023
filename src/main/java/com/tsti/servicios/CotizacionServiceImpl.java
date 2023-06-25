/**
 * 
 */
package com.tsti.servicios;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tsti.dto.CotizacionDolarDTO;
import com.tsti.excepcion.CotizacionDolarException;

/**
 * @author Bruno
 *
 */
@Service
public class CotizacionServiceImpl {

	private static final String URL_COTIZACION_DOLAR = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";
	private final RestTemplate restTemplate;
	
	
    public CotizacionServiceImpl() {
		this.restTemplate = new RestTemplate();
        
    }
	
	public BigDecimal getCotizacionDolarOficial(){
				
		ResponseEntity<CotizacionDolarDTO[]> response = restTemplate.getForEntity(URL_COTIZACION_DOLAR, CotizacionDolarDTO[].class);
		
			if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				
				CotizacionDolarDTO[] cotizaciones = response.getBody();
				
				for(CotizacionDolarDTO cotizacion : cotizaciones) {
					
					if(cotizacion.getNombre().equals("Dolar oficial")) {
						
						return cotizacion.getCompra();
				}
			}				
		}
		
		throw new CotizacionDolarException("No se pudo obtener la cotizacion del dolar oficial");
	}
}
