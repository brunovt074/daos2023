package com.tsti.excepcion;

/**
 * @author JOA
 *
 */
public class ValidacionFallidaEnPasajeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//PARA OMITIR UN WARNING
	
	public ValidacionFallidaEnPasajeException(String message) {
        super(message);
    }
}