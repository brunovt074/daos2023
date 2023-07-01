
/*
 * ESTA CLASE ESTA CREADA PARA INICIALIZAR EL CIUDAD FACTORY AL PRINCIPIO DE
 * LA APP Y ASI PODER POBLAR LA BD PARA ENTORNO DE DESARROLLO.
 * */
package com.tsti.faker;

import java.util.Random;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.tsti.entidades.Vuelo.EstadoVuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

@Component
public class FactoryInitializer {

    private final CiudadFactory ciudadFactory;
    private final ClienteFactory clienteFactory;
    private final DomicilioFactory domicilioFactory;
    private final VueloFactory vueloFactory;
    
    public FactoryInitializer(CiudadFactory ciudadFactory,ClienteFactory clienteFactory,
    						DomicilioFactory domicilioFactory, VueloFactory vueloFactory) {
        
    	this.ciudadFactory = ciudadFactory;
        this.clienteFactory = clienteFactory;
        this.domicilioFactory = domicilioFactory;
        this.vueloFactory = vueloFactory; 
    }  
    

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
    	    	
//    	creadorVuelos();
    	
    }
    
    private void creadorVuelos() {
    	       
        //creando vuelos 
    	//PARAMETROS:(nroVuelos, nroPasajeros, estado, tipo)
    	
    	//VUELO VACIO
    	//crearVuelos(3, 0, EstadoVuelo.REGISTRADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(3, 0, EstadoVuelo.REGISTRADO, TipoVuelo.NACIONAL);
    	    	    	
//    	//REGISTRADOS
    	//crearVuelos(3, 0, EstadoVuelo.REGISTRADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(3, 0, EstadoVuelo.REGISTRADO, TipoVuelo.NACIONAL);
    	
    	//CANCELADOS
    	//crearVuelos(6, 0, EstadoVuelo.CANCELADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(6, 0, EstadoVuelo.CANCELADO, TipoVuelo.NACIONAL);
////    //REPROGRAMADOS
    	//crearVuelos(1, 0, EstadoVuelo.REPROGRAMADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(1, 0, EstadoVuelo.REPROGRAMADO, TipoVuelo.NACIONAL);
////    //DEMORADOS
    	//crearVuelos(1, 0, EstadoVuelo.DEMORADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(1, 0, EstadoVuelo.DEMORADO, TipoVuelo.NACIONAL);
    	
    	//VUELOS NRO PASAJEROS ALEATORIOS
    	crearVuelosNroPasajerosAleatorios(2, EstadoVuelo.REGISTRADO, TipoVuelo.INTERNACIONAL);
    	crearVuelosNroPasajerosAleatorios(2, EstadoVuelo.REGISTRADO, TipoVuelo.NACIONAL);
//    	   	
    	//crearVuelosNroPasajerosAleatorios(1, EstadoVuelo.REPROGRAMADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelosNroPasajerosAleatorios(1, EstadoVuelo.REPROGRAMADO, TipoVuelo.NACIONAL);
//    	
    	//crearVuelosNroPasajerosAleatorios(1, EstadoVuelo.DEMORADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelosNroPasajerosAleatorios(1, EstadoVuelo.DEMORADO, TipoVuelo.NACIONAL);
    	
    	//VUELO LLENO
    	//crearVuelos(1, 90, EstadoVuelo.REGISTRADO, TipoVuelo.INTERNACIONAL);
    	//crearVuelos(1, 90, EstadoVuelo.REGISTRADO, TipoVuelo.NACIONAL);
    	    	
    	//crearVuelos(1, 90, EstadoVuelo.REPROGRAMADO, TipoVuelo.INTERNACIONAL);    	
    	//crearVuelos(1, 90, EstadoVuelo.REPROGRAMADO, TipoVuelo.NACIONAL);
    	
    	//crearVuelos(1, 90, EstadoVuelo.DEMORADO, TipoVuelo.INTERNACIONAL);    	
    	//crearVuelos(1, 90, EstadoVuelo.DEMORADO, TipoVuelo.NACIONAL);   	
    	
    	System.out.println("--Ejecucion de inserciones terminada.--");
    	
    }
    
    private void crearVuelos(int nroVuelos, int nroPasajeros, EstadoVuelo estado, TipoVuelo tipo) {
    		
    	for(int i = 0; i < nroVuelos; i++) {
    	    		
       			vueloFactory.crearVueloOrigenLocal(nroPasajeros, estado, tipo);
        			
       	}       	
    }
    
    private void crearVuelosNroPasajerosAleatorios(int nroVuelos, EstadoVuelo estado, TipoVuelo tipo) {
    	
    	Random  random = new Random();
        
        int pasajerosRandom = random.nextInt(91);  
 		
		for(int i = 0; i < nroVuelos; i++) {
 		
			vueloFactory.crearVueloOrigenLocal(pasajerosRandom, estado, tipo);
 			
		}
    	
    }
    
    private void crearCiudades() {
    	
    	//creando ciudades
    	//ciudadFactory.crearCiudadArgentina();
    	//ciudadFactory.crearCiudadesArgentina();
        //ciudadFactory.crearCiudadAleatoria();
        //ciudadFactory.crearCiudadesAleatoria();
    }
    
    private void crearClientes() {
    	
    	//creando clientes
        //clienteFactory.crearUnPasajeroNacional();
        //clienteFactory.crearPasajerosNacionales();
        //clienteFactory.crearUnPasajeroInternacional();
        //clienteFactory.crearPasajerosInternacionales();
    }
    
    private void crearDomicilios() {
    	
    	//creando domicilios
        //domicilioFactory.crearUnDomicilioArgentino();
        //domicilioFactory.crearUnDomicilioAleatorio();
        //domicilioFactory.crearDomiciliosArgentinos();
        //domicilioFactory.crearDomiciliosAleatorios();
    	
    }
}
