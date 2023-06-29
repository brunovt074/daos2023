package com.tsti.presentacion;

import java.util.Date;

import com.tsti.entidades.Clientes;

import jakarta.validation.constraints.NotNull;

public class ClienteForm {
	@NotNull
	private long dni; 
	private String nombre;
	@NotNull
	private String apellido;
	private String domicilio;
	private String email;
	private Date fechaNacimiento;
	private long nroPasaporte;
	private Date vencimientoPasaporte;
	private Long idCiudad;
	
	
	public Long getIdCiudad() {
		return idCiudad;
	}
	
	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public long getDni() {
		return dni;
	}
	public void setDni(long dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public long getNroPasaporte() {
		return nroPasaporte;
	}
	public void setNroPasaporte(long nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}
	public Date getVencimientoPasaporte() {
		return vencimientoPasaporte;
	}
	public void setVencimientoPasaporte(Date vencimientoPasaporte) {
		this.vencimientoPasaporte = vencimientoPasaporte;
	}
	
	public Clientes toPojo() {
		Clientes c = new Clientes();
		c.setDni(this.getDni());
		c.setApellido(this.getApellido());
		c.setNombre(this.getNombre());
		c.setEmail(this.getEmail());
		c.setFechaNacimiento(this.getFechaNacimiento());
		c.setNroPasaporte(this.getNroPasaporte());
		
		
		return c;
	}
}
