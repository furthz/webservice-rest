package pe.soapros.generacionccm.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.soapros.generacionccm.persistance.domain.Peticion;

import org.springframework.stereotype.Repository;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Long>{
	
	@SuppressWarnings("unchecked")
	Peticion save(Peticion pet);
}
