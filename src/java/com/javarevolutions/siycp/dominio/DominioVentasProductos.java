/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;


public class DominioVentasProductos extends Generic {
    private static final long serialVersionUID = -767596787460896000L;
    private Long idVentaProducto;
    private Long cantidad;
    private DominioProductos productos;
    private DominioVentas ventas;

    public DominioVentasProductos() {
        productos = new DominioProductos();
        ventas = new DominioVentas();
    }

    public void setIdVentaProducto(Long idVentaProducto) {
        this.idVentaProducto = idVentaProducto;
    }
    public Long getIdVentaProducto() {
        return idVentaProducto;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setProductos(DominioProductos productos) {
        this.productos = productos;
    }
    public DominioProductos getProductos() {
        return productos;
    }
    public void setVentas(DominioVentas ventas) {
        this.ventas = ventas;
    }
    public DominioVentas getVentas() {
        return ventas;
    }
}
