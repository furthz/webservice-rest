package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class Respuesta {
    @NotNull(message="Origen de Respuesta es obligatorio")
    Origen origen;
    
    @NotNull(message="NumOperacion de Respuesta es obligatorio")
    String numOperacion;
    
    @NotNull(message="docBase64 de Respuesta es obligatorio")
    String docBase64;


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

    public String getDocBase64() {
        return docBase64;
    }

    public void setDocBase64(String docBase64) {
        this.docBase64 = docBase64;
    }
      
}
