package tsti.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tsti.entidades.Clientes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;



public interface ClienteDAO extends JpaRepository<Clientes, Long> {
	@Query("SELECT c FROM Clientes c WHERE c.nombre like '%?1%'")
	Collection<Clientes> findClientesLike(String parte);
	
	public List<Clientes> findByApellidoOrNombre(String apellido, String nombre);
	
	@Query("SELECT dni FROM Clientes c WHERE c.dni=?1")
	public Optional<Clientes> findByDni(Long dni);
	
}
