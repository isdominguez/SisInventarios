/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioAccesoriosxproducto.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;


public class DominioAccesoriosxproducto extends Generic {
    private static final long serialVersionUID = -7243049764867527680L;
    private Long idAccesorioxproducto;
    private Long idProducto;
    private Long idAccesorio;

    public DominioAccesoriosxproducto() {
    }

    public void setIdAccesorioxproducto(Long idAccesorioxproducto) {
        this.idAccesorioxproducto = idAccesorioxproducto;
    }
    public Long getIdAccesorioxproducto() {
        return idAccesorioxproducto;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setIdAccesorio(Long idAccesorio) {
        this.idAccesorio = idAccesorio;
    }
    public Long getIdAccesorio() {
        return idAccesorio;
    }
}
