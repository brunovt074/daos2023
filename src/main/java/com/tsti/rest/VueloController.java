package com.tsti.rest;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.entidades.Vuelo;
import com.tsti.servicios.VueloServiceImpl;

@RestController
@RequestMapping("/vuelos")
public class VueloController {
	
	@Autowired
	VueloServiceImpl vueloService;
	
	@GetMapping()
	public ResponseEntity<HashSet<Vuelo>> getAll(){
		
		HashSet<Vuelo> vuelos = vueloService.getAll();
		
		return new ResponseEntity<HashSet<Vuelo>>(vuelos, HttpStatus.OK);
		
	}
}
