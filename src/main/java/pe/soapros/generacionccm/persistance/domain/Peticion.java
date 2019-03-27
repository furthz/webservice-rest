package pe.soapros.generacionccm.persistance.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CCM_PETICION")
public class Peticion implements Serializable{

	private static final long serialVersionUID = 9043312177095494738L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDPETICION")
	private long idPeticion;
	
	@Column(name = "USUCREACION")
	private String usuCreacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECCREACION")
	private Date fecCreacion;
	
	@Column(name = "USUMODIF")
	private String usuModif;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECMODIF")
	private Date fecModif;
	
	@Column(name = "NUMOPERACION")
	private String numOperacion;
	
	@Column(name = "INDERROR")
	private boolean indError;
	/*
	@OneToMany(
			mappedBy = "peticion",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Detalle> detalles;
	
	public void addDetalle(Detalle detalle) {
		this.detalles.add(detalle);
	}*/
	public long getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(long idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getUsuCreacion() {
		return usuCreacion;
	}

	public void setUsuCreacion(String usuCreacion) {
		this.usuCreacion = usuCreacion;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public String getUsuModif() {
		return usuModif;
	}

	public void setUsuModif(String usuModif) {
		this.usuModif = usuModif;
	}

	public Date getFecModif() {
		return fecModif;
	}

	public void setFecModif(Date fecModif) {
		this.fecModif = fecModif;
	}

	public String getNumOperacion() {
		return numOperacion;
	}

	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}

	public boolean isIndError() {
		return indError;
	}

	public void setIndError(boolean indError) {
		this.indError = indError;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Peticion [idPeticion=" + idPeticion + ", usuCreacion=" + usuCreacion + ", fecCreacion=" + fecCreacion
				+ ", usuModif=" + usuModif + ", fecModif=" + fecModif + ", numOperacion=" + numOperacion + ", indError="
				+ indError + "]";
	}
	
	
	
	
}
