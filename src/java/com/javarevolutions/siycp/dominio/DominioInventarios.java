/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioInventarios.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;

import javax.validation.constraints.Min;


public class DominioInventarios extends Generic {
    private static final long serialVersionUID = -6011538992386686976L;
    private Long idInventario;
    @Min(value = 0, message = "Ingresa un valor mayor o igual a 0")
    private Long cantidad;
    private long existencia;
    @Min(value = 10, message = "Ingresa un valor mayor o igual a 10")
    private Integer minimoExistencia;
    private String style;
    private DominioProductos productos;

    public DominioInventarios() {
        productos = new DominioProductos();
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }
    public Long getIdInventario() {
        return idInventario;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Long getCantidad() {
        return cantidad;
    }

    /**
     * @return the existencia
     */
    public long getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(long existencia) {
        this.existencia = existencia;
    }

    /**
     * @return the minimoExistencia
     */
    public Integer getMinimoExistencia() {
        return minimoExistencia;
    }

    /**
     * @param minimoExistencia the minimoExistencia to set
     */
    public void setMinimoExistencia(Integer minimoExistencia) {
        this.minimoExistencia = minimoExistencia;
    }

    /**
     * @return the style
     */
    public String getStyle() {
        return style;
    }

    /**
     * @param style the style to set
     */
    public void setStyle(String style) {
        this.style = style;
    }
    public void setProductos(DominioProductos productos) {
        this.productos = productos;
    }
    public DominioProductos getProductos() {
        return productos;
    }
}
