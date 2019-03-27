package pe.soapros.generacionccm.persistance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.soapros.generacionccm.persistance.domain.Detalle;


@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long>{

	@SuppressWarnings("unchecked")
	Detalle save(Detalle detalle);
	
	//@Query("SELECT d FROM Detalle d WHERE d.idPeticion = (:peticion) ")
	//List<Detalle> findByIdPeticion(@Param("peticion") long idPeticion);
	
	 
}
