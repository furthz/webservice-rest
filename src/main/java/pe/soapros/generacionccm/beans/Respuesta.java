package pe.soapros.generacionccm.beans;

public class Respuesta {
    Origen origen;
    String numOperacion, pdf;
    

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }
    public String getCodigoTransaccion() {
        return numOperacion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.numOperacion = codigoTransaccion;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
