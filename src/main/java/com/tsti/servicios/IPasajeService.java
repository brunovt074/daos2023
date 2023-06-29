package com.tsti.servicios;

import com.tsti.entidades.Clientes;
import com.tsti.entidades.Pasaje;
import com.tsti.entidades.Vuelo;

public interface IPasajeService {
    Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero);
    Pasaje consultarPasaje(Long pasajeId);
}
