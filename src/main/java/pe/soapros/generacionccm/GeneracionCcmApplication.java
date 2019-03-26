package pe.soapros.generacionccm;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"pe.soapros.generacionccm"})
//@SpringBootApplication(scanBasePackages = {"pe.soapros.generacionccm"} , exclude = JpaRepositoriesAutoConfiguration.class)
public class GeneracionCcmApplication extends SpringBootServletInitializer{

	
	
	public static void main(String[] args) {
		SpringApplication.run(GeneracionCcmApplication.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	   return application.sources(GeneracionCcmApplication.class);
	}
	
}
