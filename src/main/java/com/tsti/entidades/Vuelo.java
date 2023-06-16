package com.tsti.entidades;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	@Column(name = "nro_vuelo")		
	private Long nroVuelo;
	//Se opto por separar fechas y horas para poder buscar los vuelos 
	//por fechas y tratar de simplificar el manejo de las mismas.
	@Column(name = "fecha_partida")
	@DateTimeFormat
	@NotNull
	private LocalDate fechaPartida;
	@Column(name = "hora_partida")	
	@NotNull
	private LocalTime horaPartida;	
	@NotNull	
	private String aerolinea;
	private String avion;
	@Transient
	private int nroFilas;
	@Transient
	private int nroColumnas;
	@Transient
	private Clientes plazas[][];
	@Column(name = "nro_asientos")
	@NotNull
	private int nroAsientos;	
	@Column(name = "tipo_vuelo")
	@NotNull
	private TipoVuelo tipoVuelo;
	@ManyToOne
	@JoinColumn(name = "origen_id")
	private Ciudad origen;
	@ManyToOne
	@JoinColumn(name = "destino_id")	
	private Ciudad destino;//creada la entidad Ciudad	
	//@ManyToMany(mappedBy = "vuelos") //linkeamos al HashSet vuelos de Clientes	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	    name = "vuelos_pasajeros",
	    joinColumns = @JoinColumn(name = "vuelo_id"),
	    inverseJoinColumns = @JoinColumn(name = "pasajero_id")
	)
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
	
	//ENUM para estado de los vuelos. Se puede acceder por getEstadoVuelo(int estadoVuelo)
	public enum EstadoVuelo {
		REGISTRADO(0),
	    DEMORADO(1),
	    CANCELADO(2),
	    REPROGRAMADO(3);

		EstadoVuelo(int i) {
			
		}
	}
	
	//ENUM para tipo de vuelo. Se puede acceder por getTipoVuelo(int tipoVuelo)
	public enum TipoVuelo {
		NACIONAL,
		INTERNACIONAL
	}	
	
	//METODOS
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

	public String getAvion() {
		return avion;
	}

	public void setAvion(String avion) {
		this.avion = avion;
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
		return nroFilas;
	}


	public void setNroFilas(int nroFila) {
		this.nroFilas = nroFila;
	}

	public Clientes[][] getPlazas() {
		return plazas;
	}	
	
	public void setNroAsientos(int nroFilas, int nroColumnas) {
		this.nroAsientos = nroFilas * nroColumnas;
	}
	
	public int getNroAsientos() {
		return nroFilas * nroColumnas;
	}

	public int getNroColumnas() {
		return nroColumnas;
	}

	public void setNroColumnas(int nroColumnas) {
		this.nroColumnas = nroColumnas;
	}
	
	public void setPlazas(Clientes[][] plazas) {
		this.plazas = plazas;
	}
	
	public TipoVuelo getTipoVuelo() {
		
		return this.tipoVuelo;
		
	}
	
	@Autowired //fuerzo a spring que utilice este metodo durante la IOC
	public void setTipoVuelo() {		
		String argentina = "Argentina";
		
		if(argentina.equalsIgnoreCase(this.destino.getPais())) {
			
			this.tipoVuelo = TipoVuelo.NACIONAL;
		}
		else {			
			this.tipoVuelo = TipoVuelo.INTERNACIONAL;			
		}		
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


	public EstadoVuelo getEstadoVuelo() {
		
		return this.estadoVuelo;
	}

	//ej de parametro: EstadoVuelo.CANCELADO
	public void setEstadoVuelo(EstadoVuelo estado) {
		this.estadoVuelo = estado;
	}
	
	public Set<Clientes> getPasajeros(){
		
		return pasajeros;
		
	}
	
	public void setPasajeros(HashSet<Clientes> pasajeros){
		
		this.pasajeros = pasajeros;	
		
	}
	
	public void addPasajero(Clientes pasajero){
		
		this.pasajeros.add(pasajero);		
		
	}

	@Override
	public String toString() {
		return "Vuelo [nroVuelo=" + nroVuelo + ", hora de partida= " + horaPartida + ", nroFila=" + nroFilas
				+ ", nroColumnas=" + nroColumnas + ", tipo_vuelo=" + tipoVuelo + ", Origen=" + origen + ", Destino="
				+ destino + ", Estado=" + estadoVuelo + "]";
	}

	

		
	
}
