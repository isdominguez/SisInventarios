/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessGeneric.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOIfaceGeneric;

public class BusinessGeneric {
    void executeTransaction(Object entity, Generic generic, DAOIfaceGeneric dao, String accion) {
        try {
            if(accion.equals("save")) {
                dao.guardar(entity);
            }
            if(accion.equals("update")) {
                dao.actualizar(entity);
            }
            if(accion.equals("delete")) {
                dao.borrar(entity);
            }
            generic.setStatus(true);
        } catch(Exception e) {
            generic.setMsg(""+e);
            generic.setStatus(false);
        }
    }
}
