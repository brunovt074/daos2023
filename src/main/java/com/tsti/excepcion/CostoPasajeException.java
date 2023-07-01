/**
 * 
 */
package com.tsti.excepcion;

import org.springframework.http.HttpStatusCode;

/**
 * @author Bruno
 *
 */
public class CostoPasajeException extends RuntimeException{
	
	private static final long serialVersionUID = 9876543219876543L;
	
	private String mensaje;
	private int statusCode;
	
	public CostoPasajeException(String message) {
		
		super(message);
	}
	
	public CostoPasajeException (String mensaje,HttpStatusCode statusCode) {
		super();
		this.mensaje = mensaje;		
	}
	

	public CostoPasajeException(String mensaje, int statusCode) {
		super();
		this.mensaje = mensaje;
		this.statusCode = statusCode;
	}


	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}
