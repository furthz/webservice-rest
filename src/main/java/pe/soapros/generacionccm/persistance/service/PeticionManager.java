package pe.soapros.generacionccm.persistance.service;

import java.io.Serializable;

import pe.soapros.generacionccm.persistance.domain.Peticion;

public interface PeticionManager extends Serializable{

	public void crearPeticion(Peticion pet);
}
