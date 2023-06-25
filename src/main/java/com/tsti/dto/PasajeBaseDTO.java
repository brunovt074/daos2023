/**
 * 
 */
package com.tsti.dto;

import java.math.BigDecimal;

import com.tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author Bruno
 *
 */
public class PasajeBaseDTO {
	
	private Long nroVuelo;
	private Long dni;
	private BigDecimal precioNeto;
	private BigDecimal precioFinal;
	private TipoVuelo tipoVuelo;
	
	public PasajeBaseDTO() {
		super();
	}
			
	public PasajeBaseDTO(Long nroVuelo, Long dni, BigDecimal precio, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precio;
		this.tipoVuelo = tipoVuelo;
		this.setPrecioFinal(precioFinal);		
	}
	
	public PasajeBaseDTO(Long nroVuelo, Long dni, BigDecimal precioNeto, BigDecimal precioFinal, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precioNeto;
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
	
	public BigDecimal getPrecioNeto() {
		return precioNeto;
	}
	
	public void setPrecioNeto(BigDecimal precio) {
		this.precioNeto = precio;
	}

	public BigDecimal getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(BigDecimal precioFinal) {
		this.precioFinal = precioFinal;
	}

	public TipoVuelo getTipoVuelo() {
		return tipoVuelo;
	}

	public void setTipoVuelo(TipoVuelo tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}	
}
