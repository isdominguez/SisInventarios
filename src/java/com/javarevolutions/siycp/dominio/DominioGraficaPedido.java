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
public class DominioGraficaPedido implements Serializable {
    private Long idPedido;
    private Double totalVenta;

    /**
     * @return the idPedido
     */
    public Long getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
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
