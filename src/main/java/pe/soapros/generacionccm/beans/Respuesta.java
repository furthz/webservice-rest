package pe.soapros.generacionccm.beans;

public class Respuesta {
    Origen origen;
    String numOperacion;
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

    public void setDocBase64(String pdf) {
        this.docBase64 = pdf;
    } 
}
