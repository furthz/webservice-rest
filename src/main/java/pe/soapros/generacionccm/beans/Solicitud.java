package pe.soapros.generacionccm.beans;

import javax.validation.Valid;

public class Solicitud {

	@Valid
	private Origen origen;

	@Valid
	private CabeceraIN cabecera;

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

	@Override
	public String toString() {
		return "Solicitud [origen=" + origen + ", cabecera=" + cabecera + ", getOrigen()=" + getOrigen()
				+ ", getCabecera()=" + getCabecera() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
}
