package com.javarevolutions.siycp.ejb.entity;
// Generated 13/02/2014 04:39:40 PM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * Pagos generated by hbm2java
 */
public class Pagos  implements java.io.Serializable {


     private long idPago;
     private SeguimientoPagos seguimientoPagos;
     private double cantidadPago;
     private Date fechaPago;

    public Pagos() {
    }

    public Pagos(long idPago, SeguimientoPagos seguimientoPagos, double cantidadPago, Date fechaPago) {
       this.idPago = idPago;
       this.seguimientoPagos = seguimientoPagos;
       this.cantidadPago = cantidadPago;
       this.fechaPago = fechaPago;
    }
   
    public long getIdPago() {
        return this.idPago;
    }
    
    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }
    public SeguimientoPagos getSeguimientoPagos() {
        return this.seguimientoPagos;
    }
    
    public void setSeguimientoPagos(SeguimientoPagos seguimientoPagos) {
        this.seguimientoPagos = seguimientoPagos;
    }
    public double getCantidadPago() {
        return this.cantidadPago;
    }
    
    public void setCantidadPago(double cantidadPago) {
        this.cantidadPago = cantidadPago;
    }
    public Date getFechaPago() {
        return this.fechaPago;
    }
    
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }




}


