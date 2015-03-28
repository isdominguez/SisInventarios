/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioPedidos.java
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
import java.util.Date;

public class DominioPedidos extends Generic {
    private static final long serialVersionUID = -6989741384301136896L;
    private Long idPedido;
    private List<DominioPedidosProductos> pedidosProductosList;
    private Date fechaPedido;
    private Date fechaModificacion;
    private Date horaPedido;
    private String condiciones;
    private DominioUsuarios usuarios;
    private Date fechaInicio;
    private Date fechaFin;
    private double totalPagar;

    public DominioPedidos() {
        usuarios = new DominioUsuarios();
    }

    public void setPedidosProductosList(List<DominioPedidosProductos> pedidosProductosList) {
        this.pedidosProductosList = pedidosProductosList;
    }
    public List<DominioPedidosProductos> getPedidosProductosList() {
        return pedidosProductosList;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public Long getIdPedido() {
        return idPedido;
    }
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public Date getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    public Date getFechaModificacion() {
        return fechaModificacion;
    }
    public void setHoraPedido(Date horaPedido) {
        this.horaPedido = horaPedido;
    }
    public Date getHoraPedido() {
        return horaPedido;
    }
    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }
    public String getCondiciones() {
        return condiciones;
    }
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the totalPagar
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * @param totalPagar the totalPagar to set
     */
    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }
}
