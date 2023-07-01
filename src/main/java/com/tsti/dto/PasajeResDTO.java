package com.tsti.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author JOA
 *
 */
public class PasajeResDTO {
    private Long id;
    @JsonIgnore
    private ClienteResponseDTO cliente;
    @JsonIgnore
    private VueloDTO vuelo;
    private Integer numeroAsiento;
    private BigDecimal costo;
    
    //CONSTRUCTORES
    public PasajeResDTO() {
    	
    }
    public PasajeResDTO(Long id, ClienteResponseDTO cliente, VueloDTO vuelo, Integer numeroAsiento) {
        this.id = id;
        this.cliente = cliente;
        this.vuelo = vuelo;
        this.numeroAsiento = numeroAsiento;
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
}
