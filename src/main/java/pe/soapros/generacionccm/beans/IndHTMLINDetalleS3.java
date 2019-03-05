package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class IndHTMLINDetalleS3 {
    
    @NotNull(message="indS3HTML de IndHTMLINDetalleS3 es obligatorio")
    String indS3HTML;
    @NotNull(message="metadata de IndHTMLINDetalleS3 es obligatorio")
    String metadata;
    @NotNull(message="rutaURLDestinoHTML de IndHTMLINDetalleS3 es obligatorio")
    String rutaURLDestinoHTML;

    public String getIndS3HTML() {
        return indS3HTML;
    }

    public void setIndS3HTML(String indS3HTML) {
        this.indS3HTML = indS3HTML;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRutaURLDestinoHTML() {
        return rutaURLDestinoHTML;
    }

    public void setRutaURLDestinoHTML(String rutaURLDestinoHTML) {
        this.rutaURLDestinoHTML = rutaURLDestinoHTML;
    }
    
    
    
}
