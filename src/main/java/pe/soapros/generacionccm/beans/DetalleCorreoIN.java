package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleCorreoIN {
    @NotNull(message="IndCorreo de DetalleCorreoIN no puede ser nulo")
    String indCorreo;
    
    @NotNull(message="de de DetalleCorreoIN no puede ser nulo")
    String  de;
    
    @NotNull(message="aliasDe de DetalleCorreoIN no puede ser nulo")
    String  aliasDe;
    
    @NotNull(message="para de DetalleCorreoIN no puede ser nulo")
    String[] para;
    
    @NotNull(message="conCopia de DetalleCorreoIN no puede ser nulo")
    String[] conCopia;
    
    @NotNull(message="conCopiaOculta de DetalleCorreoIN no puede ser nulo")
    String[] conCopiaOculta;
    
    @NotNull(message="asuntoPlantilla de DetalleCorreoIN no puede ser nulo")
    String asuntoPlantilla;
    
    @NotNull(message="asunto de DetalleCorreoIN no puede ser nulo")
    String asunto;
    
    @NotNull(message="AdjuntoCorreo de DetalleCorreoIN no puede ser nulo")
    AdjuntoCorreo[] adjuntosadicionalesBean;

    public String getIndCorreo() {
        return indCorreo;
    }

    public void setIndCorreo(String indCorreo) {
        this.indCorreo = indCorreo;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String[] getPara() {
        return para;
    }

    public void setPara(String[] para) {
        this.para = para;
    }

    public String[] getConCopia() {
        return conCopia;
    }

    public void setConCopia(String[] conCopia) {
        this.conCopia = conCopia;
    }

    public String[] getConCopiaOculta() {
        return conCopiaOculta;
    }

    public void setConCopiaOculta(String[] conCopiaOculta) {
        this.conCopiaOculta = conCopiaOculta;
    }

    public String getAsuntoPlantilla() {
        return asuntoPlantilla;
    }

    public void setAsuntoPlantilla(String asuntoPlantilla) {
        this.asuntoPlantilla = asuntoPlantilla;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public AdjuntoCorreo[] getAdjuntosadicionalesBean() {
        return adjuntosadicionalesBean;
    }

    public void setAdjuntosadicionalesBean(AdjuntoCorreo[] adjuntosadicionalesBean) {
        this.adjuntosadicionalesBean = adjuntosadicionalesBean;
    }

    public String getAliasDe() {
        return aliasDe;
    }

    public void setAliasDe(String aliasDe) {
        this.aliasDe = aliasDe;
    }
     
}
