
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleTrazabilidadCorreoIN {
   @NotNull(message="IndTrazabilidad de DetalleTrazabilidadCorreoIN no puede ser nulo")
   String indTrazabilidad;
   
   @NotNull(message="DataSource de DetalleTrazabilidadCorreoIN no puede ser nulo")
   String dataSource;

    public String getIndTrazabilidad() {
        return indTrazabilidad;
    }

    public void setIndTrazabilidad(String indTrazabilidad) {
        this.indTrazabilidad = indTrazabilidad;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
    
}
