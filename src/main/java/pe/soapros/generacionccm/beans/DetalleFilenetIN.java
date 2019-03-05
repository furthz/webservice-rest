package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleFilenetIN {
    
    @NotNull(message="repositoryId de DetalleFilenetIN es obligatorio")
    String repositoryId;
    
    @NotNull(message="indPDFFilenetBean de DetalleFilenetIN es obligatorio")
    IndPDFFilenetIN indPDFFilenetBean;
    
    @NotNull(message="indTXTFilenetBean de DetalleFilenetIN es obligatorio")
    IndTXTFilenetIN indTXTFilenetBean;
    
    @NotNull(message="indHTMLFilenetBean de DetalleFilenetIN es obligatorio")
    IndHTMLFilenetIN indHTMLFilenetBean;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public IndPDFFilenetIN getIndPDFFilenetBean() {
        return indPDFFilenetBean;
    }

    public void setIndPDFFilenetBean(IndPDFFilenetIN indPDFFilenetBean) {
        this.indPDFFilenetBean = indPDFFilenetBean;
    }

    public IndTXTFilenetIN getIndTXTFilenetBean() {
        return indTXTFilenetBean;
    }

    public void setIndTXTFilenetBean(IndTXTFilenetIN indTXTFilenetBean) {
        this.indTXTFilenetBean = indTXTFilenetBean;
    }

    public IndHTMLFilenetIN getIndHTMLFilenetBean() {
        return indHTMLFilenetBean;
    }

    public void setIndHTMLFilenetBean(IndHTMLFilenetIN indHTMLFilenetBean) {
        this.indHTMLFilenetBean = indHTMLFilenetBean;
    }
        
}
