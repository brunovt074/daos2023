package com.tsti.servicios;
import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Cliente;
/**
 * 
 * @author cecilia
 * 
 */
public interface ClienteService {
	
	public List<Cliente> getAll();
	
	public Optional<Cliente> getById(Long Id);
	
	public void update(Cliente c);
	
	public void insert(Cliente c) throws Exception;
	
	public void delete(Long id);
	
	public List<Cliente> filtrar(String apellido, String nombre);
	
	
}
