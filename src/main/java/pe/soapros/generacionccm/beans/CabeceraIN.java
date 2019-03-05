package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class CabeceraIN {
    
    @NotNull(message="DetallePDFIN de Cabecera es obligatorio")
    DetallePDFIN detallePDFINBean;
    
    @NotNull(message="DetalleTXTIN de Cabecera es obligatorio")
    DetalleTXTIN detalleTXTINBean;
    
    @NotNull(message="DetalleHTMLIN de Cabecera es obligatorio")
    DetalleHTMLIN detalleHTMLINBean;
    
    @NotNull(message="DetalleCorreoIN de Cabecera es obligatorio")
    DetalleCorreoIN detalleCorreoINBean;
    
    @NotNull(message="DetalleSMSIN de Cabecera es obligatorio")
    DetalleSMSIN detalleSMSINBean;
    
    @NotNull(message="DetalleTrazabilidadCorreoIN de Cabecera es obligatorio")
    DetalleTrazabilidadCorreoIN DetalleTrazabilidadCorreoINBean;
    
    @NotNull(message="DetalleServicioGenericoIN de Cabecera es obligatorio")
    DetalleServicioGenericoIN detalleServicioGenericoBean;
    
    @NotNull(message="DetalleS3IN de Cabecera es obligatorio")
    DetalleS3IN detalleS3Bean;
    
    @NotNull(message="DetalleFilenetIN de Cabecera es obligatorio")
    DetalleFilenetIN detalleFilenetBean;

    public DetallePDFIN getDetallePDFINBean() {
        return detallePDFINBean;
    }

    public void setDetallePDFINBean(DetallePDFIN detallePDFINBean) {
        this.detallePDFINBean = detallePDFINBean;
    }

    public DetalleTXTIN getDetalleTXTINBean() {
        return detalleTXTINBean;
    }

    public void setDetalleTXTINBean(DetalleTXTIN detalleTXTINBean) {
        this.detalleTXTINBean = detalleTXTINBean;
    }

    public DetalleHTMLIN getDetalleHTMLINBean() {
        return detalleHTMLINBean;
    }

    public void setDetalleHTMLINBean(DetalleHTMLIN detalleHTMLINBean) {
        this.detalleHTMLINBean = detalleHTMLINBean;
    }

    public DetalleCorreoIN getDetalleCorreoINBean() {
        return detalleCorreoINBean;
    }

    public void setDetalleCorreoINBean(DetalleCorreoIN detalleCorreoINBean) {
        this.detalleCorreoINBean = detalleCorreoINBean;
    }

    public DetalleSMSIN getDetalleSMSINBean() {
        return detalleSMSINBean;
    }

    public void setDetalleSMSINBean(DetalleSMSIN detalleSMSINBean) {
        this.detalleSMSINBean = detalleSMSINBean;
    }

    public DetalleTrazabilidadCorreoIN getDetalleTrazabilidadCorreoINBean() {
        return DetalleTrazabilidadCorreoINBean;
    }

    public void setDetalleTrazabilidadCorreoINBean(DetalleTrazabilidadCorreoIN DetalleTrazabilidadCorreoINBean) {
        this.DetalleTrazabilidadCorreoINBean = DetalleTrazabilidadCorreoINBean;
    }

    public DetalleServicioGenericoIN getDetalleServicioGenericoBean() {
        return detalleServicioGenericoBean;
    }

    public void setDetalleServicioGenericoBean(DetalleServicioGenericoIN detalleServicioGenericoBean) {
        this.detalleServicioGenericoBean = detalleServicioGenericoBean;
    }

    public DetalleS3IN getDetalleS3Bean() {
        return detalleS3Bean;
    }

    public void setDetalleS3Bean(DetalleS3IN detalleS3Bean) {
        this.detalleS3Bean = detalleS3Bean;
    }

    public DetalleFilenetIN getDetalleFilenetBean() {
        return detalleFilenetBean;
    }

    public void setDetalleFilenetBean(DetalleFilenetIN detalleFilenetBean) {
        this.detalleFilenetBean = detalleFilenetBean;
    }

}
