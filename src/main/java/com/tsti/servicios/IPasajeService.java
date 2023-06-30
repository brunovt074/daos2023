package com.tsti.servicios;

import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Clientes;
import com.tsti.entidades.Pasaje;
import com.tsti.entidades.Vuelo;

public interface IPasajeService {
    Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero);
	Optional<Pasaje> findById(Long id);
}
