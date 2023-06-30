package tsti.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 * @author Joa
 *
 */
public class Pasaje {

	/*
	 *SI, NECESITAMOS.
	 * */
	
	//ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		
	@NotNull
	private Long dni;		
	@NotNull
	private Long nroVuelo;
	@NotNull
	private Integer nroAsiento;
	
	//CONSTRUCTOR
	public Pasaje() {
		super();
	}
	public Pasaje(Long dni, Long nroVuelo, Integer nroAsiento) {
		super();
	}
	//METODOS
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}
	public void setNroAsiento(Integer nroAsiento) {
		this.nroAsiento = nroAsiento;
	}
	
	public Long getDni() {
		return this.dni;
	}
	public Long getNroVuelo() {
		return this.nroVuelo;
	}
	public Integer getNroAsiento() {
		return this.nroAsiento;
	}
	
	//VALIDAR EXISTENCIA DE CLIENTE EN LA BASE DE DATOS, MEDIANTE EL DNI.
	
	//VALIDAR EXISTENCIA DEL VUELO EN LA BASE DE DATOS, MEDIANTE EL NUMERO DE VUELO.
	
	
}
