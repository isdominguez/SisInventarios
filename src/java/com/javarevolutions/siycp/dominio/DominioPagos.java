/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;

import java.util.Date;

public class DominioPagos extends Generic {
    private static final long serialVersionUID = -7670355535308447744L;
    private Long idPago;
    private Double cantidadPago;
    private Date fechaPago;
    private DominioSeguimientoPagos seguimientoPagos;

    public DominioPagos() {
        seguimientoPagos = new DominioSeguimientoPagos();
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }
    public Long getIdPago() {
        return idPago;
    }
    public void setCantidadPago(Double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }
    public Double getCantidadPago() {
        return cantidadPago;
    }
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }
    public Date getFechaPago() {
        return fechaPago;
    }
    public void setSeguimientoPagos(DominioSeguimientoPagos seguimientoPagos) {
        this.seguimientoPagos = seguimientoPagos;
    }
    public DominioSeguimientoPagos getSeguimientoPagos() {
        return seguimientoPagos;
    }
}
