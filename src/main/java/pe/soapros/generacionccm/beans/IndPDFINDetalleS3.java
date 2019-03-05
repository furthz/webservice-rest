package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class IndPDFINDetalleS3 {
    @NotNull(message="indS3PDF de IndPDFINDetalleS3 es obligatorio")
    String indS3PDF;
    
    @NotNull(message="metadata de IndPDFINDetalleS3 es obligatorio")
    String metadata;
    
    @NotNull(message="rutaURLDestinoPDF de IndPDFINDetalleS3 es obligatorio")
    String rutaURLDestinoPDF;

    public String getIndS3PDF() {
        return indS3PDF;
    }

    public void setIndS3PDF(String indS3PDF) {
        this.indS3PDF = indS3PDF;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRutaURLDestinoPDF() {
        return rutaURLDestinoPDF;
    }

    public void setRutaURLDestinoPDF(String rutaURLDestinoPDF) {
        this.rutaURLDestinoPDF = rutaURLDestinoPDF;
    }
    
    
    
}
