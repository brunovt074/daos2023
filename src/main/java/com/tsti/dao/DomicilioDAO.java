package com.tsti.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.entidades.Domicilio;

/**
 * @author Bruno
 *
 */
public interface DomicilioDAO extends JpaRepository<Domicilio, Long> {

}
