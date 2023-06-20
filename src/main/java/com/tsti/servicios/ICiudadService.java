package tsti.servicios;

import java.util.Optional;

import tsti.entidades.Ciudad;

public interface ICiudadService {
	public java.util.List<Ciudad> findAll();

	public Optional<Ciudad> getById(Long id);

}
