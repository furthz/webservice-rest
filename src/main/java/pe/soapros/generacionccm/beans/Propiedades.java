package pe.soapros.generacionccm.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Propiedades {

	@Value("${ewsUrl}")
	private String ewsUrl;
	
	@Value("${orquestador}")
	private String servicio;
	

	public String getEwsUrl() {
		return ewsUrl;
	}

	public void setEwsUrl(String ewsUrl) {
		this.ewsUrl = ewsUrl;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	
	
}
