package tsti.servicios;

import java.util.Optional;

import tsti.entidades.Clientes;
import tsti.entidades.Pasaje;
import tsti.entidades.Vuelo;
/**
 * @author JOA
 *
 */
public interface IPasajeService {
    Pasaje crearPasaje(Vuelo vuelo, Clientes pasajero, Integer nroAsiento);
	Optional<Pasaje> findById(Long id);
}
