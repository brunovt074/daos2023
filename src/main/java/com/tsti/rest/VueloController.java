package com.tsti.rest;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.servicios.IVueloService;

@RestController
public class VueloController {

    private final IVueloService vueloService;

    @Autowired
    public VueloController(IVueloService vueloService) {
        this.vueloService = vueloService;
    }
    
    @GetMapping("/vuelos")
    public ResponseEntity<List<Vuelo>> getVuelosByDestinoAndFechaPartida(
        @RequestParam("destino") String destino,
        @RequestParam("fechaPartida") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fechaPartida) {
            
        List<Vuelo> vuelos = vueloService.findByDestinoAndFechaPartida(destino, fechaPartida);

        if (vuelos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(vuelos);
        }
    }    
}



