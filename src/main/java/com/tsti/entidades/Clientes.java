package com.tsti.entidades; 
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author cecilia
 *  Establece/Actualiza/Retorna los datos de cliente
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
		//Relacion 1:1 con domicilio
		@OneToOne
		@JoinColumn(name = "domicilio_id")
		private Domicilio domicilio;		
		@Column(unique = true)
		private String tel;		
		@Column(unique = true)
		private String email;		
		private Date fechaNacimiento;// ver lo de fecha en esta pagina https://www.campusmvp.es/recursos/post/como-manejar-correctamente-fechas-en-java-el-paquete-java-time.aspx
		@Column(unique = true)
		private Long nroPasaporte;		
		private Date vencimientoPasaporte; // lo mismo que fechaNacimiento
		//Un pasajero puede realizar muchos vuelos
		@OneToMany(mappedBy = "pasajero")
		List<Vuelo> vuelos;
		
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
		
		@Override
		public String toString() {
			return dni+" - "+ nombre +" "+ apellido;
		}
		
}
