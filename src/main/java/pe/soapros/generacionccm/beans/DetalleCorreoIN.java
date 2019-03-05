package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleCorreoIN {
    @NotNull(message="IndCorreo de DetalleCorreoIN no puede ser nulo")
    String indCorreo;

    public String getIndCorreo() {
        return indCorreo;
    }

    public void setIndCorreo(String indCorreo) {
        this.indCorreo = indCorreo;
    }
    
    
    
}
