package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Respuesta {
    @NotNull(message="Origen de Respuesta es obligatorio")
    Origen origen;
    @NotNull(message="NumOperacion de Respuesta es obligatorio")
    String numOperacion, pdf;


    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public String getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(String numOperacion) {
        this.numOperacion = numOperacion;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    } 
}
