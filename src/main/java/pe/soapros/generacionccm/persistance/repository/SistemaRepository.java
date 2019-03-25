package pe.soapros.generacionccm.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.soapros.generacionccm.persistance.domain.Documento;
import pe.soapros.generacionccm.persistance.domain.Sistema;

public interface SistemaRepository extends JpaRepository<Sistema, Long>{

	@Query("SELECT s FROM Sistema s WHERE s.nombre = (:nombre)")
	Sistema getByNombre(@Param("nombre") String nombre);
	
	@Query("SELECT d FROM Sistema s, Documento d WHERE s.idSistema = d.idSistema AND s.nombre = (:sistema) AND d.codigo = (:codigo)")
	Documento getDocumentoSistemaByPlantilla(@Param("sistema") String idSistema, @Param("codigo") String codigo);
}
