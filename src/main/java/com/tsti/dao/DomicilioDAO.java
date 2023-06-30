package tsti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tsti.entidades.Domicilio;

/**
 * @author Bruno
 *
 */
public interface DomicilioDAO extends JpaRepository<Domicilio, Long> {

}
