/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javarevolutions.siycp.dominio;

import java.io.Serializable;

/**
 *
 * @author SergioRios
 */
public class DominioGraficaVenta implements Serializable {
    private Long idVenta;
    private Double totalVenta;

    /**
     * @return the idVenta
     */
    public Long getIdVenta() {
        return idVenta;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * @return the totalVenta
     */
    public Double getTotalVenta() {
        return totalVenta;
    }

    /**
     * @param totalVenta the totalVenta to set
     */
    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }
}
