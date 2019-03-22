package pe.soapros.generacionccm.persistance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.soapros.generacionccm.persistance.domain.Detalle;
import pe.soapros.generacionccm.persistance.domain.Peticion;

import org.springframework.stereotype.Repository;

@Repository
public interface PeticionRepository extends JpaRepository<Peticion, Long>{
	
	@SuppressWarnings("unchecked")
	Peticion save(Peticion pet);
	
	@Query("SELECT d FROM Peticion p, Detalle d WHERE p.idPeticion = d.idPeticion AND p.numOperacion = (:operacion) ORDER BY d.idDetalle ")
	List<Detalle> detallesByOperacion(@Param("operacion") String operacion);
}
