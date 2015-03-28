package com.javarevolutions.siycp.ejb.entity;
// Generated 13/02/2014 04:39:40 PM by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private Long idUsuario;
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
     private Set horarioEmpleadoses = new HashSet(0);
     private Set productoses = new HashSet(0);
     private Set userses = new HashSet(0);
     private Set pedidoses = new HashSet(0);
     private Set seguimientoPagoses = new HashSet(0);
     private Set proveedoresProductoses = new HashSet(0);

    public Usuarios() {
    }

	
    public Usuarios(String nombre, String tipoPersona) {
        this.nombre = nombre;
        this.tipoPersona = tipoPersona;
    }
    public Usuarios(String nombre, String app, String apm, String telefono, String celular, String otroTelefono, String direccion, String calle, Integer numero, String colonia, Integer codigoPostal, String ciudad, String estado, String tipoPersona, String tipoCliente, Set horarioEmpleadoses, Set productoses, Set userses, Set pedidoses, Set seguimientoPagoses, Set proveedoresProductoses) {
       this.nombre = nombre;
       this.app = app;
       this.apm = apm;
       this.telefono = telefono;
       this.celular = celular;
       this.otroTelefono = otroTelefono;
       this.direccion = direccion;
       this.calle = calle;
       this.numero = numero;
       this.colonia = colonia;
       this.codigoPostal = codigoPostal;
       this.ciudad = ciudad;
       this.estado = estado;
       this.tipoPersona = tipoPersona;
       this.tipoCliente = tipoCliente;
       this.horarioEmpleadoses = horarioEmpleadoses;
       this.productoses = productoses;
       this.userses = userses;
       this.pedidoses = pedidoses;
       this.seguimientoPagoses = seguimientoPagoses;
       this.proveedoresProductoses = proveedoresProductoses;
    }
   
    public Long getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApp() {
        return this.app;
    }
    
    public void setApp(String app) {
        this.app = app;
    }
    public String getApm() {
        return this.apm;
    }
    
    public void setApm(String apm) {
        this.apm = apm;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getOtroTelefono() {
        return this.otroTelefono;
    }
    
    public void setOtroTelefono(String otroTelefono) {
        this.otroTelefono = otroTelefono;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCalle() {
        return this.calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getColonia() {
        return this.colonia;
    }
    
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    public Integer getCodigoPostal() {
        return this.codigoPostal;
    }
    
    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getTipoPersona() {
        return this.tipoPersona;
    }
    
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public String getTipoCliente() {
        return this.tipoCliente;
    }
    
    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
    public Set getHorarioEmpleadoses() {
        return this.horarioEmpleadoses;
    }
    
    public void setHorarioEmpleadoses(Set horarioEmpleadoses) {
        this.horarioEmpleadoses = horarioEmpleadoses;
    }
    public Set getProductoses() {
        return this.productoses;
    }
    
    public void setProductoses(Set productoses) {
        this.productoses = productoses;
    }
    public Set getUserses() {
        return this.userses;
    }
    
    public void setUserses(Set userses) {
        this.userses = userses;
    }
    public Set getPedidoses() {
        return this.pedidoses;
    }
    
    public void setPedidoses(Set pedidoses) {
        this.pedidoses = pedidoses;
    }
    public Set getSeguimientoPagoses() {
        return this.seguimientoPagoses;
    }
    
    public void setSeguimientoPagoses(Set seguimientoPagoses) {
        this.seguimientoPagoses = seguimientoPagoses;
    }
    public Set getProveedoresProductoses() {
        return this.proveedoresProductoses;
    }
    
    public void setProveedoresProductoses(Set proveedoresProductoses) {
        this.proveedoresProductoses = proveedoresProductoses;
    }




}

