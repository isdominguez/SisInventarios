/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioTiposMedidas.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;

import java.util.List;

public class DominioTiposMedidas extends Generic {
    private static final long serialVersionUID = -7619642352456910848L;
    private Integer idTipoMedida;
    private List<DominioProductos> productosList;
    private String tipoMedida;

    public DominioTiposMedidas() {
    }

    public void setProductosList(List<DominioProductos> productosList) {
        this.productosList = productosList;
    }
    public List<DominioProductos> getProductosList() {
        return productosList;
    }
    public void setIdTipoMedida(Integer idTipoMedida) {
        this.idTipoMedida = idTipoMedida;
    }
    public Integer getIdTipoMedida() {
        return idTipoMedida;
    }
    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }
    public String getTipoMedida() {
        return tipoMedida;
    }
}
