package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class IndPDFFilenetIN {
    @NotNull(message="indFilenetPDF de IndPDFFilenetIN es obligatorio")
    String indFilenetPDF;
    
    @NotNull(message="PropiedadesFilenetPDF de IndPDFFilenetIN es obligatorio")
            
    PropiedadesFilenetPDF PropiedadesFilenetPDFBean;
    
    @NotNull(message="ContentStreamFilenetPDF de IndPDFFilenetIN es obligatorio")
    ContentStreamFilenetPDF contentStreamFilenetPDFBean;

    public String getIndFilenetPDF() {
        return indFilenetPDF;
    }

    public void setIndFilenetPDF(String indFilenetPDF) {
        this.indFilenetPDF = indFilenetPDF;
    }

    public PropiedadesFilenetPDF getPropiedadesFilenetPDFBean() {
        return PropiedadesFilenetPDFBean;
    }

    public void setPropiedadesFilenetPDFBean(PropiedadesFilenetPDF PropiedadesFilenetPDFBean) {
        this.PropiedadesFilenetPDFBean = PropiedadesFilenetPDFBean;
    }

    public ContentStreamFilenetPDF getContentStreamFilenetPDFBean() {
        return contentStreamFilenetPDFBean;
    }

    public void setContentStreamFilenetPDFBean(ContentStreamFilenetPDF contentStreamFilenetPDFBean) {
        this.contentStreamFilenetPDFBean = contentStreamFilenetPDFBean;
    }
    
    
    
}
