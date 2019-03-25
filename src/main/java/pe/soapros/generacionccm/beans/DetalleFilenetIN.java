package pe.soapros.generacionccm.beans;

public class DetalleFilenetIN {

	private String repositoryId;
	private IndPDFFilenetIN indPDF;
	private IndTXTFilenetIN indTXT;
	private IndHTMLFilenetIN indHTML;
	
	
	public String getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}
	public IndPDFFilenetIN getIndPDF() {
		return indPDF;
	}
	public void setIndPDF(IndPDFFilenetIN indPDF) {
		this.indPDF = indPDF;
	}
	public IndTXTFilenetIN getIndTXT() {
		return indTXT;
	}
	public void setIndTXT(IndTXTFilenetIN indTXT) {
		this.indTXT = indTXT;
	}
	public IndHTMLFilenetIN getIndHTML() {
		return indHTML;
	}
	public void setIndHTML(IndHTMLFilenetIN indHTML) {
		this.indHTML = indHTML;
	}
	
	
	@Override
	public String toString() {
		return "DetalleFilenetIN [repositoryId=" + repositoryId + ", indPDF=" + indPDF + ", indTXT=" + indTXT
				+ ", indHTML=" + indHTML + "]";
	}

	

}
