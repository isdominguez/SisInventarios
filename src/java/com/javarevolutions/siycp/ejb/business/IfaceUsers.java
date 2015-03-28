/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioUsers;
import java.util.List;

public interface IfaceUsers {
    List<DominioUsers> getUsers() throws Exception;
    List<DominioUsers> getListaPagination(DominioUsers obj) throws Exception;
    int getRowCount() throws Exception;
    DominioUsers save(DominioUsers obj);
    DominioUsers update(DominioUsers obj);
    DominioUsers delete(DominioUsers obj);
    DominioUsers deleteAll(List<DominioUsers> lista);
    DominioUsers getById(DominioUsers obj);
    DominioUsers validateLogin(DominioUsers obj);
    DominioUsers registraAsistencia(DominioUsers obj);
}
