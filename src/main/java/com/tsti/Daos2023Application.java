package tsti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tsti.faker"})
public class Daos2023Application {

	public static void main(String[] args) {
		SpringApplication.run(Daos2023Application.class, args);	
		
	}
	
}
