package pe.soapros.generacionccm.beans;

public class DetallePDF {
	String indPDF,indExito ,codEstado ,msgEstado  ;

	
	public String getIndPDF() {
		return indPDF;
	}

	public void setIndPDF(String indPDF) {
		this.indPDF = indPDF;
	}

	public String getIndExito() {
		return indExito;
	}

	public void setIndExito(String indExito) {
		this.indExito = indExito;
	}

	public String getCodEstado() {
		return codEstado;
	}

	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	public String getMsgEstado() {
		return msgEstado;
	}

	public void setMsgEstado(String msgEstado) {
		this.msgEstado = msgEstado;
	}

	@Override
	public String toString() {
		return "DetallePDF [indPDF=" + indPDF + ", indExito=" + indExito + ", codEstado=" + codEstado + ", msgEstado="
				+ msgEstado + "]";
	}

	



	
	
}
