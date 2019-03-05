package pe.soapros.generacionccm.beans;

import javax.validation.Valid;

public class Solicitud {
    
    //@NotNull(message="Origen no puede ser nulo")
    @Valid
    Origen origen;
    
    //@NotNull(message="Cabecera no puede ser nulo")
    //@Valid
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
