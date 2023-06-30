/**
 * 
 */
package tsti.dto;

import java.math.BigDecimal;

import tsti.entidades.Vuelo.TipoVuelo;

/**
 * @author Bruno
 *
 */
public class PasajeDTO {
	
	private Long nroVuelo;
	private Long dni;
	private BigDecimal precioNeto;
	private BigDecimal tasa;
	private BigDecimal precioFinal;
	private TipoVuelo tipoVuelo;
	
	public PasajeDTO() {
		super();
	}
			
	public PasajeDTO(Long nroVuelo, Long dni, BigDecimal precio, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precio;
		this.tipoVuelo = tipoVuelo;				
	}
	
	public PasajeDTO(Long nroVuelo, Long dni, BigDecimal precioNeto, BigDecimal tasa, 
									BigDecimal precioFinal, TipoVuelo tipoVuelo) {
		super();
		this.nroVuelo = nroVuelo;
		this.dni = dni;
		this.precioNeto = precioNeto;
		this.tasa = tasa;
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

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}	
} 
