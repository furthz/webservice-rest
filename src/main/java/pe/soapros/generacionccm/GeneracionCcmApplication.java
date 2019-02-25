package pe.soapros.generacionccm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pe.soapros.generacionccm"})
public class GeneracionCcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneracionCcmApplication.class, args);
	}

}
