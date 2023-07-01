package com.tsti.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import com.tsti.entidades.Clientes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteDAO extends JpaRepository<Clientes, Long> {
    @Query("SELECT c FROM Clientes c WHERE c.nombre like '%?1%'")
    Collection<Clientes> findClientesLike(String parte);

    public List<Clientes> findByApellidoOrNombre(String apellido, String nombre);

    public Optional<Clientes> findByDni(Long dni);

    @Transactional
    public void deleteByDni(Long dni);
}
