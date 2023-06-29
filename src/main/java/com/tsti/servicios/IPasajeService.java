package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Clientes;
import com.tsti.entidades.Pasaje;
import com.tsti.entidades.Vuelo;

public interface IPasajeService {
    Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero);
    Pasaje consultarPasaje(Long pasajeId);
	List<Pasaje> obtenerPasajesPorPasajero(Optional<Clientes> pasajero);
}
