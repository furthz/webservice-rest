package pe.soapros.generacionccm.beans;

import javax.validation.Valid;

public class Solicitud {

	@Valid
	private Origen origen;

	@Valid
	private CabeceraIN cabecera;

	
	private String jsonData;
	
	public Origen getOrigen() {
		return origen;
	}

	public void setOrigen(Origen origen) {
		this.origen = origen;
	}

	public CabeceraIN getCabecera() {
		return cabecera;
	}

	public void setCabecera(CabeceraIN cabecera) {
		this.cabecera = cabecera;
	}
	
	

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public String toString() {
		return "Solicitud [origen=" + origen + ", cabecera=" + cabecera + ", jsonData=" + jsonData + "]";
	}

	
	
}
