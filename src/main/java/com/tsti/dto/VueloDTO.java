/**
 * 
 */
package com.tsti.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsti.entidades.Ciudad;
import com.tsti.entidades.Vuelo;
import com.tsti.serializer.CiudadSerializer;

/**
 * @author Bruno
 *
 */
public class VueloDTO {

	private Long nroVuelo;
	private String aerolinea;
	@JsonSerialize(using = CiudadSerializer.class)
	private Ciudad destino;
	private LocalDate fechaPartida;
	private LocalTime horaPartida;
	private int nroFilasAsientos;
	private int nroColumnasAsientos;
	private BigDecimal precioNeto;
	
	public VueloDTO() {
		// TODO Auto-generated constructor stub
	}	

	
	public VueloDTO(Vuelo pojo) {
		super();
		this.nroVuelo = pojo.getNroVuelo();
		this.aerolinea = pojo.getAerolinea();
		this.destino = pojo.getDestino();
		this.fechaPartida = pojo.getFechaPartida();
		this.horaPartida = pojo.getHoraPartida();
		this.nroFilasAsientos = pojo.getNroFilas();
		this.nroColumnasAsientos = pojo.getNroColumnas();		
	}

	public Long getNroVuelo() {
		return nroVuelo;
	}

	public void setNroVuelo(Long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}

	public String getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}

	public Ciudad getDestino() {
		return destino;
	}

	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}

	public LocalDate getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(LocalDate fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}

	public int getNroFilas() {
		return getNroFilasAsientos();
	}


	public int getNroFilasAsientos() {
		return nroFilasAsientos;
	}

	public void setNroFilas(int nroFilas) {
		setNroFilasAsientos(nroFilas);
	}


	public void setNroFilasAsientos(int nroFilas) {
		this.nroFilasAsientos = nroFilas;
	}

	public int getNroColumnas() {
		return getNroColumnasAsientos();
	}


	public int getNroColumnasAsientos() {
		return nroColumnasAsientos;
	}

	public void setNroColumnas(int nroColumnas) {
		setNroColumnasAsientos(nroColumnas);
	}

	public void setNroColumnasAsientos(int nroColumnas) {
		this.nroColumnasAsientos = nroColumnas;
	}

	public BigDecimal getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecioNeto(BigDecimal precioNeto) {
		this.precioNeto = precioNeto;
	}	
	
	
	
	
	
}
