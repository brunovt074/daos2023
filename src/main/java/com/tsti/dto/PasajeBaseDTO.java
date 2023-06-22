/**
 * 
 */
package com.tsti.dto;

import com.tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author Bruno
 *
 */
public class PasajeBaseDTO {
	
	private Long nroVuelo;
	private Long dni;
	private double precioNeto;
	private double precioFinal;
	private TipoVuelo tipoVuelo;
	
	public PasajeBaseDTO() {
		super();
	}
			
	public PasajeBaseDTO(Long nroVuelo, Long dni, Double precio, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precio;
		this.tipoVuelo = tipoVuelo;
		this.setPrecioFinal(precioFinal);		
	}
	
	public PasajeBaseDTO(Long nroVuelo, Long dni, Double precio, double precioFinal, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precio;
		this.tipoVuelo = tipoVuelo;
		this.precioFinal = precioFinal ;		
	}



	public Long getNroVuelo() {
		return nroVuelo;
	}

	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public Double getPrecio() {
		return getPrecioNeto();
	}

	public Double getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecio(double precio) {
		setPrecioNeto(precio);
	}

	public void setPrecioNeto(double precio) {
		this.precioNeto = precio;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double precioFinal) {
		this.precioFinal = precioFinal;
	}

	public TipoVuelo getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(TipoVuelo tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}	
	
	
}
