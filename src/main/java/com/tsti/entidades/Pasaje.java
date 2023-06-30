package com.tsti.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
/**
 * @author JOA
 *
 */

@Entity
public class Pasaje {
    //ATRIBUTOS
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vuelo_id")
    private Vuelo vuelo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pasajero_id")
    private Clientes pasajero;

	 
    private Integer numeroAsiento;

    //CONSTRUCTORES
    public Pasaje() {
    }
    public Pasaje(Vuelo vuelo, Clientes pasajero, Integer nroAsiento) {
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.numeroAsiento = nroAsiento;//TODO CAMBIAR POR RANDOM.
    }    

    //GETTERS
    public Long getId() {
        return id;
    }
    public Long getDniCliente() {
        return this.pasajero.getDni();
    }
    public Long getNumeroVuelo() {
        return this.vuelo.getNroVuelo();
    }
    public Integer getNumeroAsiento() {
        return numeroAsiento;
    }
	public Clientes getPasajero() {
		return pasajero;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
    

    //SETTERS
    public void setNumeroAsiento(Integer numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }
	public void setPasajero(Clientes pasajero) {
		this.pasajero = pasajero;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
}
