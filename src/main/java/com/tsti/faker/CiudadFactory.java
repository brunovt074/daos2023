/**
 * CLASE CREADA PARA POBLAR LA BD EN EL ENTORNO DE DESARROLLO
 * */
package tsti.faker;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tsti.dao.CiudadDAO;
import tsti.entidades.Ciudad;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 * LA ANOTACION COMPONENT ES PARA MATCHEARLOS CON FactoryInitializer
 */
@Component
public class CiudadFactory {
	
	private final Faker faker;
	@Autowired
	private CiudadDAO ciudadDAO;
	private Ciudad nuevaCiudad;
	
	@Autowired
	public CiudadFactory() {

		this.faker = new Faker(new Locale("es"));		
		this.nuevaCiudad = new Ciudad();
		//this.ciudadDAO = ciudadDAO;
	}
	
	public Ciudad getCiudadSauceViejo() {
		
		if(ciudadDAO.existsByCodAeropuerto("SAAV")){
			
			nuevaCiudad = ciudadDAO.findFirstByCodAeropuertoAndNombreCiudad
														("SAAV", "Sauce Viejo");
		} else{
		
		nuevaCiudad.setCodAeropuerto("SAAV");
		nuevaCiudad.setNombreCiudad("Sauce Viejo");
		nuevaCiudad.setProvincia("Santa Fe");
		nuevaCiudad.setCodPostal("S3017");
		nuevaCiudad.setPais("Argentina");	
		
		
		}
		
		return nuevaCiudad;
	}
	
	public Ciudad getCiudadArgentina() {
		
		Ciudad nuevaCiudad = new Ciudad();
		
		inicializarCiudad (nuevaCiudad, true);	
		
		return nuevaCiudad;			
	}
	
	public void crearCiudadArgentina() {
			
		Ciudad nuevaCiudad = new Ciudad();
		
		inicializarCiudad (nuevaCiudad, true);
		
		ciudadDAO.save(nuevaCiudad);
		System.out.println("UNA CIUDAD ARG GUARDADA: "+ nuevaCiudad.toString());
			
	}
	
	public void crearCiudadesArgentina() {
		
		for (int i = 0; i < 100; i++){
			
			Ciudad nuevaCiudad = new Ciudad();
			
			inicializarCiudad (nuevaCiudad, true);
			
			ciudadDAO.save(nuevaCiudad);
			System.out.println("CIUDAD GUARDADA: "+ nuevaCiudad.toString());
		}
	}
	
	public Ciudad getCiudadAleatoria() {
		
		Ciudad nuevaCiudad = new Ciudad();

		inicializarCiudad (nuevaCiudad, false);
		
		System.out.println("UNA CIUDAD INTERNAC CREADA: "+ nuevaCiudad.toString());
		return nuevaCiudad;			
	}
	
	public void crearCiudadAleatoria() {
		
		Ciudad nuevaCiudad = new Ciudad();
		
		inicializarCiudad (nuevaCiudad, false);
		
		ciudadDAO.save(nuevaCiudad);
		System.out.println("UNA CIUDAD INTERNAC GUARDADA: "+ nuevaCiudad.toString());
		
	}

	public void crearCiudadesAleatoria() {
		
		for (int i = 0; i < 100; i++){
			
			Ciudad nuevaCiudad = new Ciudad();
			
			inicializarCiudad (nuevaCiudad, false);
			
			ciudadDAO.save(nuevaCiudad);
			System.out.println("CIUDAD GUARDADA: "+ nuevaCiudad.toString());
		}
	}
	
	private Ciudad inicializarCiudad (Ciudad nuevaCiudad, boolean esNacional) {
		
		if(esNacional) {
			
			nuevaCiudad.setCodAeropuerto(faker.aviation().airport());
			nuevaCiudad.setNombreCiudad(faker.address().cityName());
			nuevaCiudad.setProvincia(faker.address().state());
			nuevaCiudad.setCodPostal(faker.address().zipCode());
			nuevaCiudad.setPais("Argentina");
		
		
		}
		
		else {
		
			nuevaCiudad.setCodAeropuerto(faker.aviation().airport());
			nuevaCiudad.setNombreCiudad(faker.address().cityName());
			nuevaCiudad.setProvincia(faker.address().state());
			nuevaCiudad.setPais(faker.address().country());
			nuevaCiudad.setCodPostal(faker.address().zipCode());
		}
		
		
		return nuevaCiudad;
	}
}	
		 