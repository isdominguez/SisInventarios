/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioVentas.java
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

public class DominioVentas extends Generic {
    private static final long serialVersionUID = -882277947891197696L;
    private Long idVenta;
    private List<DominioVentasProductos> ventasProductosList;
    private Date fechaVenta;
    private Date fechaModificacion;
    private Date horaVenta;
    private String observaciones;
    private Long idUsuario;
    private DominioUsuarios usuarios;
    private Date fechaInicio;
    private Date fechaFin;
    private double totalPagar;

    public DominioVentas() {
        usuarios = new DominioUsuarios();
    }

    public void setVentasProductosList(List<DominioVentasProductos> ventasProductosList) {
        this.ventasProductosList = ventasProductosList;
    }
    public List<DominioVentasProductos> getVentasProductosList() {
        return ventasProductosList;
    }
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }
    public Long getIdVenta() {
        return idVenta;
    }
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
    public Date getFechaVenta() {
        return fechaVenta;
    }

    /**
     * @return the fechaModificacion
     */
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    /**
     * @return the horaVenta
     */
    public Date getHoraVenta() {
        return horaVenta;
    }

    /**
     * @param horaVenta the horaVenta to set
     */
    public void setHoraVenta(Date horaVenta) {
        this.horaVenta = horaVenta;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @return the usuarios
     */
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
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
