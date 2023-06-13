package tsti.dto;

import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.springframework.hateoas.RepresentationModel;

import tsti.entidades.Clientes;

/**
 * 
 * @author cecilia
 *
 */
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {
	private Long dni;
	private Long nroPasaporte;
	private String apellido;
	private String nombre;
	
	public ClienteResponseDTO(Clientes pojo) {
		
		super();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		this.nroPasaporte = pojo.getNroPasaporte();
		
		
	}
	
	
	public Long getNroPasaporte() {
		return nroPasaporte;
	}


	public void setNroPasaporte(Long nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}


	public Long getDni() {
		return dni;
	}


	public void setDni(Long dni) {
		this.dni = dni;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public String toString() {
		return nroPasaporte + "-" + dni+" - "+ nombre +", "+ apellido;
	}
	
	
}
