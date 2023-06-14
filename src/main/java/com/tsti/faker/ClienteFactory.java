/*
 *CLASE CREADA PARA POBLAR LA TABLA DE CLIENTES EN ENTORNO DE DESARROLLO.  
 */
package com.tsti.faker;

import org.springframework.beans.factory.annotation.Autowired;

import com.tsti.dao.CiudadDAO;
import com.tsti.dao.ClienteDAO;
import com.tsti.entidades.Clientes;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 */
public class ClienteFactory {
	//Clase que crea datos falsos.
	private final Faker faker;
	@Autowired
	private ClienteDAO clienteDAO;
	
	public ClienteFactory() {
		this.faker = new Faker();
	}
	
	public void crearPasajerosNacionales() {
		for (int i = 0; i < 100; i++){
			
			Clientes nuevoCliente = new Clientes();
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String)
			//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
			nuevoCliente.setNroPasaporte(faker.number().numberBetween(1000000L, 999999999L));
			//nuevoCliente.vencimientoPasaporte(faker.date().future(i, null);
		}
	}

}
