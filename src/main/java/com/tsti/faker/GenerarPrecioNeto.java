/**
 * 
 */
package tsti.faker;

import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * @author Bruno
 *
 */
import java.util.concurrent.ThreadLocalRandom;

public class GenerarPrecioNeto {

	private static double min;
	private static double max;
	private static double randomDouble; 
	
	/**
	 * 
	 */
	public GenerarPrecioNeto() {
		// TODO Auto-generated constructor stub
	}

	public static BigDecimal generarPrecioNetoPesos() {
	    min = 20000.0;
	    max = 500000.0;
	    randomDouble = ThreadLocalRandom.current().nextDouble(min, max);
	    	        
	    return new BigDecimal(randomDouble).setScale(2, RoundingMode.HALF_DOWN);
	}
	
	public static BigDecimal generarPrecioNetoDolares() {
	    min = 20.0;
	    max = 1000.0;
	    //metodo para crear numero random
	    randomDouble = ThreadLocalRandom.current().nextDouble(min, max);
	    
	    //se crea y devuelve un decimal de dos cifras redondeado hacia arriba.
	    return new BigDecimal(randomDouble).setScale(2,RoundingMode.HALF_DOWN);
	}
}
