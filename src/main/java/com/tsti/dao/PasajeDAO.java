package com.tsti.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tsti.entidades.Pasaje;

/**
 * @author JOA
 *
 */
public interface PasajeDAO extends JpaRepository<Pasaje, Long> {
//	@Query("SELECT pasaje FROM Pasaje pasaje WHERE pasaje.vuelo_id = :vuelo")
//    List<Pasaje> findByNumeroVuelo(String vuelo_id);
//	
//	@Query("SELECT pasaje FROM Pasaje pasaje WHERE pasaje.pasajero_id = :pasajero")
//	List<Pasaje> findByCliente(String pasajero_id);
}