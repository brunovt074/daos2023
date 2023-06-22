/**
 * 
 */
package com.tsti.faker;

/**
 * @author Bruno
 *
 */
import java.util.concurrent.ThreadLocalRandom;

public class GenerarPrecioNeto {

	/**
	 * 
	 */
	public GenerarPrecioNeto() {
		// TODO Auto-generated constructor stub
	}
	
	

	public static double generarPrecioNetoPesos() {
	    double min = 20000.0;
	    double max = 500000.0;
	    return ThreadLocalRandom.current().nextDouble(min, max);
	}
	
	public static double generarPrecioNetoDolares() {
	    double min = 20.0;
	    double max = 1000.0;
	    return ThreadLocalRandom.current().nextDouble(min, max);
	}


}
