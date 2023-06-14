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
    
    public FactoryInitializer(CiudadFactory ciudadFactory,ClienteFactory clienteFactory) {
        this.ciudadFactory = ciudadFactory;
        this.clienteFactory = clienteFactory; 
    }  
    

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
    	//creando ciudades
    	ciudadFactory.crearCiudadArgentina();
    	ciudadFactory.crearCiudadesArgentina();
        ciudadFactory.crearCiudadAleatoria();
        ciudadFactory.crearCiudadesAleatoria();
        
        //creando clientes
        clienteFactory.crearUnPasajeroInternacional();
        clienteFactory.crearPasajerosNacionales();
        clienteFactory.crearUnPasajeroInternacional();
        clienteFactory.crearPasajerosInternacionales();
        
        //creando domicilios
        
        //
    }
}

  

