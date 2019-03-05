
package pe.soapros.generacionccm.beans;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class Origen {
	@NotNull(message="Sistema es obligatorio")
	String sistema;
	@NotNull(message="ambiente es obligatorio")
	String ambiente;
	@NotNull(message="proceso es obligatorio")
	String proceso;
	@NotNull(message="subproceso es obligatorio")
	String subproceso;
	@NotNull(message="fechadeEnvio es obligatorio")
	String fechadeEnvio;
	@NotNull(message="usuario es obligatorio")
	String usuario;
	@NotNull(message="identificador1 es obligatorio")

	String[] identificador;
	
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

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

    public String[] getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String[] identificador) {
        this.identificador = identificador;
    }

}
