package com.tsti.entidades; 
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author cecilia
 *  Establece/Actualiza/Retorna los datos de cliente
 *  
 *  LAS RESTRICCIONES UNIQUE ESTAN DESACTIVADAS PARA FACILITAR LA POBLACION
 *  DE LA BD CON FAKER.
 */
@Entity
public class Clientes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;		
	@NotNull
	private Long dni;		
	@NotNull
	private String nombre;		
	@NotNull
	private String apellido;		
	//Relacion 1:1 con domicilio, si se borra un cliente se borra el domicilio
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "domicilio_id")
	private Domicilio domicilio;		
	//@Column(unique = true)
	private String tel;		
	//@Column(unique = true)
	private String email;
	@Column(name = "fecha_nac")
	private LocalDate  fechaNacimiento;// ver lo de fecha en esta pagina https://www.campusmvp.es/recursos/post/como-manejar-correctamente-fechas-en-java-el-paquete-java-time.aspx
	@Column(name = "nro_pasaporte"/*, unique = true*/)
	private Long nroPasaporte;		
	@Column(name = "exp_pasaporte"/*, unique = true*/)
	private LocalDate vencimientoPasaporte; // lo mismo que fechaNacimiento	
	//atributo vuelos tipo HashSet.

    @OneToMany(mappedBy = "pasajero")
    private List<Pasaje> pasajes;

	//CONSTRUCTOR
	public Clientes() {
		super();
	}
	
	//METODOS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getDni() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setDni(Long dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Domicilio getDomicilio() {
		return domicilio;
	}
	
	public void setDomicilio(Domicilio domicilio) {
		this.domicilio = domicilio;
	}	

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	/*
	 * public boolean isEsPrimerVuelo() { return esPrimerVuelo; }
	 * 
	 * public void setEsPrimerVuelo(boolean esPrimerVuelo) { this.esPrimerVuelo =
	 * esPrimerVuelo; }
	 */

	public List<Pasaje> getVuelos() {
		return pasajes;
	}

	public void setVuelos(List<Pasaje> pasajes) {
		this.pasajes =pasajes;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Long getNroPasaporte() {
		return nroPasaporte;
	}
	
	public void setNroPasaporte(Long nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}
	
	public LocalDate getVencimientoPasaporte() {
		return vencimientoPasaporte;
	}
	
	public void setVencimientoPasaporte(LocalDate vencimientoPasaporte) {
		this.vencimientoPasaporte = vencimientoPasaporte;
	}
	
	public void addVuelo(Pasaje vuelo) {
        pasajes.add(vuelo);
//        vuelo.getPasajeros().add(this);
    }
    
    public void removeVuelo(Pasaje vuelo) {
        pasajes.remove(vuelo);
//        pasajes.getPasajeros().remove(this);
    }
	
	@Override
	public String toString() {
		return dni+" - "+ nombre +" "+ apellido;
	}	
		
}