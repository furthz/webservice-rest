package pe.soapros.generacionccm.beans;

import javax.validation.Valid;

public class Solicitud {

    @Valid
    Origen origen;

    @Valid
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
