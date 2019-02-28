package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Solicitud {
	
	@NotNull(message="Origen no puede ser nulo")
    Origen origen;
    Cabecera cabecera;
    
    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }


    
    
}
