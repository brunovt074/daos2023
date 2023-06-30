/**
 * 
 */
package com.tsti.presentacion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.tsti.entidades.Vuelo;


import jakarta.validation.constraints.NotNull;

/**
 * @author Bruno
 *
 *Clase que representa un formulario de entrada para crear y editar vuelos
 */
public class CrearVueloForm {
	
	// Atributos de vuelo	
	private Long nroVuelo;
	@NotNull
	private String aerolinea;
	private String avion;
	@NotNull
	private LocalDate fechaPartida;
	@NotNull
	private LocalTime horaPartida;
	@NotNull
	private int nroFilasAsientos;
	@NotNull
	private int nroColumnasAsientos;
	@NotNull	
	private BigDecimal precioNeto;
	
	//Atributos de ciudad
	private Long idDestino; 
	private String codAeropuerto;
    private String nombreCiudad;
    private String provincia;
    private String pais;
    private String codPostal;
	
    //Constructor de vuelo.
    public CrearVueloForm() {
		// TODO Auto-generated constructor stub
	}
	
	public CrearVueloForm(Vuelo pojo) {
		super();
		this.nroVuelo = pojo.getNroVuelo();
		this.aerolinea = pojo.getAerolinea();
		this.avion = pojo.getAvion();
		this.fechaPartida = pojo.getFechaPartida();
		this.horaPartida = pojo.getHoraPartida();
		this.nroFilasAsientos = pojo.getNroFilas();
		this.nroColumnasAsientos = pojo.getNroColumnas();
		this.idDestino = pojo.getDestino().getId();
		this.codAeropuerto = pojo.getDestino().getcodAeropuerto();
		this.nombreCiudad = pojo.getDestino().getNombreCiudad();
		this.provincia = pojo.getDestino().getProvincia();
		this.pais = pojo.getDestino().getPais();
		this.codPostal = pojo.getDestino().getCodPostal();
	}

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
		return getNroFilasAsientos();
	}


	public int getNroFilasAsientos() {
		return nroFilasAsientos;
	}

	public void setNroFilas(int nroFilas) {
		setNroFilasAsientos(nroFilas);
	}


	public void setNroFilasAsientos(int nroFilas) {
		this.nroFilasAsientos = nroFilas;
	}

	public int getNroColumnas() {
		return getNroColumnasAsientos();
	}


	public int getNroColumnasAsientos() {
		return nroColumnasAsientos;
	}

	public void setNroColumnas(int nroColumnas) {
		setNroColumnasAsientos(nroColumnas);
	}

	public void setNroColumnasAsientos(int nroColumnas) {
		this.nroColumnasAsientos = nroColumnas;
	}

	public BigDecimal getPrecioNeto() {
		return precioNeto;
	}

	public void setPrecioNeto(BigDecimal precioNeto) {
		this.precioNeto = precioNeto;
	}

	public Long getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

	public String getCodAeropuerto() {
		return codAeropuerto;
	}

	public void setCodAeropuerto(String codAeropuerto) {
		this.codAeropuerto = codAeropuerto;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aerolinea, fechaPartida, horaPartida, precioNeto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrearVueloForm other = (CrearVueloForm) obj;
		return Objects.equals(aerolinea, other.aerolinea) && Objects.equals(codAeropuerto, other.codAeropuerto)
				&& Objects.equals(nombreCiudad, other.nombreCiudad)
				&& Objects.equals(provincia, other.provincia)
				&& Objects.equals(pais, other.pais)
				&& Objects.equals(fechaPartida, other.fechaPartida) && Objects.equals(horaPartida, other.horaPartida)
				&& Objects.equals(precioNeto, other.precioNeto);
	}

	@Override
	public String toString() {
		return "CrearVueloForm [nroVuelo=" + nroVuelo + ", aerolinea=" + aerolinea + ", destino=" + nombreCiudad
				+ ", fechaPartida=" + fechaPartida + ", horaPartida=" + horaPartida + ", nroFilasAsientos="
				+ nroFilasAsientos + ", nroColumnasAsientos=" + nroColumnasAsientos + ", precioNeto=" + precioNeto
				+ "]";
	}

	
	
	
}
