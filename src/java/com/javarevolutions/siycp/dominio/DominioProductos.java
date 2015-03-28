/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioProductos.java
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
import javax.validation.constraints.Min;

public class DominioProductos extends Generic {
    private static final long serialVersionUID = -7355589561428508672L;
    private Long idProducto;
    private List<DominioInventarios> inventariosList;
    private String nombre;
    private String descripcion;
    private Float costo;
    private Float precioGeneral;
    private Float precioIntermedio;
    private Float precioEspecial;
    private String tipo;
    @Min(value = 1, message = "Ingresa un valor mayor o igual a 1")
    private Long cantidad;
    private DominioTiposMedidas tiposMedidas;
    private DominioUsuarios usuarios;
    private Long existencia;
    private String entidadPersistir;

    public DominioProductos() {
        tiposMedidas = new DominioTiposMedidas();
        usuarios = new DominioUsuarios();
    }

    public void setInventariosList(List<DominioInventarios> inventariosList) {
        this.inventariosList = inventariosList;
    }
    public List<DominioInventarios> getInventariosList() {
        return inventariosList;
    }
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Long getIdProducto() {
        return idProducto;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setCosto(Float costo) {
        this.costo = costo;
    }
    public Float getCosto() {
        return costo;
    }
    public void setPrecioGeneral(Float precioGeneral) {
        this.precioGeneral = precioGeneral;
    }
    public Float getPrecioGeneral() {
        return precioGeneral;
    }
    public void setPrecioIntermedio(Float precioIntermedio) {
        this.precioIntermedio = precioIntermedio;
    }
    public Float getPrecioIntermedio() {
        return precioIntermedio;
    }
    public void setPrecioEspecial(Float precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
    public Float getPrecioEspecial() {
        return precioEspecial;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
    public Long getCantidad() {
        return cantidad;
    }
    public void setTiposMedidas(DominioTiposMedidas tiposMedidas) {
        this.tiposMedidas = tiposMedidas;
    }
    public DominioTiposMedidas getTiposMedidas() {
        return tiposMedidas;
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
     * @return the existencia
     */
    public Long getExistencia() {
        return existencia;
    }

    /**
     * @param existencia the existencia to set
     */
    public void setExistencia(Long existencia) {
        this.existencia = existencia;
    }

    /**
     * @return the entidadPersistir
     */
    public String getEntidadPersistir() {
        return entidadPersistir;
    }

    /**
     * @param entidadPersistir the entidadPersistir to set
     */
    public void setEntidadPersistir(String entidadPersistir) {
        this.entidadPersistir = entidadPersistir;
    }
}
