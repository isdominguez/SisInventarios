/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DominioHorarioEmpleados.java
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

public class DominioHorarioEmpleados extends Generic {
    private static final long serialVersionUID = -101304522068881520L;
    private Long idHorario;
    private Date horaRegistro;
    private DominioUsuarios usuarios;

    public DominioHorarioEmpleados() {
        usuarios = new DominioUsuarios();
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }
    public Long getIdHorario() {
        return idHorario;
    }
    public void setHoraRegistro(Date horaRegistro) {
        this.horaRegistro = horaRegistro;
    }
    public Date getHoraRegistro() {
        return horaRegistro;
    }
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }
}
