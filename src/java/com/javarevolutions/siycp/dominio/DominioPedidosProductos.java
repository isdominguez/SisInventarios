/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioPedidosProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;


public class DominioPedidosProductos extends Generic {
    private static final long serialVersionUID = -8919765636573821952L;
    private Long idPedidoProducto;
    private DominioPedidos pedidos;
    private DominioProductos productos;
    private Long cantidad;

    public DominioPedidosProductos() {
        pedidos = new DominioPedidos();
        productos = new DominioProductos();
    }

    public void setIdPedidoProducto(Long idPedidoProducto) {
        this.idPedidoProducto = idPedidoProducto;
    }
    public Long getIdPedidoProducto() {
        return idPedidoProducto;
    }
    public void setPedidos(DominioPedidos pedidos) {
        this.pedidos = pedidos;
    }
    public DominioPedidos getPedidos() {
        return pedidos;
    }
    public void setProductos(DominioProductos productos) {
        this.productos = productos;
    }
    public DominioProductos getProductos() {
        return productos;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Long getCantidad() {
        return cantidad;
    }
}
