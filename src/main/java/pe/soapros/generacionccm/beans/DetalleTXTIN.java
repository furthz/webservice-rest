package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleTXTIN {
    
    @NotNull(message="IndTXT no puede ser nulo")
    String indTXT;
    
    @NotNull(message="Codigo de Plantilla no puede ser nulo")
    String codigoPlantilla;
    
    @NotNull(message="Nombre de Documento no puede ser nulo")
    String nombreDocumento;
    
    @NotNull(message="Ind Guardado no puede ser nulo")
    String indGuardado;

    public String getIndTXT() {
        return indTXT;
    }

    public void setIndTXT(String indTXT) {
        this.indTXT = indTXT;
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
