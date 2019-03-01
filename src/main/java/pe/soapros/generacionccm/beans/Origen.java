
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Origen {

	String sistema;
	String ambiente;
	String proceso;
	String subproceso;
        String fechadeEnvio;
        String usuario;
        String identificador1;
        String identificador2;

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getSubproceso() {
		return subproceso;
	}

	public void setSubproceso(String subproceso) {
		this.subproceso = subproceso;
	}

	public String getFechadeEnvio() {
		return fechadeEnvio;
	}

	public void setFechadeEnvio(String fechadeEnvio) {
		this.fechadeEnvio = fechadeEnvio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIdentificador1() {
		return identificador1;
	}

	public void setIdentificador1(String identificador1) {
		this.identificador1 = identificador1;
	}

	public String getIdentificador2() {
		return identificador2;
	}

	public void setIdentificador2(String identificador2) {
		this.identificador2 = identificador2;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

}
