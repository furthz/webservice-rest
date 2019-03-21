package pe.soapros.generacionccm.business;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.persistance.domain.Peticion;
import pe.soapros.generacionccm.persistance.repository.PeticionRepository;

@Component
public class PeticionBO {

	private static final Logger logger = LogManager.getLogger(PeticionBO.class);

	@Autowired
	private PeticionRepository peticionRepository;
	
	public Respuesta procesarPeticion(Respuesta solicitud) {
		Respuesta rpta = null;
		
		Peticion pet = new Peticion();
		logger.debug("JPA", pet.toString());

		pet.setUsuCreacion(solicitud.getOrigen().getUsuario());
		pet.setFecCreacion(new Date());
		pet.setNumOperacion(solicitud.getNumOperacion());
		pet.setIndError(false);

		Peticion p = peticionRepository.save(pet);
		logger.debug("JPA Guardado");
		//logger.debug(p.toString());
		
		return rpta;
	}
	
}
