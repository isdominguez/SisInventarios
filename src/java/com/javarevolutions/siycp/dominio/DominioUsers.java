/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioUsers.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/
package com.javarevolutions.siycp.dominio;

import java.util.Date;

public class DominioUsers extends Generic {
    private static final long serialVersionUID = -2071188569479336704L;
    private String user;
    private String password;
    private Date fechaAcceso;
    private String statusUser;
    private String lblActivarDesactivar;
    private DominioUsuarios usuarios;
    private Boolean validaAsistencia;

    public DominioUsers() {
        usuarios = new DominioUsuarios();
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getUser() {
        return user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setFechaAcceso(Date fechaAcceso) {
        this.fechaAcceso = fechaAcceso;
    }
    public Date getFechaAcceso() {
        return fechaAcceso;
    }

    /**
     * @return the statusUser
     */
    public String getStatusUser() {
        return statusUser;
    }

    /**
     * @param statusUser the statusUser to set
     */
    public void setStatusUser(String statusUser) {
        this.statusUser = statusUser;
    }

    /**
     * @return the lblActivarDesactivar
     */
    public String getLblActivarDesactivar() {
        return lblActivarDesactivar;
    }

    /**
     * @param lblActivarDesactivar the lblActivarDesactivar to set
     */
    public void setLblActivarDesactivar(String lblActivarDesactivar) {
        this.lblActivarDesactivar = lblActivarDesactivar;
    }
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @return the validaAsistencia
     */
    public Boolean getValidaAsistencia() {
        return validaAsistencia;
    }

    /**
     * @param validaAsistencia the validaAsistencia to set
     */
    public void setValidaAsistencia(Boolean validaAsistencia) {
        this.validaAsistencia = validaAsistencia;
    }
}
