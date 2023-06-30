/**
 * 
 */
package tsti.faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import net.datafaker.Faker;

/**
 * @author Bruno
 *
 */
public class GeneradorFechaHora {
	static Faker faker;
	
	public GeneradorFechaHora() {
		GeneradorFechaHora.faker = new Faker(new Locale("es") );
		
	}
	
	public static Object[] fechaHora() {
		
		Object[] fechaHoraPartida = new Object[2];		
		
		//Obtener fecha.
		LocalDateTime fechaPartidaTimestamp = (faker.date().future(60,0,TimeUnit.DAYS)).toLocalDateTime();
		LocalDate fechaPartida = fechaPartidaTimestamp.toLocalDate();
		//Obtener hora.
		LocalDateTime horaPartidaTimestamp = (faker.date().future(12,0,TimeUnit.HOURS)).toLocalDateTime();
		LocalTime horaPartida = horaPartidaTimestamp.toLocalTime().truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
		
		fechaHoraPartida[0] = fechaPartida; //Elemento de tipo LocalDate
		fechaHoraPartida[1] = horaPartida; //Elemento de tipo LocalTime
		
		return fechaHoraPartida;
		
	}

}
