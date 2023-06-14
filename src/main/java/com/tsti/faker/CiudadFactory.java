/**
 * CLASE CREADA PARA POBLAR LA BD EN EL ENTORNO DE DESARROLLO
 * */
package com.tsti.faker;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsti.dao.CiudadDAO;
import com.tsti.entidades.Ciudad;

import net.datafaker.Faker;

/**
 * @author Bruno
 */
@Component
public class CiudadFactory {
	
	private final Faker faker;
	@Autowired
	private CiudadDAO ciudadDAO;
	
	public CiudadFactory() {

		this.faker = new Faker(new Locale("es"));		

	}
	
	public void crearCiudadArgentina() {
		
		for (int i = 0; i < 100; i++){
			
			Ciudad nuevaCiudad = new Ciudad();
			nuevaCiudad.setcodAeropuerto(faker.aviation().airport());
			nuevaCiudad.setNombreCiudad(faker.address().cityName());
			nuevaCiudad.setProvincia(faker.address().state());
			nuevaCiudad.setCodPostal(faker.address().zipCode());
			nuevaCiudad.setPais("Argentina");
			
			ciudadDAO.save(nuevaCiudad);
			System.out.println("CIUDAD GUARDADA: "+ nuevaCiudad.toString());
		}
	}

	public void crearCiudadAleatoria() {
		
		for (int i = 0; i < 100; i++){
			
			Ciudad nuevaCiudad = new Ciudad();
			nuevaCiudad.setcodAeropuerto(faker.aviation().airport());
			nuevaCiudad.setNombreCiudad(faker.address().cityName());
			nuevaCiudad.setProvincia(faker.address().state());
			nuevaCiudad.setPais(faker.address().country());
			nuevaCiudad.setCodPostal(faker.address().zipCode());
			
			ciudadDAO.save(nuevaCiudad);
			System.out.println("CIUDAD GUARDADA: "+ nuevaCiudad.toString());
		}
	}	
}	
		 