/**
 * 
 */
package com.tsti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.entidades.Ciudad;


/**
 * @author Bruno
 *
 */
public interface CiudadDAO extends JpaRepository<Ciudad, Long> {

}
