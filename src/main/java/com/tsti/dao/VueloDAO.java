package com.tsti.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.entidades.Vuelo;

/**
 * @author Bruno
 *
 */
public interface VueloDAO extends JpaRepository<Vuelo, Long> {
	
}
