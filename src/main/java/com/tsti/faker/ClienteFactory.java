/*
 *CLASE CREADA PARA POBLAR LA TABLA DE CLIENTES EN ENTORNO DE DESARROLLO.  
 */
package com.tsti.faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsti.dao.ClienteDAO;
import com.tsti.entidades.Clientes;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 */
@Component
public class ClienteFactory {
	//Clase que crea datos falsos.
	private final Faker faker;
	@Autowired
	private ClienteDAO clienteDAO;
	
	public ClienteFactory() {
		this.faker = new Faker();
	}
	
	public void crearUnPasajeroNacional() {
		
		Clientes nuevoCliente = new Clientes();
		//String pasaporte = "[A-Za-z]{3}\\d{6}";
		
		nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
		nuevoCliente.setNombre(faker.name().firstName());
		nuevoCliente.setApellido(faker.name().lastName());
		nuevoCliente.setTel(faker.phoneNumber().cellPhone());
		nuevoCliente.setEmail(faker.internet().emailAddress());
		//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
		//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
		clienteDAO.save(nuevoCliente);
		System.out.println("CARGADO 1 PASAJERO NAC: " + nuevoCliente.toString());
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
			//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
			//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
			clienteDAO.save(nuevoCliente);
			System.out.println("CARGADO PASAJERO NAC: " + nuevoCliente.toString());
		}
	}
	
	public void crearUnPasajeroInternacional() {
		
		Clientes nuevoCliente = new Clientes();
		//String pasaporte = "[A-Za-z]{3}\\d{6}";
		//Crear domicilio
		
		nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
		nuevoCliente.setNombre(faker.name().firstName());
		nuevoCliente.setApellido(faker.name().lastName());
		nuevoCliente.setTel(faker.phoneNumber().cellPhone());
		nuevoCliente.setEmail(faker.internet().emailAddress());
		//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
		//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
		//Se deberia castear pasaporte a String.
		nuevoCliente.setNroPasaporte(faker.number().numberBetween(1000000L, 999999999L));
		//nuevoCliente.vencimientoPasaporte(faker.date().future(i, null);
		clienteDAO.save(nuevoCliente);
		
		System.out.println("CARGADO 1 PASAJERO INTERNAC: " + nuevoCliente.toString());
	}
	
	public void crearPasajerosInternacionales() {
		
		for (int i = 0; i < 100; i++){
			
			Clientes nuevoCliente = new Clientes();
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			//nuevoCliente.setDomicilio(domicilio);
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
			//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
			nuevoCliente.setNroPasaporte(faker.number().numberBetween(1000000L, 999999999L));
			//nuevoCliente.vencimientoPasaporte(faker.date().future(i, null);
			clienteDAO.save(nuevoCliente);
			System.out.println("CARGADO PASAJERO INTER: " + nuevoCliente.toString());
		}
	}

}
