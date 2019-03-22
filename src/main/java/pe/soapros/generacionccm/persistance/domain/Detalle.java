package pe.soapros.generacionccm.persistance.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CCM_DETALLE")
public class Detalle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -517462587099745437L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDDETALLE")
	private long idDetalle;
	
	@Column(name="IDPETICION")
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
	
	@Column(name = "NOMFASE")
	private String nomFase;
	
	@Lob
	@Basic
	@Column(name = "INPUTJSON")
	private String inputJson;
	
	@Lob
	@Basic
	@Column(name = "RESPONSEJSON")
	private String responseJson;
	
	@Column(name = "INDERROR")
	private int indError;
	
	@Column(name = "DESCERROR")
	private String descError;

	public long getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(long idDetalle) {
		this.idDetalle = idDetalle;
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

	public String getNomFase() {
		return nomFase;
	}

	public void setNomFase(String nomFase) {
		this.nomFase = nomFase;
	}

	public String getInputJson() {
		return inputJson;
	}

	public void setInputJson(String inputJson) {
		this.inputJson = inputJson;
	}

	public String getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	public int getIndError() {
		return indError;
	}

	public void setIndError(int indError) {
		this.indError = indError;
	}

	public String getDescError() {
		return descError;
	}

	public void setDescError(String descError) {
		this.descError = descError;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(long idPeticion) {
		this.idPeticion = idPeticion;
	}

	@Override
	public String toString() {
		return "Detalle [idDetalle=" + idDetalle + ", idPeticion=" + idPeticion + ", usuCreacion=" + usuCreacion
				+ ", fecCreacion=" + fecCreacion + ", usuModif=" + usuModif + ", fecModif=" + fecModif + ", nomFase="
				+ nomFase + ", inputJson=" + inputJson + ", responseJson=" + responseJson + ", indError=" + indError
				+ ", descError=" + descError + "]";
	}





	
	
}
