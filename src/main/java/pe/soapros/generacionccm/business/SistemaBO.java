package pe.soapros.generacionccm.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.soapros.generacionccm.persistance.domain.Documento;
import pe.soapros.generacionccm.persistance.domain.Sistema;
import pe.soapros.generacionccm.persistance.repository.SistemaRepository;

@Component
public class SistemaBO {

	private static final Logger logger = LogManager.getLogger(SistemaBO.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");
	
	@Autowired
	private SistemaRepository sistemaRepository;
	
	public boolean validateSistema(String sistema) {
		logger.debug(ADMIN_USER, "validateSistema: " + sistema);
		
		boolean rpta = false;
		
		Sistema sist = sistemaRepository.getByNombre(sistema);
				
		if(sist!= null && sist.getNombre().equals(sistema)) {
			logger.debug(ADMIN_USER, "Validado");
			logger.debug(ADMIN_USER, "Query: " + sist.toString());
			rpta = true;
		}		
		
		return rpta;
	}
	
	public boolean validatePlantilla(String sistema, String codigo) {
		logger.debug(ADMIN_USER, "validatePlantilla Sistema: {} Codigo: {}",sistema, codigo);
		
		boolean rpta = false;
		
		Documento doc = sistemaRepository.getDocumentoSistemaByPlantilla(sistema, codigo);
		logger.debug(ADMIN_USER, "Documento recuperado {}", doc);
		
		if(doc != null) {
			logger.debug(ADMIN_USER, "Validado");
			logger.debug(ADMIN_USER, "Query: " + doc.toString());
			rpta = true;
		}
		
		return rpta;
	}
}
