package tsti.servicios;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tsti.entidades.Ciudad;

@Service
public interface ICiudadService {
	public java.util.List<Ciudad> findAll();

	public Optional<Ciudad> getById(Long id);
}