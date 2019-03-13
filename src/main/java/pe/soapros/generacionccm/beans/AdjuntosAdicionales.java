package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdjuntosAdicionales {

	@NotNull(message = "adjuntosAdicionales: El nombre de los archivo de adjuntos adicionales es obligatorio")
	@Size(min = 1, max = 4000, message = "adjuntosAdicionales: El nombre del archivo no puede superar 4000 caracteres")
	private String nombreArchivo;

	@NotNull(message = "adjuntosAdicionales: El tipo de adjunto es obligatorio")
	@Size(min = 1, max = 100, message = "adjuntosAdicionales: El tamaño del tipo de adjunto no puede superar los 100 caracteres")
	private String tipoAdjunto;

	@NotNull(message = "adjuntosAdicionales: La ruta de la URL es obligatoria")
	private String rutaURLArchivo;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getTipoAdjunto() {
		return tipoAdjunto;
	}

	public void setTipoAdjunto(String tipoAdjunto) {
		this.tipoAdjunto = tipoAdjunto;
	}

	public String getRutaURLArchivo() {
		return rutaURLArchivo;
	}

	public void setRutaURLArchivo(String rutaURLArchivo) {
		this.rutaURLArchivo = rutaURLArchivo;
	}

}
