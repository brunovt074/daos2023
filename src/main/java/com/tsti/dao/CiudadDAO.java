/**
 * 
 */
package tsti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tsti.entidades.Ciudad;


/**
 * @author Bruno
 *
 */
public interface CiudadDAO extends JpaRepository<Ciudad, Long> {

}
