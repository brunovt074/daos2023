package com.tsti.servicios;
import java.util.List;
import java.util.Optional;

import com.tsti.entidades.Clientes;
/**
 * 
 * @author cecilia
 * 
 */
public interface IClienteService {
	
	public List<Clientes> getAll();
	
	public Optional<Clientes> getById(Long Id);
	
	public void update(Clientes c);
	
	public void insert(Clientes c) throws Exception;
	
	public void delete(Long id);
	
	public List<Clientes> filtrar(String apellido, String nombre);
	
	
}
