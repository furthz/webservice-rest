package pe.soapros.generacionccm.beans;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.JsonNode;


public class Solicitud {

	//@Valid
	private Origen origen;

	//@Valid
	private CabeceraIN cabecera;

	private JsonNode  jsonData;
	
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
	
	

	public JsonNode  getJsonData() {
		return jsonData;
	}

	public void setJsonData(JsonNode  jsonData) {
		this.jsonData = jsonData;
	}

	@Override
	public String toString() {
		return "Solicitud [origen=" + origen + ", cabecera=" + cabecera + ", jsonData=" + jsonData + "]";
	}


	
	
}
