package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class IndTXTINDetalleS3 {
    @NotNull(message="indS3TXT de IndTXTINDetalleS3 es obligatorio")
    String indS3TXT;
    @NotNull(message="metadata de IndTXTINDetalleS3 es obligatorio")
    String metadata;
    @NotNull(message="rutaURLDestinoTXT de IndTXTINDetalleS3 es obligatorio")
    String rutaURLDestinoTXT;

    public String getIndS3TXT() {
        return indS3TXT;
    }

    public void setIndS3TXT(String indS3TXT) {
        this.indS3TXT = indS3TXT;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getRutaURLDestinoTXT() {
        return rutaURLDestinoTXT;
    }

    public void setRutaURLDestinoTXT(String rutaURLDestinoTXT) {
        this.rutaURLDestinoTXT = rutaURLDestinoTXT;
    }
    
    
}
