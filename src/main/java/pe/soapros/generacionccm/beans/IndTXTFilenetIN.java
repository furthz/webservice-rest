
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

class IndTXTFilenetIN {
    
    @NotNull(message="indFilenetTXT de IndTXTFilenetIN es obligatorio")
    String indFilenetTXT;
    
    @NotNull(message="PropiedadesFilenetTXT de IndTXTFilenetIN es obligatorio")
    PropiedadesFilenetTXT PropiedadesFilenetPDFBean;
    
    @NotNull(message="ContentStreamFilenetTXT de IndTXTFilenetIN es obligatorio")
    ContentStreamFilenetTXT contentStreamFilenetPDFBean;

    public String getIndFilenetTXT() {
        return indFilenetTXT;
    }

    public void setIndFilenetTXT(String indFilenetTXT) {
        this.indFilenetTXT = indFilenetTXT;
    }

    public PropiedadesFilenetTXT getPropiedadesFilenetPDFBean() {
        return PropiedadesFilenetPDFBean;
    }

    public void setPropiedadesFilenetPDFBean(PropiedadesFilenetTXT PropiedadesFilenetPDFBean) {
        this.PropiedadesFilenetPDFBean = PropiedadesFilenetPDFBean;
    }

    public ContentStreamFilenetTXT getContentStreamFilenetPDFBean() {
        return contentStreamFilenetPDFBean;
    }

    public void setContentStreamFilenetPDFBean(ContentStreamFilenetTXT contentStreamFilenetPDFBean) {
        this.contentStreamFilenetPDFBean = contentStreamFilenetPDFBean;
    }
    
    
    
}
