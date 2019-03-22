package pe.soapros.generacionccm.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.soapros.generacionccm.persistance.domain.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

}
