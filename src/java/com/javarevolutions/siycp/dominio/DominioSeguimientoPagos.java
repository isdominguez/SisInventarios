/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioSeguimientoPagos.java
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

public class DominioSeguimientoPagos extends Generic {
    private static final long serialVersionUID = -4188278483615061504L;
    private Integer idSeguimientoPago;
    private List<DominioPagos> pagosList;
    private Double totalDeuda;
    private DominioUsuarios usuarios;

    public DominioSeguimientoPagos() {
        usuarios = new DominioUsuarios();
    }

    public void setPagosList(List<DominioPagos> pagosList) {
        this.pagosList = pagosList;
    }
    public List<DominioPagos> getPagosList() {
        return pagosList;
    }
    public void setIdSeguimientoPago(Integer idSeguimientoPago) {
        this.idSeguimientoPago = idSeguimientoPago;
    }
    public Integer getIdSeguimientoPago() {
        return idSeguimientoPago;
    }
    public void setTotalDeuda(Double totalDeuda) {
        this.totalDeuda = totalDeuda;
    }
    public Double getTotalDeuda() {
        return totalDeuda;
    }
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }
}
