/*
 * ESTA CLASE ESTA CREADA PARA INICIALIZAR EL CIUDAD FACTORY AL PRINCIPIO DE
 * LA APP Y ASI PODER POBLAR LA BD PARA ENTORNO DE DESARROLLO.
 * */
package com.tsti.faker;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
    	//creando ciudades//    	ciudadFactory.crearCiudadArgentina();//    	ciudadFactory.crearCiudadesArgentina();//        ciudadFactory.crearCiudadAleatoria();
//        ciudadFactory.crearCiudadesAleatoria();
        
        //creando clientes
        //clienteFactory.crearUnPasajeroNacional();
        clienteFactory.crearPasajerosNacionales();
        //clienteFactory.crearUnPasajeroInternacional();
        //clienteFactory.crearPasajerosInternacionales();
        
        //creando domicilios
        //domicilioFactory.crearUnDomicilioArgentino();
        //domicilioFactory.crearUnDomicilioAleatorio();
        //domicilioFactory.crearDomiciliosArgentinos();
        //domicilioFactory.crearDomiciliosAleatorios();
        
        //creando vuelos
        //vueloFactory.crearVueloNacionalOrigenLocal();
    }
}

  

