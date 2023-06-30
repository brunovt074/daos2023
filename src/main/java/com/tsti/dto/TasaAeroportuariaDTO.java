/**
 * 
 */
package tsti.dto;

import java.math.BigDecimal;

/**
 * @author Bruno
 *
 */
public class TasaAeroportuariaDTO {
	
	BigDecimal tasa;
	String moneda;
	
	public TasaAeroportuariaDTO() {
		super();		
	}

	public TasaAeroportuariaDTO(BigDecimal tasa, String moneda) {
		super();
		this.tasa = tasa;
		this.moneda = moneda;
	}

	public BigDecimal getTasa() {
		return tasa;
	}

	public void setTasa(BigDecimal tasa) {
		this.tasa = tasa;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}	

}
