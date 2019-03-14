package pe.soapros.generacionccm.persistance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.soapros.generacionccm.persistance.domain.Peticion;
import pe.soapros.generacionccm.persistance.repository.PeticionRepository;


@Service
public class PeticionManagerImpl implements PeticionManager{

	private static final long serialVersionUID = 4275809371160978549L;

	@Autowired
	private PeticionRepository peticionRepository;
	
	
	public void crearPeticion(Peticion pet) {

		this.peticionRepository.save(pet);
	}

}
