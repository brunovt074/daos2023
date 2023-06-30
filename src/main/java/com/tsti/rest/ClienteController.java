package com.tsti.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import com.tsti.dto.ClienteResponseDTO;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Clientes;
import com.tsti.excepcion.Excepcion;
import com.tsti.excepcion.MensajeError;
import com.tsti.presentacion.ClienteForm;
import com.tsti.servicios.ICiudadService;
import com.tsti.servicios.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private IClienteService service;


	/**
	 * http://localhost:8081/clientes?=Lopez
	 * @param apellido
	 * @param nombre
	 * @return Lista de clietes que conincidan con el apellido o nombre buscado por parametro
	 * @throws Excepcion
	 */
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
	
	/**
	 * http://localhost:8081/clientes/4
	 * @param id
	 * @return Devuelve un solo cliente que coicida con el id buscado
	 * @throws Excepcion
	 */
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Long id) throws Excepcion
	{
			
			Optional<Clientes> rta = service.getById(id);
	
		if(rta.isPresent())
		{
			Clientes pojo = rta.get();
			
			return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
			
		}
		else
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					
		
	}
	
	
	/**
	 * Busca el cliente por dni. el path para buscar es http://localhost:8081/clientes/dni/nroDNIaBuscar
	 * @param dni
	 * @return Busca cliente por numero de dni  
	 * @throws Excepcion
	 */
	@GetMapping(value = "/dni/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClienteResponseDTO> getByDni(@PathVariable("dni") Long dni) throws Excepcion
	{
			
			Optional<Clientes> rta = service.filtrarPorDni(dni);
	
		if(rta.isPresent())
		{
			Clientes pojo = rta.get();
			
			return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
			
		}
		else
			System.out.print("no se encuentra el cliente con ese dni");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					
		
	}
	
	
	/**
	 * http://localhost:8081/clientes
	 * Guarda cliente nuevo en la bd
	 * @param form
	 * @param result
	 * @return Cliente nuevo en la bd de datos. Lo agrega al final
	 * @throws Exception
	 */
	@PostMapping("/guardarCliente")
	public ResponseEntity<Object> guardar( @Valid @RequestBody ClienteForm form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( this.formatearError(result));
		}
		
		Clientes c = form.toPojo();
		
		service.insert(c);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
				.buildAndExpand(c.getDni()).toUri(); 
		return ResponseEntity.created(location).build();
		

	}
	
	/**
	 * Actualiza cliente
	 * @param form
	 * @param dni
	 * @return Un cliente con los datos actualizados 
	 * @throws Exception
	 */
	@PutMapping("/{dni}")
	public ResponseEntity<Object>  actualizar(@RequestBody ClienteForm form, @PathVariable long dni) throws Exception
	{
		Optional<Clientes> rta = service.filtrarPorDni(dni);
		System.out.print("persona a acrualizar: "+ rta);
		if(!rta.isPresent())
			
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra la persona que desea modificar.");
			
		else
		{	Clientes clienteActualizado = form.toPojo();
			Clientes cliente = rta.get();
			
			cliente.setApellido(clienteActualizado.getApellido());
			cliente.setNombre(clienteActualizado.getNombre());
			cliente.setEmail(clienteActualizado.getEmail());
			cliente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());
			 
			if(cliente.getDni() != dni)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("03", "Dato no editable", "No puede modificar el dni."));
			service.update(cliente);
			
			return ResponseEntity.ok(buildResponse(cliente));
		}
		
	}
	
	
	/**
	 * http://localhost:8081/clientes/4567483
	 * Elimina el cliente que se le pasa por parametro como dni
	 * @param dni
	 * @return 
	 */
	@DeleteMapping("/{dni}")
	public ResponseEntity<String> eliminar(@PathVariable("dni") Long dni)
	{
		Optional<Clientes> clienteAeliminar = service.filtrarPorDni(dni);
		
		
		if(!clienteAeliminar.isPresent())
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe una persona con ese dni");
		else {
			
				System.out.print("id "+ clienteAeliminar);
				service.deleteByDni(dni);
				
			
		
		
		}
		return ResponseEntity.ok().build();
		
	}
	
	
	private String getError(String code, String err, String descr) throws JsonProcessingException
	{
		MensajeError e1=new MensajeError();
		e1.setCodigo(code);
		ArrayList<Map<String,String>> errores=new ArrayList<>();
		Map<String, String> error=new HashMap<String, String>();
		error.put(err, descr);
		errores.add(error);
		e1.setMensajes(errores);
		
		
				ObjectMapper maper = new ObjectMapper();
				String json = maper.writeValueAsString(e1);
				return json;
	}
	
	
	private String formatearError(BindingResult result) throws JsonProcessingException
	{

		List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
															Map<String, String> error= new HashMap<>();
															error.put(err.getField(),err.getDefaultMessage() );
															return error;
														}).collect(Collectors.toList());
		MensajeError e1=new MensajeError();
		e1.setCodigo("01");
		e1.setMensajes(errores);
	
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
	}


	private ClienteResponseDTO buildResponse(Clientes pojo) throws Excepcion {
		try {
			ClienteResponseDTO dto = new ClienteResponseDTO(pojo);
			
			Link selfLink = WebMvcLinkBuilder.linkTo(ClienteController.class).slash(pojo.getDni()).withSelfRel();
			
			dto.add(selfLink);
			
			return dto;
		} catch (Exception e) {
			throw new Excepcion(e.getMessage(), 500);
		}
	}
	
	
}
