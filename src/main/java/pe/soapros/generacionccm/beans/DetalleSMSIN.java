
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleSMSIN {
    @NotNull(message="indSMS de DetalleSMSIN no puede ser nulo")
    String indSMS;
    @NotNull(message="dataSource de DetalleSMSIN no puede ser nulo")
    String dataSource;
    @NotNull(message="destinatario de DetalleSMSIN no puede ser nulo")
    String[] destinatario;

    public String getIndSMS() {
        return indSMS;
    }

    public void setIndSMS(String indSMS) {
        this.indSMS = indSMS;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String[] getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String[] destinatario) {
        this.destinatario = destinatario;
    }
    
    
}
