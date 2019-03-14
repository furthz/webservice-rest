package pe.soapros.generacionccm.business;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pe.soapros.generacionccm.beans.UsuarioCS;

@Component
public class CommunicationServerBO {

	private static final Logger logger = LogManager.getLogger(CommunicationServerBO.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	public String getToken() {
		logger.debug("CommunicationServerBO - token");
		
		String token = "";		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		logger.debug("Cabcera");
		
		UsuarioCS usuario = new UsuarioCS();
		usuario.setUser_name("");
		usuario.setPassword("");
		
		HttpEntity<UsuarioCS> entity = new HttpEntity<UsuarioCS>(usuario,headers);
		
		String url = "https://soaotxcsprueba:8443/otdsws/api/index.html#/authentication/authenticateWithPassword";
		
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class );
		
		logger.debug(response.toString());
		
		return response.getBody();
	}
}
