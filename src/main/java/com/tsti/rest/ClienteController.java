package tsti.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tsti.dto.ClienteResponseDTO;
import tsti.entidades.Clientes;
import tsti.excepcion.Excepcion;
import tsti.servicios.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<ClienteResponseDTO> filtrar(@RequestParam(name = "apellido", required = false) String apellido,
			@RequestParam(name = "nombre", required = false) @jakarta.validation.constraints.Size(min = 1, max = 20) String nombre)
			throws Excepcion {

		List<Clientes> clientes = service.filtrar(apellido, nombre);
		List<ClienteResponseDTO> dtos = new ArrayList<ClienteResponseDTO>();
		for (Clientes pojo : clientes) {

			dtos.add(buildResponse(pojo));
		}
		return dtos;

	}
	
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) throws Excepcion
	{
		Optional<Clientes> rta = service.getById(id);
		if(rta.isPresent())
		{
			Clientes pojo=rta.get();
			return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	

	private ClienteResponseDTO buildResponse(Clientes pojo) throws Excepcion {
		try {
			ClienteResponseDTO dto = new ClienteResponseDTO(pojo);
			// Self link
			Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class).slash(pojo.getDni()).withSelfRel();
			// Method link: Link al servicio que permitirá navegar hacia la ciudad
			// relacionada a la persona
		
			dto.add(selfLink);
			
			return dto;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}
}
