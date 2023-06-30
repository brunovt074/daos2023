/*
 *CLASE CREADA PARA POBLAR LA TABLA DE CLIENTES EN ENTORNO DE DESARROLLO.  
 */
package tsti.faker;

//import java.time.LocalDate;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tsti.dao.CiudadDAO;
import tsti.dao.ClienteDAO;
import tsti.dao.DomicilioDAO;
import tsti.entidades.Clientes;
import tsti.entidades.Domicilio;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 *LA ANOTACION COMPONENT ES PARA MATCHEARLOS CON FactoryInitializer
 */
@Component
public class ClienteFactory {
	//Clase que crea datos falsos.
	private Faker faker;
	private DomicilioFactory domicilioFactory;
	//private CiudadFactory ciudadFactory;
	private Domicilio domicilio;
	@Autowired
	private ClienteDAO clienteDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired
	private CiudadDAO ciudadDAO;
	
	
	public ClienteFactory() {
		
		this.domicilioFactory = new DomicilioFactory();
		//this.ciudadFactory = new CiudadFactory();
	}
	
	public Clientes getUnPasajeroNacional(CiudadDAO ciudadDAO, DomicilioDAO domicilioDAO) {
		faker = new Faker(new Locale("es"));
		Clientes nuevoCliente = new Clientes();

		inicializarCliente(nuevoCliente, ciudadDAO, domicilioDAO, true);
		
		System.out.println("CREADO 1 PASAJERO NAC: " + nuevoCliente.toString());
		return nuevoCliente;
		
	}
	
	public void crearUnPasajeroNacional() {
		faker = new Faker(new Locale("es"));
		Clientes nuevoCliente = new Clientes();

		inicializarCliente(nuevoCliente, true);
		
		clienteDAO.save(nuevoCliente);
		System.out.println("CARGADO 1 PASAJERO NAC: " + nuevoCliente.toString());
	}
	
	public void crearPasajerosNacionales() {
		faker = new Faker(new Locale("es"));
		
		for (int i = 0; i < 100; i++){
			Clientes nuevoCliente = new Clientes();
		
			inicializarCliente(nuevoCliente, true);
			clienteDAO.save(nuevoCliente);
			System.out.println("CARGADO PASAJERO NAC: " + nuevoCliente.toString());
		}
	}
	
	public Clientes getUnPasajeroInternacional(CiudadDAO ciudadDAO, DomicilioDAO domicilioDAO) {
		faker = new Faker();
		Clientes nuevoCliente = new Clientes();

		inicializarCliente(nuevoCliente, ciudadDAO, domicilioDAO, false);
		
		System.out.println("CREADO 1 PASAJERO INTERNAC: " + nuevoCliente.toString());
		
		return nuevoCliente;
		
	}
	 public void crearUnPasajeroInternacional() {
		faker = new Faker();
		Clientes nuevoCliente = new Clientes();
		
		inicializarCliente(nuevoCliente, false);
		
		
		clienteDAO.save(nuevoCliente);
		
		System.out.println("CARGADO 1 PASAJERO INTERNAC: " + nuevoCliente.toString());
	}
	
	public void crearPasajerosInternacionales() {
		faker = new Faker();
		
		for (int i = 0; i < 100; i++){
			
			Clientes nuevoCliente = new Clientes();

			inicializarCliente(nuevoCliente, false);
			
			clienteDAO.save(nuevoCliente);
			System.out.println("CARGADO PASAJERO INTER: " + nuevoCliente.toString());
		}
	}
	
	//PARA ASISTIR A METODOS VOID
	private Clientes inicializarCliente(Clientes nuevoCliente, boolean esNacional){
		
		if(esNacional) {
			domicilio = domicilioFactory.getUnDomicilioArgentino(ciudadDAO);
			domicilioDAO.save(domicilio);			
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));			
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			nuevoCliente.setDomicilio(domicilio);
			//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
			//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
		}else {
			domicilio = domicilioFactory.getUnDomicilioAleatorio(ciudadDAO);
			domicilioDAO.save(domicilio);
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			nuevoCliente.setDomicilio(domicilio);
			nuevoCliente.setNroPasaporte(faker.number().numberBetween(1000000L, 999999999L));
			//Fecha de nacimiento con problemas de casteo (Sugerencia: cambiar a String o Timestamp)
			//nuevoCliente.setFechaNacimiento(faker.date().birthday(18, 99, "yyyy-MM-dd"));
			//nuevoCliente.vencimientoPasaporte(faker.date().future(i, null);
			System.out.println(nuevoCliente);
		}		
		
		return nuevoCliente;
	}
	
	//PARA ASISTIR A METODOS GET
	private Clientes inicializarCliente(Clientes nuevoCliente, CiudadDAO ciudadDAO, DomicilioDAO domicilioDAO, boolean esNacional){
		
		if(esNacional) {
			domicilio = domicilioFactory.getUnDomicilioArgentino(ciudadDAO);
			domicilioDAO.save(domicilio);			
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			nuevoCliente.setDomicilio(domicilio);
			nuevoCliente.setFechaNacimiento(faker.date().birthday().toLocalDateTime().toLocalDate());
		}else {
			domicilio = domicilioFactory.getUnDomicilioAleatorio(ciudadDAO);
			domicilioDAO.save(domicilio);
			//String pasaporte = "[A-Za-z]{3}\\d{6}";
			
			nuevoCliente.setDni(faker.number().numberBetween(1000000L, 59999999L));
			nuevoCliente.setNombre(faker.name().firstName());
			nuevoCliente.setApellido(faker.name().lastName());
			nuevoCliente.setTel(faker.phoneNumber().cellPhone());
			nuevoCliente.setEmail(faker.internet().emailAddress());
			nuevoCliente.setDomicilio(domicilio);
			nuevoCliente.setNroPasaporte(faker.number().numberBetween(1000000L, 999999999L));
			nuevoCliente.setFechaNacimiento(faker.date().birthday().toLocalDateTime().toLocalDate());
			nuevoCliente.setVencimientoPasaporte(faker.date().future(3650, TimeUnit.DAYS).toLocalDateTime().toLocalDate());
			
		}		
		
		return nuevoCliente;
	}

}
