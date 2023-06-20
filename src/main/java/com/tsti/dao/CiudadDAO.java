/**
 * 
 */
package com.tsti.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsti.entidades.Ciudad;



/**
 * @author Bruno
 *
 */

@Repository
public interface CiudadDAO extends JpaRepository<Ciudad, Long> {

	
	
}
