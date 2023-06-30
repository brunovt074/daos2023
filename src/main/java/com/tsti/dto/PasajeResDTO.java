package com.tsti.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tsti.servicios.TasaServiceImpl;
import com.tsti.entidades.Vuelo.TipoVuelo;
import com.tsti.servicios.CotizacionServiceImpl;

/**
 * @author JOA
 *
 */
public class PasajeResDTO {
    private Long id;
    private ClienteResponseDTO cliente;
    private VueloDTO vuelo;
    private Integer numeroAsiento;
    private BigDecimal costo;
    private TasaServiceImpl tasaService;
    
    //CONSTRUCTORES
    public PasajeResDTO() {
    	
    }
    public PasajeResDTO(Long id, ClienteResponseDTO cliente, VueloDTO vuelo, Integer numeroAsiento) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.numeroAsiento = numeroAsiento;
        this.tasaService = new TasaServiceImpl();
        this.costo = vuelo.getPrecioNeto();
    }

    
    //GETTERS
    public Long getId() {
        return id;
    }
    public ClienteResponseDTO getCliente() {
        return cliente;
    }
    public VueloDTO getVuelo() {
        return vuelo;
    }
    public Integer getNumeroAsiento() {
    	return numeroAsiento;
    }
    public BigDecimal getCosto() {
    	return costo;
    }
    
    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }
    public void setVuelo(VueloDTO vuelo) {
        this.vuelo = vuelo;
    }
    public void setNumeroAsiento(Integer numeroAsiento) {
		this.numeroAsiento = numeroAsiento;
    }
    
    //OTROS METODOS
    public BigDecimal setCosto() {
    	BigDecimal precioNeto = vuelo.getPrecioNeto();			
		BigDecimal tasa = tasaService.getTasa(vuelo.getTipoVuelo());			
		BigDecimal precioFinal = precioNeto.add(tasa);
		if(vuelo.getTipoVuelo() == TipoVuelo.INTERNACIONAL){
			
			CotizacionServiceImpl cotizacionService = new CotizacionServiceImpl();
			precioFinal =  precioFinal.multiply(cotizacionService.getCotizacionDolarOficial()).setScale(2, RoundingMode.HALF_DOWN);			
		}
    	return null;
    }
}
