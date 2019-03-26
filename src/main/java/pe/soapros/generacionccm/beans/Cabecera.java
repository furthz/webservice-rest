package pe.soapros.generacionccm.beans;

public class Cabecera {
	
	private DetallePDF detallepdf;
	private DetalleTXT detalleTXT;
	private detalleHTML detalleHTML;
	private DetalleSMS EnvioSMS;
	private DetalleTrazabilidad detalleTrazabilidad;
	private DetalleServicio detalleServicio;
	private AlmacenamientoS3 almacenamientoS3;
	private AlmacenamientoFilenet almacenamientoFilenet;
	
	
	public AlmacenamientoS3 getAlmacenamientoS3() {
		return almacenamientoS3;
	}
	public void setAlmacenamientoS3(AlmacenamientoS3 almacenamientoS3) {
		this.almacenamientoS3 = almacenamientoS3;
	}
	public AlmacenamientoFilenet getAlmacenamientoFilenet() {
		return almacenamientoFilenet;
	}
	public void setAlmacenamientoFilenet(AlmacenamientoFilenet almacenamientoFilenet) {
		this.almacenamientoFilenet = almacenamientoFilenet;
	}
	public DetallePDF getDetallepdf() {
		return detallepdf;
	}
	public void setDetallepdf(DetallePDF detallepdf) {
		this.detallepdf = detallepdf;
	}
	
	public detalleHTML getDetalleHTML() {
		return detalleHTML;
	}
	public void setDetalleHTML(detalleHTML detalleHTML) {
		this.detalleHTML = detalleHTML;
	}
	
	public DetalleSMS getEnvioSMS() {
		return EnvioSMS;
	}
	public void setEnvioSMS(DetalleSMS envioSMS) {
		EnvioSMS = envioSMS;
	}
	public DetalleServicio getDetalleServicio() {
		return detalleServicio;
	}
	public void setDetalleServicio(DetalleServicio detalleServicio) {
		this.detalleServicio = detalleServicio;
	}
	public DetalleTXT getDetalleTXT() {
		return detalleTXT;
	}
	public void setDetalleTXT(DetalleTXT detalleTXT) {
		this.detalleTXT = detalleTXT;
	}
	public DetalleTrazabilidad getDetalleTrazabilidad() {
		return detalleTrazabilidad;
	}
	public void setDetalleTrazabilidad(DetalleTrazabilidad detalleTrazabilidad) {
		this.detalleTrazabilidad = detalleTrazabilidad;
	}
	
	
	@Override
	public String toString() {
		return "Cabecera [detallepdf=" + detallepdf + ", detalleTXT=" + detalleTXT + ", detalleHTML=" + detalleHTML
				+ ", EnvioSMS=" + EnvioSMS + ", detalleTrazabilidad=" + detalleTrazabilidad + ", detalleServicio="
				+ detalleServicio + ", almacenamientoS3=" + almacenamientoS3 + ", almacenamientoFilenet="
				+ almacenamientoFilenet + "]";
	}
		
	
}
