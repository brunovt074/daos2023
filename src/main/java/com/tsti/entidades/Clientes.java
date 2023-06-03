package com.tsti.entidades;

import java.util.Date;

/**
 *
 * @author cecilia
 *  Establece/Actualiza/Retorna los datos de cliente
 */
public class Clientes {
		private long dni; 
		private String nombre;
		private String apellido;
		private String domicilio;
		private String email;
		private Date fechaNacimiento; // ver lo de fecha en esta pagina https://www.campusmvp.es/recursos/post/como-manejar-correctamente-fechas-en-java-el-paquete-java-time.aspx
		private long nroPasaporte;
		private Date vencimientoPasaporte; // lo mismo que fechaNacimiento
		
		
		public Clientes() {
			super();
		}

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
		
		public String getDomicilio() {
			return domicilio;
		}
		
		public void setDomicilio(String domicilio) {
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
