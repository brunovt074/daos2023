package com.tsti.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsti.dao.ClienteDAO;
import com.tsti.dao.VueloDAO;
import com.tsti.dto.PasajeDTO;
import com.tsti.entidades.Clientes;
import com.tsti.entidades.Pasaje;
import com.tsti.servicios.PasajeServiceImpl;

@RestController
public class PasajeController {

	private final PasajeServiceImpl pasajeServiceImpl;
	@Autowired
	private VueloDAO vueloDAO;
	@Autowired
	private ClienteDAO clienteDAO;

    public PasajeController(PasajeServiceImpl pasajeServiceImpl) {
        this.pasajeServiceImpl = pasajeServiceImpl;
    }
    
    @GetMapping("/pasajes/{idCliente}")
    public ResponseEntity<List<Pasaje>> obtenerPasajesPorPasajero(@PathVariable Long id) {
        //OBTENER PASAJERO MEDIANTE SU ID
    	Optional<Clientes> pasajero=clienteDAO.findById(id);
    	
    	// OBTENER LOS PASAJES DEL PASAJERO
    	List<Pasaje> pasajes = pasajeServiceImpl.obtenerPasajesPorPasajero(pasajero);
        return ResponseEntity.ok(pasajes);
    }
    
    @PostMapping("/pasajes")
    public ResponseEntity<Pasaje> crearPasaje(@RequestBody PasajeDTO pasajeDTO) {
        try {
            Pasaje pasaje = pasajeServiceImpl.crearPasaje(pasajeDTO.getVuelo(), pasajeDTO.getPasajero());
            return ResponseEntity.status(HttpStatus.CREATED).body(pasaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//	private Class<?> obtenerPasaje(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}