package com.tsti.dto;

import java.math.BigDecimal;

import com.tsti.entidades.Clientes;
import com.tsti.entidades.Vuelo;
import com.tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author JOA
 *
 */
public class PasajeDTO {
	private Clientes pasajero;
	private Vuelo vuelo;
	private Long nroVuelo;
	private Long dni;
    private Integer numeroAsiento;
	private BigDecimal precioNeto;
	private BigDecimal tasa;
	private BigDecimal precioFinal;
	private TipoVuelo tipoVuelo;
	
	
	//CONSTRUCTORES
	public PasajeDTO() {
		super();
	}
	public PasajeDTO(Vuelo vuelo, Clientes pasajero, BigDecimal precio, TipoVuelo tipoVuelo) {
		super();
		this.vuelo = vuelo;
		this.pasajero = pasajero;
		this.nroVuelo = this.vuelo.getNroVuelo();
		this.dni = this.pasajero.getDni();
		this.precioNeto = precio;
		this.tipoVuelo = tipoVuelo;
		this.setPrecioFinal(precioFinal);		
	}
	public PasajeDTO(Vuelo vuelo, Clientes pasajero, BigDecimal precioNeto, BigDecimal precioFinal, TipoVuelo tipoVuelo) {
		super();
		this.vuelo = vuelo;
		this.pasajero = pasajero;
		this.nroVuelo = this.vuelo.getNroVuelo();
		this.dni = this.pasajero.getDni();
		this.precioNeto = precioNeto;
		this.tipoVuelo = tipoVuelo;
		this.precioFinal = precioFinal ;		
	}


	//GETTERS
	public Long getNroVuelo() {
		return nroVuelo;
	}
	public Long getDni() {
		return dni;
	}
	public BigDecimal getPrecioNeto() {
		return precioNeto;
	}
	public BigDecimal getPrecioFinal() {
		return precioFinal;
	}
	public TipoVuelo getTipoVuelo() {
		return tipoVuelo;
	}
	public BigDecimal getTasa() {
		return tasa;
	}
	
	
	//SETTERS
	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	public void setPrecioNeto(BigDecimal precio) {
		this.precioNeto = precio;
	}
	public void setPrecioFinal(BigDecimal precioFinal) {
		this.precioFinal = precioFinal;
	}
	public void setTipoVuelo(TipoVuelo tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}
	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}	
} 
