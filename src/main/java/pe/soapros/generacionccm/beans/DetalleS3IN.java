
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleS3IN {
    
    @NotNull(message="IndPDFINDetalleS3 de DetalleS3INes obligatorio")
    IndPDFINDetalleS3 indPDFInDetalleS3Bean;
    
    @NotNull(message="IndTXTINDetalleS3 de DetalleS3IN es obligatorio")
    IndTXTINDetalleS3 indTXTInDetalleS3Bean;
    
    @NotNull(message="IndHTMLINDetalleS3 de DetalleS3IN es obligatorio")
    IndHTMLINDetalleS3 indHTMLInDetalleS3Bean;

    public IndPDFINDetalleS3 getIndPDFInDetalleS3Bean() {
        return indPDFInDetalleS3Bean;
    }

    public void setIndPDFInDetalleS3Bean(IndPDFINDetalleS3 indPDFInDetalleS3Bean) {
        this.indPDFInDetalleS3Bean = indPDFInDetalleS3Bean;
    }

    public IndTXTINDetalleS3 getIndTXTInDetalleS3Bean() {
        return indTXTInDetalleS3Bean;
    }

    public void setIndTXTInDetalleS3Bean(IndTXTINDetalleS3 indTXTInDetalleS3Bean) {
        this.indTXTInDetalleS3Bean = indTXTInDetalleS3Bean;
    }

    public IndHTMLINDetalleS3 getIndHTMLInDetalleS3Bean() {
        return indHTMLInDetalleS3Bean;
    }

    public void setIndHTMLInDetalleS3Bean(IndHTMLINDetalleS3 indHTMLInDetalleS3Bean) {
        this.indHTMLInDetalleS3Bean = indHTMLInDetalleS3Bean;
    }

  
}
