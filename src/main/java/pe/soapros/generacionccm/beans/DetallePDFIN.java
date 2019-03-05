package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetallePDFIN {
        //Input Solicitud Petición
        @NotNull(message="IndPDF indPDF no puede ser nulo")
	String indPDF;
        
        @NotNull(message="CodigoPlantilla no puede ser nulo")
        String codigoPlantilla;
        
        @NotNull(message="IndVisualizacion no puede ser nulo")
        String indVisualizacion;
        
        @NotNull(message="NombreDocumento no puede ser nulo")
        String nombreDocumento;
        
        @NotNull(message="IndGuardado no puede ser nulo")
        String indGuardado;
        
    public String getIndPDF() {
            return indPDF;
    }

    public void setIndPDF(String indPDF) {
            this.indPDF = indPDF;
    }



    public String getCodigoPlantilla() {
        return codigoPlantilla;
    }

    public void setCodigoPlantilla(String codigoPlantilla) {
        this.codigoPlantilla = codigoPlantilla;
    }

    public String getIndVisualizacion() {
        return indVisualizacion;
    }

    public void setIndVisualizacion(String indVisualizacion) {
        this.indVisualizacion = indVisualizacion;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getIndGuardado() {
        return indGuardado;
    }

    public void setIndGuardado(String indGuardado) {
        this.indGuardado = indGuardado;
    }

	
	
}
