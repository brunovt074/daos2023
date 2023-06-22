package tsti.rest;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import tsti.dto.ClienteResponseDTO;
import tsti.entidades.Ciudad;
import tsti.entidades.Clientes;
import tsti.excepcion.Excepcion;
import tsti.excepcion.MensajeError;
import tsti.presentacion.ClienteForm;
import tsti.servicios.ICiudadService;
import tsti.servicios.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@Autowired
	private ICiudadService ciudadService;

	//busca cliente por apellido o por apellido y nombre
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
	
	
	//obtiene cliente por id
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
			System.out.print("no se encuentra id");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					
		
	}
	
	
	//Guarda cliente nuevo en la bd
	@PostMapping("/guardarCliente")
	public ResponseEntity<Object> guardar( @Valid @RequestBody ClienteForm form, BindingResult result) throws Exception
	{
		
		if(result.hasErrors())
		{
			//Dos alternativas:
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( this.formatearError(result));
		}
		
		Clientes c = form.toPojo();
		
		
		//ahora inserto el cliente
		service.insert(c);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
				.buildAndExpand(c.getDni()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

		return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
		

	}
	
	/*//edita un cliente que ya esta en la bd por dni
	@PutMapping("/{dni}")
	public ResponseEntity<Object>  actualizar(@RequestBody ClienteForm form, @PathVariable long dni) throws Exception
	{
		List<Clientes> rta = service.filtrarPorDni(dni);
		List<ClienteResponseDTO> dtos = new ArrayList<ClienteResponseDTO>();
		for (Clientes pojo : rta) {

			dtos.add(buildResponse(pojo));
		}
		
		if(rta.isEmpty())
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra la persona ");
			
		else
		{
			Clientes cliente = form.toPojo();
			
			if(cliente.getDni() != dni)//El dni es el identificador, con lo cual es el único dato que no permito modificar
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("03", "Dato no editable", "No puede modificar el dni."));
			service.update(cliente);
			return ResponseEntity.ok(buildResponse(cliente));
		}
		
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
		
		//ahora usamos la librería Jackson para pasar el objeto a json
				ObjectMapper maper = new ObjectMapper();
				String json = maper.writeValueAsString(e1);
				return json;
	}
	*/
	
	private String formatearError(BindingResult result) throws JsonProcessingException
	{
//		primero transformamos la lista de errores devuelta por Java Bean Validation
		List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
															Map<String, String> error= new HashMap<>();
															error.put(err.getField(),err.getDefaultMessage() );
															return error;
														}).collect(Collectors.toList());
		MensajeError e1=new MensajeError();
		e1.setCodigo("01");
		e1.setMensajes(errores);
		
		//ahora usamos la librería Jackson para pasar el objeto a json
		ObjectMapper maper = new ObjectMapper();
		String json = maper.writeValueAsString(e1);
		return json;
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
