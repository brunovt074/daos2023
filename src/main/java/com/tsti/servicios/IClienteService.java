package tsti.servicios;
import java.util.List;
import java.util.Optional;

import tsti.entidades.Clientes;
/**
 * 
 * @author cecilia
 * 
 */
public interface IClienteService {
	
	public List<Clientes> getAll();
	
	public Optional<Clientes> getById(Long id);
	
	
	public void update(Clientes c);
	
	public void insert(Clientes c) throws Exception;
	
	public void delete(Long id);
	
	public List<Clientes> filtrar(String apellido, String nombre);
	
	public List<Clientes> filtrarPorDni(Long dni);
	
}
