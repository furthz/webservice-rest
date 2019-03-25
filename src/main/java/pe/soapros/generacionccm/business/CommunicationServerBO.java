package pe.soapros.generacionccm.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import pe.soapros.generacionccm.beans.Propiedades;
import pe.soapros.generacionccm.beans.ResponseTokenCS;
import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.beans.UsuarioCS;

@Component
public class CommunicationServerBO {

	private static final Logger logger = LogManager.getLogger(CommunicationServerBO.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Propiedades propiedades;

	public ResponseTokenCS getToken() {
		logger.debug(ADMIN_USER, "getToken");

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		logger.debug(ADMIN_USER, headers);

		UsuarioCS usuario = new UsuarioCS();
		usuario.setUser_name("otadmin@otds.admin");
		usuario.setPassword("Streamserve16!");

		String data = "{  \"userName\": \"otadmin@otds.admin\"," + "  \"password\": \"Streamserve16!\"\r\n" + "}";

		HttpEntity<String> entity = new HttpEntity<String>(data, headers);

		String url = "https://soaotxcsprueba:8443/otdsws/rest/authentication/credentials";

		ResponseEntity<ResponseTokenCS> response = restTemplate.postForEntity(url, entity, ResponseTokenCS.class); // (url,
																													// HttpMethod.POST,
																													// entity,
																													// String.class
																													// );

		logger.debug(response.toString());

		return response.getBody();
	}

	public Respuesta callOrquestador(Solicitud solicitud) {
		logger.debug(ADMIN_USER, "callOrquestador", solicitud);

		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		logger.debug(ADMIN_USER, "Headers", headers);

		HttpEntity<Solicitud> entity = new HttpEntity<Solicitud>(solicitud, headers);
		logger.debug(ADMIN_USER, "Entity", entity);

		String url = propiedades.getServicio();// "http://192.168.1.227:3000/api/v1/peticion";
		logger.debug(ADMIN_USER, "URL", url);

		ResponseEntity<Respuesta> response = restTemplate.postForEntity(url, entity, Respuesta.class); // (url,
																										// HttpMethod.POST,
																										// entity,
																										// String.class
																										// );
		logger.debug(ADMIN_USER, "Respuesta", response);

		return response.getBody();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
