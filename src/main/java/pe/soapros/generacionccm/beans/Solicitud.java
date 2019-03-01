package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Solicitud {
    
    @NotNull(message="Origen no puede ser nulo")
    Origen origen;
    
    CabeceraIN cabeceraBean;

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public CabeceraIN getCabeceraBean() {
        return cabeceraBean;
    }

    public void setCabeceraBean(CabeceraIN cabeceraBean) {
        this.cabeceraBean = cabeceraBean;
    }
    
}
