/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioUsuarios.java
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

public class DominioUsuarios extends Generic {
    private static final long serialVersionUID = -5485298886351869952L;
    private Long idUsuario;
    private List<DominioHorarioEmpleados> horarioEmpleadosList;
    private String nombre;
    private String app;
    private String apm;
    private String telefono;
    private String celular;
    private String otroTelefono;
    private String direccion;
    private String calle;
    private Integer numero;
    private String colonia;
    private Integer codigoPostal;
    private String ciudad;
    private String estado;
    private String tipoPersona;
    private String tipoCliente;

    public DominioUsuarios() {
    }

    public void setHorarioEmpleadosList(List<DominioHorarioEmpleados> horarioEmpleadosList) {
        this.horarioEmpleadosList = horarioEmpleadosList;
    }
    public List<DominioHorarioEmpleados> getHorarioEmpleadosList() {
        return horarioEmpleadosList;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setApp(String app) {
        this.app = app;
    }
    public String getApp() {
        return app;
    }
    public void setApm(String apm) {
        this.apm = apm;
    }
    public String getApm() {
        return apm;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getCelular() {
        return celular;
    }
    public void setOtroTelefono(String otroTelefono) {
        this.otroTelefono = otroTelefono;
    }
    public String getOtroTelefono() {
        return otroTelefono;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCalle() {
        return calle;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public String getColonia() {
        return colonia;
    }
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public Integer getCodigoPostal() {
        return codigoPostal;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getEstado() {
        return estado;
    }
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public String getTipoCliente() {
        return tipoCliente;
    }
}
