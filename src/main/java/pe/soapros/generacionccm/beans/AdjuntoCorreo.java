package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class AdjuntoCorreo {

    @NotNull(message = "nombreArchivo de AdjuntoCorreo es obligatorio")
    String nombreArchivo;

    @NotNull(message = "tipoAdjunto de AdjuntoCorreo es obligatorio")
    String tipoAdjunto;

    @NotNull(message = "rutaURLArchivo de AdjuntoCorreo es obligatorio")
    String rutaURLArchivo;

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
