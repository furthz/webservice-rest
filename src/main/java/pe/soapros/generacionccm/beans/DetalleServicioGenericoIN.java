
package pe.soapros.generacionccm.beans;

import javax.validation.constraints.NotNull;

public class DetalleServicioGenericoIN {
   @NotNull(message="IndServicioGenerico de DetalleServicioGenericoIN no puede ser nulo")
   String indServicioGenerico;
   @NotNull(message="DataSource de DetalleServicioGenericoIN no puede ser nulo")
   String dataSource;

    public String getIndServicioGenerico() {
        return indServicioGenerico;
    }

    public void setIndServicioGenerico(String indServicioGenerico) {
        this.indServicioGenerico = indServicioGenerico;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }
   
   
    
}
