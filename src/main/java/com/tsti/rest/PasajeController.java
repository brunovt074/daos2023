package com.tsti.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dto.PasajeDTO;
import com.tsti.entidades.Pasaje;
import com.tsti.servicios.ClienteServiceImpl;
import com.tsti.servicios.PasajeServiceImpl;

@RestController
public class PasajeController {

	private final PasajeServiceImpl pasajeServiceImpl;
	private final ClienteServiceImpl clienteServiceImpl;

    public PasajeController(PasajeServiceImpl pasajeServiceImpl) {
        this.pasajeServiceImpl = pasajeServiceImpl;
		this.clienteServiceImpl = new ClienteServiceImpl();
    }
    
}