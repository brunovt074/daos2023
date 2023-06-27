package com.tsti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Bruno
 *
 */
public class CotizacionDolarDTO {
	
	private CasaDTO casa;
	
	public CasaDTO getCasa(){
		
		return casa;
	}
		
	public void setCasa(CasaDTO casa){
		
		this.casa = casa;
		
	}
	
//Se crea una clase interna porque los atributos del JSON de rta de la api-dolar estan anidados.
	public static class CasaDTO {
//	    private BigDecimal compra;
		private String venta;
//	    private String agencia;
		private String nombre;
//	    private BigDecimal variacion;
//	    private boolean ventaCero;
//	    private String decimales;
		
		@JsonProperty("venta")
		public String getVenta() {
			return venta;
		}
		@JsonProperty("venta")
		public void setVenta(String venta) {
			this.venta = venta;
		}
		@JsonProperty("nombre")
		public String getNombre() {
			return nombre;
		}
		@JsonProperty("nombre")
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}		
	}
}

