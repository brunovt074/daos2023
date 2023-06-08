package com.tsti.entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Establece/Actualiza/Retorna los datos de un vuelo
 * @author cecilia
 *
 */
@Entity
@Table(name = "vuelos")
public class Vuelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	@Column(unique = true)
	@NotNull
	private Long nroVuelo; // No podrá haber dos vuelos con el mismo nro
	@NotNull
	private Date fecha_HoraVuelo;// ver tema fechas y aca iria con hora 
	@NotNull
	private Integer nroFila;
	@NotNull
	private Integer nroAsiento;
	@NotNull
	private String tipoVuelo;
	@ManyToOne
	@JoinColumn(name = "origen_id")
	private Ciudad origen;
	@ManyToOne
	@JoinColumn(name = "destino_id")	
	private Ciudad destino;// creada la entidad Ciudad 
	@NotNull
	private String estado; // (registrado / reprogramado / cancelado) lo mismo quiza, se debe agregar en la base las opciones
	@ManyToOne
	@JoinColumn(name = "pasajero_id")	
	private Cliente pasajero;
	/* 
	 * El estado es autocalculado por el sistema, no puede ser establecido por
		el usuario.
		No podrá haber dos vuelos con el mismo nro.
		Una vez registrado, solo se permitirá cambiar la fecha y hora 
		del mismo(lo cual pasa el vuelo al estado reprogramado) 
		o eliminar el mismo (lo cual pasa el vuelo al estado cancelado).
		Tanto la reprogramación como la cancelación de un vuelo dispararía la
		notificación automática del evento a todos los pasajeros aunque por 
		simplicidad, no se pide implementar el servicio de alertas. */
	
	
	//CONSTRUCTOR
	public Vuelo() {
		super();
	}

	//METODOS
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


	public Ciudad getOrigen() {
		return origen;
	}


	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}


	public Ciudad getDestino() {
		return destino;
	}


	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", fecha_HoraVuelo=" + fecha_HoraVuelo + ", nroFila=" + nroFila
				+ ", nroAsiento=" + nroAsiento + ", tipoVuelo=" + tipoVuelo + ", Origen=" + origen + ", Destino="
				+ destino + ", Estado=" + estado + "]";
	}
	
	
	
	
	
}
