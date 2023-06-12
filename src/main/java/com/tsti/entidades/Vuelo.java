package com.tsti.entidades;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	@Column(name = "nro_vuelo", unique = true)
	@NotNull	
	private Long nroVuelo;
	//Se opto por separar fechas y horas para poder buscar los vuelos 
	//por fechas y tratar de simplificar el manejo de las mismas.
	@Column(name = "fecha_partida")
	@NotNull
	private LocalDate fechaPartida;
	@Column(name = "hora_partida")
	@NotNull
	private LocalDate horaPartida;		
	@NotNull
	private String aerolinea;
	@Column(name = "nro_fila")
	@NotNull
	private Integer nroFila;
	@Column(name = "nro_asiento")
	@NotNull
	private Integer nroAsiento;
	@Column(name = "tipo_vuelo")
	@NotNull
	private TipoVuelo tipoVuelo;
	@ManyToOne
	@JoinColumn(name = "origen_id")
	private Ciudad origen;
	@ManyToOne
	@JoinColumn(name = "destino_id")	
	private Ciudad destino;//creada la entidad Ciudad	
	@ManyToMany(mappedBy = "vuelos") //linkeamos al HashSet vuelos de Clientes	
	private Set<Clientes> pasajeros = new HashSet<>();	
	@NotNull
	private EstadoVuelo estadoVuelo; // (registrado / reprogramado / cancelado) lo mismo quiza, se debe agregar en la base las opciones					
						   			//Creado tipo ENUM para este caso. 
	/* 
	 * El estado es autocalculado por el sistema, no puede ser establecido por
		el usuario.
		Una vez registrado, solo se permitirá cambiar la fecha y hora 
		del mismo(lo cual pasa el vuelo al estado reprogramado) 
		o eliminar el mismo (lo cual pasa el vuelo al estado cancelado).
		Tanto la reprogramación como la cancelación de un vuelo dispararía la
		notificación automática del evento a todos los pasajeros aunque por 
		simplicidad, no se pide implementar el servicio de alertas.*/
		
	//CONSTRUCTOR
	public Vuelo() {
		super();
	}
	
	//ENUM para estado de los vuelos.
	public enum EstadoVuelo {
		REGISTRADO,
	    DEMORADO,
	    CANCELADO,
	    REPROGRAMADO
	}
	
	//ENUM para tipo de vuelo.
	public enum TipoVuelo {
		NACIONAL,
		INTERNACIONAL
	}
	
	//METODOS
	public long getNroVuelo() {
		return nroVuelo;
	}


	public void setNroVuelo(long nroVuelo) {
		this.nroVuelo = nroVuelo;
	}

	public LocalDate getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(LocalDate fechaPartida) {
		this.fechaPartida = fechaPartida;
	}
	
	public LocalDate getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalDate horaPartida) {
		this.horaPartida = horaPartida;
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


	public TipoVuelo getTipoVuelo() {
		return this.tipoVuelo;
	}

	public void setTipoVuelo(TipoVuelo tipoVuelo) {
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


	public EstadoVuelo getEstado() {
		return this.estadoVuelo;
	}

	//ej de parametro: EstadoVuelo.CANCELADO
	public void setEstado(EstadoVuelo estado) {
		this.estadoVuelo = estado;
	}
	
	public Set<Clientes>getPasajeros(){
		
		return pasajeros;
		
	}

	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", fecha de partida=" + fechaPartida + ", hora de partida= " + horaPartida + ", nroFila=" + nroFila
				+ ", nroAsiento=" + nroAsiento + ", tipo_vuelo=" + tipoVuelo + ", Origen=" + origen + ", Destino="
				+ destino + ", Estado=" + estadoVuelo + "]";
	}	
	
}
