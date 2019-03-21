package pe.soapros.generacionccm.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.soapros.generacionccm.persistance.domain.Detalle;


@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long>{

	Detalle save(Detalle detalle);
	
	//@Query("SELECT d FROM Detalle d WHERE d.idPeticion = (:peticion) ")
	//List<Detalle> findByIdPeticion(@Param("peticion") long idPeticion);
	
	 
}
