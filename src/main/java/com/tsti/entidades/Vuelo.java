package com.tsti.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Establece/Actualiza/Retorna los datos de un vuelo
 * @author cecilia
 *
 */
 @Entity
public class Vuelo {
	@Id
	private int id;
	private long nroVuelo; // No podrá haber dos vuelos con el mismo nro
	private Date fecha_HoraVuelo;// ver tema fechas y aca iria con hora 
	private int nroFila;
	private int nroAsiento;
	private String tipoVuelo;
	private String Origen = "aeropuerto sauce viejo"; 
	private String Destino;// ¿en la base se crean aeropuesto para agreagar en esto? 
	private String Estado; // (registrado / reprogramado / cancelado) lo mismo quiza, se debe agregar en la base las opciones
	
	/* 
	 * El estado es autocalculado por el sistema, no puede ser establecido por
		el usuario.
		No podrá haber dos vuelos con el mismo nro.
		Una vez registrado, solo se permitirá cambiar la fecha y hora del mismo(lo cual pasa el vuelo al estado
		reprogramado) o eliminar el mismo (lo cual pasa el vuelo al estado cancelado).Tanto la reprogramación 
		como la cancelación de un vuelo dispararía la notificación automática del evento a todos los pasajeros 
		aunque por simplicidad, no se pide implementar el servicio de alertas. */
	
	
	
	public Vuelo() {
		super();
	}


	public long getNroVuelo() {
		return nroVuelo;
	}


	public void setNroVuelo(long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}


	public Date getFecha_HoraVuelo() {
		return fecha_HoraVuelo;
	}


	public void setFecha_HoraVuelo(Date fecha_HoraVuelo) {
		this.fecha_HoraVuelo = fecha_HoraVuelo;
	}


	public int getNroFila() {
		return nroFila;
	}


	public void setNroFila(int nroFila) {
		this.nroFila = nroFila;
	}


	public int getNroAsiento() {
		return nroAsiento;
	}


	public void setNroAsiento(int nroAsiento) {
		this.nroAsiento = nroAsiento;
	}


	public String getTipoVuelo() {
		return tipoVuelo;
	}


	public void setTipoVuelo(String tipoVuelo) {
		this.tipoVuelo = tipoVuelo;
	}


	public String getOrigen() {
		return Origen;
	}


	public void setOrigen(String origen) {
		Origen = origen;
	}


	public String getDestino() {
		return Destino;
	}


	public void setDestino(String destino) {
		Destino = destino;
	}


	public String getEstado() {
		return Estado;
	}


	public void setEstado(String estado) {
		Estado = estado;
	}


	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", fecha_HoraVuelo=" + fecha_HoraVuelo + ", nroFila=" + nroFila
				+ ", nroAsiento=" + nroAsiento + ", tipoVuelo=" + tipoVuelo + ", Origen=" + Origen + ", Destino="
				+ Destino + ", Estado=" + Estado + "]";
	}
	
	
	
	
	
}
