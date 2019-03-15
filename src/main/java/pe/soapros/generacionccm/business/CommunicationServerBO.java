package pe.soapros.generacionccm.business;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pe.soapros.generacionccm.beans.ResponseTokenCS;
import pe.soapros.generacionccm.beans.UsuarioCS;

@Component
public class CommunicationServerBO {

	private static final Logger logger = LogManager.getLogger(CommunicationServerBO.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	public ResponseTokenCS getToken() {
		logger.debug("CommunicationServerBO - token");
				
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		logger.debug("Cabcera");
		
		UsuarioCS usuario = new UsuarioCS();
		usuario.setUser_name("otadmin@otds.admin");
		usuario.setPassword("Streamserve16!");

		String data = "{  \"userName\": \"otadmin@otds.admin\"," + 
				"  \"password\": \"Streamserve16!\"\r\n" + 
				"}";		
	
		HttpEntity<String> entity = new HttpEntity<String>(data,headers);		
		
		String url = "https://soaotxcsprueba:8443/otdsws/rest/authentication/credentials";
		
		ResponseEntity<ResponseTokenCS> response = restTemplate.postForEntity(url, entity, ResponseTokenCS.class); //(url, HttpMethod.POST, entity, String.class );
		
		logger.debug(response.toString());
		
		return response.getBody();
	}
		
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
