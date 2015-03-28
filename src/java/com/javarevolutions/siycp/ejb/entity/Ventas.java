package com.javarevolutions.siycp.ejb.entity;
// Generated 13/02/2014 04:39:40 PM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ventas generated by hbm2java
 */
public class Ventas  implements java.io.Serializable {


     private long idVenta;
     private Date fechaVenta;
     private Date fechaModificacion;
     private Date horaVenta;
     private String observaciones;
     private Long idUsuario;
     private Set ventasProductoses = new HashSet(0);

    public Ventas() {
    }

	
    public Ventas(long idVenta, Date fechaVenta, String observaciones) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.observaciones = observaciones;
    }
    public Ventas(long idVenta, Date fechaVenta, String observaciones, Long idUsuario, Set ventasProductoses) {
       this.idVenta = idVenta;
       this.fechaVenta = fechaVenta;
       this.observaciones = observaciones;
       this.idUsuario = idUsuario;
       this.ventasProductoses = ventasProductoses;
    }
   
    public long getIdVenta() {
        return this.idVenta;
    }
    
    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }
    public Date getFechaVenta() {
        return this.fechaVenta;
    }
    
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
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
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Long getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Set getVentasProductoses() {
        return this.ventasProductoses;
    }
    
    public void setVentasProductoses(Set ventasProductoses) {
        this.ventasProductoses = ventasProductoses;
    }




}


