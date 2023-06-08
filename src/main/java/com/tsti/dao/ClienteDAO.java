package com.tsti.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Collection;
import java.util.List;

import com.tsti.entidades.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
	@Query("SELECT c FROM Clientes c WHERE c.nombre like '%?1%'")
	Collection<Cliente> findClientesLike(String parte);
	
	public List<Cliente> findByApellidoOrNombre(String apellido, String nombre);
}
