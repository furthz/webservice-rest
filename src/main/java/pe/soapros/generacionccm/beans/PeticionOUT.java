package pe.soapros.generacionccm.beans;

public class PeticionOUT  {

	Origen origen;
	CabeceraIN cabecera;
	String numOperacion;
	String estado;
	
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
	public String getNumOperacion() {
		return numOperacion;
	}
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	
	
}	