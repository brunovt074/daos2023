/*
* CLASE CREADA PARA POBLACION DE TABLA DOMICILIOS 
 */
package com.tsti.faker;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.DomicilioDAO;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 */
public class DomicilioFactory {
	
	private final Faker faker;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired
	private CiudadDAO ciudadDAO;
	
	public DomicilioFactory() {
		this.faker = new Faker(new Locale("es"));
	}

}
