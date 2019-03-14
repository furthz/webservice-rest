package pe.soapros.generacionccm.utils;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


import pe.soapros.generacionccm.beans.Origen;
import pe.soapros.generacionccm.beans.Solicitud;

@JsonComponent
public class ValueDeserializer extends JsonDeserializer<Solicitud>{

	private static final Logger logger = LogManager.getLogger(ValueDeserializer.class);
	
	
	
	@Override
	public Solicitud deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		logger.debug("custom deserializar");
		logger.debug(p.toString());
		
		ObjectCodec oc = p.getCodec();
	    JsonNode node = oc.readTree(p);
	    
	    JsonNode origen = node.get("origen");
	    JsonNode cabecera = node.get("cabecera");
	    JsonNode jsonData = node.get("jsondata");
	    
	    //validar campos del origen
	    String sistema = origen.get("sistema").asText();
	    Origen orig = new Origen();
	    orig.setSistema(sistema);
	    
	    //validar campos de la cabecera
	    
		
		Solicitud s = new Solicitud();
		s.setOrigen(orig);
		
		if(jsonData != null) {
			s.setJsonData(jsonData.asText());
		}
		
		if(jsonData == null) {
			throw new JsonProcessingException("No hay JSON DATA") {
				private void init() {
					this.initCause(this);
				}
			};
		}
		
		return s; 	
	
	}

}
