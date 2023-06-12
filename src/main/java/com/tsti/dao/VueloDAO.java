package com.tsti.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsti.entidades.Vuelo;

/**
 * @author Bruno
 *
 */
@Repository
public interface VueloDAO extends JpaRepository<Vuelo, Long> {
	
}
