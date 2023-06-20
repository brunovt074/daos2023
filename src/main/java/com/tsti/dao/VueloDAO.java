package com.tsti.dao;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author Bruno
 *
 */
@Repository
public interface VueloDAO extends JpaRepository<Vuelo, Long> {
    
	List<Vuelo> findByDestino(String destino);
	List<Vuelo> findByFechaPartida(LocalDate fecha);
	List<Vuelo> findByDestinoAndFechaPartida(String destino, LocalDate fecha);
    List<Vuelo> findByTipoVuelo(TipoVuelo tipoVuelo);    
    
}

