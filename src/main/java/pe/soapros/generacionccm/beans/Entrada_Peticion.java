package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Entrada_Peticion {
	@NotNull(message="Origen es obligatorio")
	Origen origen;
	@NotNull(message="Número de Operación es obligatorio")
	String numOperacion;
	public Origen getOrigen() {
		return origen;
	}
	public void setOrigen(Origen origen) {
		this.origen = origen;
	}
	public String getNumOperacion() {
		return numOperacion;
	}
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}
	
	
	
}
