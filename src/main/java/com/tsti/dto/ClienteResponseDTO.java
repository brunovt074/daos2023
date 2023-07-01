package com.tsti.dto;

import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;
import com.tsti.entidades.Clientes;
/**
 * 
 * @author cecilia
 *
 */
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {
	private Long dni;
	private long id;
	private String apellido;
	private String nombre;
	private String email;
	private LocalDate fecha_nac;
	public ClienteResponseDTO() {
		super();
	}
	public ClienteResponseDTO(Clientes pojo) {
		
		super();
		this.id=pojo.getId();
		this.apellido=pojo.getApellido();
		this.nombre=pojo.getNombre();
		this.dni=pojo.getDni();
		this.email=pojo.getEmail();
		this.fecha_nac=pojo.getFechaNacimiento();	
		this.dni=pojo.getDni();		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}
	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	public Long getDni() {
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
