/*
* CLASE CREADA PARA POBLACION DE TABLA DOMICILIOS 
 */
package com.tsti.faker;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.DomicilioDAO;
import com.tsti.entidades.Domicilio;
import com.tsti.entidades.Ciudad;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 *LA ANOTACION COMPONENT ES PARA MATCHEARLOS CON FactoryInitializer
 */
@Component
public class DomicilioFactory {
	
	private Faker faker;
	private CiudadFactory ciudadFactory;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired 
	private CiudadDAO ciudadDAO;
	 
	
	public DomicilioFactory() {
		this.ciudadFactory = new CiudadFactory();
	}
	
	public Domicilio obtenerUnDomicilioArgentino(CiudadDAO ciudadDAO){
		
		faker = new Faker(new Locale("es"));
		Domicilio nuevoDomicilio = new Domicilio();		
		Ciudad nuevaCiudad = ciudadFactory.getCiudadArgentina(); 
		ciudadDAO.save(nuevaCiudad);
		
		nuevoDomicilio.setDireccion(faker.address().streetAddress());
		nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
		//nuevoDomicilio.setDepto;
		nuevoDomicilio.setCiudad(nuevaCiudad);		
		
		return nuevoDomicilio;		
		
	}
	
	public void crearUnDomicilioArgentino(){
		
		faker = new Faker(new Locale("es"));
		Domicilio nuevoDomicilio = new Domicilio();		
		Ciudad nuevaCiudad = ciudadFactory.getCiudadArgentina(); 
		ciudadDAO.save(nuevaCiudad);
		
		nuevoDomicilio.setDireccion(faker.address().streetAddress());
		nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
		//nuevoDomicilio.setDepto;
		nuevoDomicilio.setCiudad(nuevaCiudad);		
		
		domicilioDAO.save(nuevoDomicilio);
		
		System.out.println("DOMICILIO CREADO: " + nuevoDomicilio.toString());
	}
	
	public void crearDomiciliosArgentinos(){
				
		faker = new Faker(new Locale("es"));
		
		for (int i = 0; i < 100; i++){
			Domicilio nuevoDomicilio = new Domicilio();		
			Ciudad nuevaCiudad = ciudadFactory.getCiudadArgentina(); 
			ciudadDAO.save(nuevaCiudad);
			
			nuevoDomicilio.setDireccion(faker.address().streetAddress());
			nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
			//nuevoDomicilio.setDepto;
			nuevoDomicilio.setCiudad(nuevaCiudad);		
			
			domicilioDAO.save(nuevoDomicilio);
			
			System.out.println("DOMICILIO CREADO: " + nuevoDomicilio.toString());
		}		
	}
	
	public Domicilio obtenerUnDomicilioAleatorio(CiudadDAO ciudadDAO){
		faker = new Faker();
		
		Domicilio nuevoDomicilio = new Domicilio();		
		Ciudad nuevaCiudad = ciudadFactory.getCiudadAleatoria(); 
		ciudadDAO.save(nuevaCiudad);
		
		nuevoDomicilio.setDireccion(faker.address().streetAddress());
		nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
		//nuevoDomicilio.setDepto;
		nuevoDomicilio.setCiudad(nuevaCiudad);		
		
		return nuevoDomicilio;	
		
	}
	
	public void crearUnDomicilioAleatorio(){
		faker = new Faker();
		
		Domicilio nuevoDomicilio = new Domicilio();		
		Ciudad nuevaCiudad = ciudadFactory.getCiudadAleatoria(); 
		ciudadDAO.save(nuevaCiudad);
		
		nuevoDomicilio.setDireccion(faker.address().streetAddress());
		nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
		//nuevoDomicilio.setDepto;
		nuevoDomicilio.setCiudad(nuevaCiudad);		
		
		domicilioDAO.save(nuevoDomicilio);
		
		System.out.println("DOMICILIO CREADO: " + nuevoDomicilio.toString());
	}
	
		public void crearDomiciliosAleatorios(){
		
			faker = new Faker(new Locale("es"));
		
			for (int i = 0; i < 100; i++){
				Domicilio nuevoDomicilio = new Domicilio();		
				Ciudad nuevaCiudad = ciudadFactory.getCiudadAleatoria(); 
				ciudadDAO.save(nuevaCiudad);
				
				nuevoDomicilio.setDireccion(faker.address().streetAddress());
				nuevoDomicilio.setNro(Integer.parseInt(faker.address().streetAddressNumber()));
				//nuevoDomicilio.setDepto;
				nuevoDomicilio.setCiudad(nuevaCiudad);		
				
				domicilioDAO.save(nuevoDomicilio);
				
				System.out.println("DOMICILIO CREADO: " + nuevoDomicilio.toString());
			}		
		}
}
