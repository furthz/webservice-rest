package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleHTMLIN
{
    @NotNull(message="Ind HTML de DetalleHTMLIN no puede ser nulo")
    String indHTML;
    @NotNull(message="Codigo Plantilla de DetalleHTMLIN no puede ser nulo")
    String codigoPlantilla;
    @NotNull(message="Nombre Documento de DetalleHTMLIN no puede ser nulo")
    String nombreDocumento;
    @NotNull(message="Ind Guardado de DetalleHTMLIN no puede ser nulo")
    String indGuardado;

    public String getIndHTML() {
        return indHTML;
    }

    public void setIndHTML(String indHTML) {
        this.indHTML = indHTML;
    }

    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getIndGuardado() {
        return indGuardado;
    }

    public void setIndGuardado(String indGuardado) {
        this.indGuardado = indGuardado;
    }
    
    
}
