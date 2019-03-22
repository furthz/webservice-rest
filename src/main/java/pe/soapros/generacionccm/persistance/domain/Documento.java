package pe.soapros.generacionccm.persistance.domain;

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
@Table(name = "CCM_DOCUMENTO")
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDDOCUMENTO")
	private long idDocumento;
	
	@Column(name="IDSISTEMA")
	private long idSistema;
	
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
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "INDACTIVO")
	private int indActivo;

	public long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public long getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(long idSistema) {
		this.idSistema = idSistema;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIndActivo() {
		return indActivo;
	}

	public void setIndActivo(int indActivo) {
		this.indActivo = indActivo;
	}
	
	
}
