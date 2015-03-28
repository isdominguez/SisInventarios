/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioProveedoresProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;


public class DominioProveedoresProductos extends Generic {
    private static final long serialVersionUID = -2227064219029389568L;
    private Long idProveedorProducto;
    private DominioUsuarios usuarios;
    private DominioProductos productos;

    public DominioProveedoresProductos() {
        usuarios = new DominioUsuarios();
        productos = new DominioProductos();
    }

    public void setIdProveedorProducto(Long idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }
    public Long getIdProveedorProducto() {
        return idProveedorProducto;
    }
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }
    public void setProductos(DominioProductos productos) {
        this.productos = productos;
    }
    public DominioProductos getProductos() {
        return productos;
    }
}
