/*
 * ESTA CLASE ESTA CREADA PARA INICIALIZAR EL CIUDAD FACTORY AL PRINCIPIO DE
 * LA APP Y ASI PODER POBLAR LA BD PARA ENTORNO DE DESARROLLO.
 * */
package com.tsti.faker;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CiudadFactoryInitializer {

    private final CiudadFactory ciudadFactory;

    public CiudadFactoryInitializer(CiudadFactory ciudadFactory) {
        this.ciudadFactory = ciudadFactory;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
    	ciudadFactory.crearCiudadArgentina();
        ciudadFactory.crearCiudadAleatoria();
    }
}

  

