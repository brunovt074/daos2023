/**
 * 
 */
package tsti.servicios;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tsti.dto.CotizacionDolarDTO;
//import com.tsti.excepcion.CotizacionDolarException;

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
		

			if(response.getStatusCode().is2xxSuccessful()) {
				
				CotizacionDolarDTO[] cotizaciones = response.getBody();				
				
				for(CotizacionDolarDTO cotizacion : cotizaciones) {
					
					CotizacionDolarDTO.CasaDTO casaDTO = cotizacion.getCasa();
					
					if(casaDTO.getNombre().equalsIgnoreCase("Dolar Oficial")) {
						
					//se recibe un string con el formato XXX,XX y se lo formatea antes de convertirlo. 	
					return BigDecimal.valueOf(
							Double.parseDouble(casaDTO.getVenta().replace(",",".")));
						
						
				}
			}
				//throw new CotizacionDolarException("No se pudo obtener la cotizacion del dolar oficial");	
		}
			
		return null;
	}
}
