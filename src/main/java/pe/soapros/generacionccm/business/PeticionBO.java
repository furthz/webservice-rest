package pe.soapros.generacionccm.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.soapros.generacionccm.beans.Entrada_Peticion;
import pe.soapros.generacionccm.beans.PeticionOUT;
import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.persistance.domain.Detalle;
import pe.soapros.generacionccm.persistance.domain.Peticion;
import pe.soapros.generacionccm.persistance.repository.DetalleRepository;
import pe.soapros.generacionccm.persistance.repository.PeticionRepository;

@Component
public class PeticionBO {

	private static final Logger logger = LogManager.getLogger(PeticionBO.class);

	@Autowired
	private PeticionRepository peticionRepository;
	
	@Autowired
	private DetalleRepository detalleRepository;
	
	public Respuesta procesarPeticion(Respuesta res, Solicitud sol) throws JsonProcessingException {
		Respuesta rpta = null;
		
		Peticion pet = new Peticion();
		logger.debug("JPA", pet.toString());

		pet.setUsuCreacion(res.getOrigen().getUsuario());
		pet.setFecCreacion(new Date());
		pet.setNumOperacion(res.getNumOperacion());
		pet.setIndError(false);

		
		Detalle det = new Detalle();
		det.setUsuCreacion(res.getOrigen().getUsuario());
		det.setFecCreacion(new Date());
		det.setNomFase("Solicitado");
		
		ObjectMapper mapper = new ObjectMapper();		
		String jsonInput = mapper.writeValueAsString(sol);
		
		det.setInputJson(jsonInput);
		det.setIndError(0);
		
		//pet.addDetalle(det);
		
		Peticion p = peticionRepository.save(pet);
		
		det.setIdPeticion(p.getIdPeticion());;
		detalleRepository.save(det);
		
		logger.debug("JPA Guardado");
		//logger.debug(p.toString());
		
		return rpta;
	}
	
	public PeticionOUT consultarPeticion(Entrada_Peticion solicitud) throws JsonParseException, JsonMappingException, IOException {
		
		List<Detalle> detalles = this.peticionRepository.detallesByOperacion(solicitud.getNumOperacion());
		logger.debug("Detalles Recuperados: " + detalles.toString());
		int tam = detalles.size();
		
		String input = "";
		
		for(Detalle det : detalles) {
			if(det.getNomFase().equals("Solicitado")) {
				input = det.getInputJson();
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		Solicitud sol = mapper.readValue(input, Solicitud.class);
		logger.debug("JSON input" + input);
		
		PeticionOUT respuesta = new PeticionOUT();
		
		respuesta.setOrigen(sol.getOrigen());
		respuesta.setCabecera(sol.getCabecera());
		
		respuesta.setNumOperacion(solicitud.getNumOperacion());
		
		
		
		String estado = detalles.get(tam-1).getNomFase();
		respuesta.setEstado(estado);
		
		
		return respuesta;
	}
	
}
