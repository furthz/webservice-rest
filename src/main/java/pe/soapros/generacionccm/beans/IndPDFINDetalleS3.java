package pe.soapros.generacionccm.beans;

public class IndPDFINDetalleS3 {
	
    private String indS3PDF;

    private String rutaURLDestinoPDF;
    

	
	public String getIndS3PDF() {
		return indS3PDF;
	}
	public void setIndS3PDF(String indS3PDF) {
		this.indS3PDF = indS3PDF;
	}
	public String getRutaURLDestinoPDF() {
		return rutaURLDestinoPDF;
	}
	public void setRutaURLDestinoPDF(String rutaURLDestinoPDF) {
		this.rutaURLDestinoPDF = rutaURLDestinoPDF;
	}
	/*
	public Gson getMetadata() {
		return metadata;
	}
	public void setMetadata(Gson metadata) {
		this.metadata = metadata;
	}

    */
	@Override
	public String toString() {
		return "IndPDFINDetalleS3 [indS3PDF=" + indS3PDF + ", rutaURLDestinoPDF=" + rutaURLDestinoPDF + "]";
	}
    
    
}
