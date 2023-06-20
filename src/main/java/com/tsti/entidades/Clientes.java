package tsti.entidades; 
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
		@Column(unique = true)
		private String tel;		
		@Column(name = "e_mail")
		private String email;
		@Column(name = "fecha_nac", unique = true)
		private Date fechaNacimiento;// ver lo de fecha en esta pagina https://www.campusmvp.es/recursos/post/como-manejar-correctamente-fechas-en-java-el-paquete-java-time.aspx
		@Column(name = "nro_pasaporte", unique = true)
		private Long nroPasaporte;		
		@Column(name = "exp_pasaporte", unique = true)
		private Date vencimientoPasaporte; // lo mismo que fechaNacimiento
		
		
		//Un pasajero puede realizar muchos vuelos y un vuelo puede tener 
		//muchos pasajeros. Se creara una nueva tabla.
		@ManyToMany(cascade = CascadeType.ALL)
		@JoinTable(name = "vuelos_pasajeros", 
				   joinColumns = @JoinColumn(name="pasajero_id"),
				   inverseJoinColumns = @JoinColumn(name="vuelo_id"))
		//atributo vuelos tipo HashSet.
		private Set<Vuelo> vuelos = new HashSet<>();
		
		//CONSTRUCTOR
		public Clientes() {
			super();
		}
		
		//METODOS
		public long getDni() {
			return dni;
		}
		
		public void setDni(long dni) {
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
		
		public String getEmail() {
			return email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public Date getFechaNacimiento() {
			return fechaNacimiento;
		}
		
		public void setFechaNacimiento(Date fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public long getNroPasaporte() {
			return nroPasaporte;
		}
		
		public void setNroPasaporte(long nroPasaporte) {
			this.nroPasaporte = nroPasaporte;
		}
		
		public Date getVencimientoPasaporte() {
			return vencimientoPasaporte;
		}
		
		public void setVencimientoPasaporte(Date vencimientoPasaporte) {
			this.vencimientoPasaporte = vencimientoPasaporte;
		}
		
		public void addVuelo(Vuelo vuelo) {
	        vuelos.add(vuelo);
	        vuelo.getPasajeros().add(this);
	    }
	    
	    public void removeVuelo(Vuelo vuelo) {
	        vuelos.remove(vuelo);
	        vuelo.getPasajeros().remove(this);
	    }
		
		@Override
		public String toString() {
			return dni+" - "+ nombre +" "+ apellido;
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

	public Set<Vuelo> getVuelos() {
		return vuelos;
	}

	public void setVuelos(HashSet<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
	
	
	public void setNroPasaporte(Long nroPasaporte) {
		this.nroPasaporte = nroPasaporte;
	}
	
	
	
	
		
}
