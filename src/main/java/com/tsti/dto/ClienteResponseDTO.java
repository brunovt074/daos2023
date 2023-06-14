package tsti.dto;

import java.util.Date;

import org.hibernate.annotations.FetchProfile.FetchOverride;
import org.springframework.hateoas.RepresentationModel;

import tsti.entidades.Clientes;

/**
 * 
 * @author cecilia
 *
 */
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {
	private	Long id;
	private Long dni;
	
	private String apellido;
	private String nombre;
	private String email;
	private Date fecha_nac;
	
	public ClienteResponseDTO(Clientes pojo) {
		
		super();
		this.id=pojo.getId();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		this.email=pojo.getEmail();
		this.fecha_nac=pojo.getFechaNacimiento();
		
		
		
	}
	
	
	


	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public Date getFecha_nac() {
		return fecha_nac;
	}





	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}





	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
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
		return   dni+" - "+ nombre +", "+ apellido;
	}
	
	
}
