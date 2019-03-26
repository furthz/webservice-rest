package pe.soapros.generacionccm.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "pe.soapros.generacionccm.persistance.repository")
@EntityScan("package pe.soapros.generacionccm.persistance.domain")
public class AppConfiguration {

	@Bean
	public DataSource dataSource() {
		
		DataSource dataSource = null; 
		System.out.println("value of datasource"+dataSource);
		try {
	   
	    Context initialContex = new InitialContext();
	    System.out.println("value of datasource"+dataSource);
	    
	    dataSource = (DataSource)(initialContex.lookup("java:jboss/datasources/DS_JOB_OT"));
	    
	    System.out.println("value of datasource"+dataSource);
	    
	    if(dataSource != null) {
	    	dataSource.getConnection();
		    
	    }
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
	   
	    return dataSource;
	    
	}
}
