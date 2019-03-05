package pe.soapros.generacionccm.beans;

public class DetalleSMS {
	String indSMS ;
	DetalleRespuesta[] numeroRespuesta ;

	

	public DetalleRespuesta[] getNumeroRespuesta() {
		return numeroRespuesta;
	}

	public void setNumeroRespuesta(DetalleRespuesta[] numeroRespuesta) {
		this.numeroRespuesta = numeroRespuesta;
	}

	public String getIndSMS() {
		return indSMS;
	}

	public void setIndSMS(String indSMS) {
		this.indSMS = indSMS;
	}

}
