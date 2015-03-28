/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.ejb.session.SessionBeanUsers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceUsers implements IfaceUsers {
    @Autowired
    private SessionBeanUsers ejbUsers;
    
    @Override
    public List<DominioUsers> getLista() throws Exception {
        return ejbUsers.getUsers();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbUsers.getUsersPagination((DominioUsers)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbUsers.getRowCountBUser();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbUsers.getById((DominioUsers)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbUsers.save((DominioUsers)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbUsers.update((DominioUsers)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbUsers.delete((DominioUsers)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbUsers.deleteAll(lista);
    }

    @Override
    public DominioUsers validateLogin(DominioUsers obj) {
        return ejbUsers.validateLogin(obj);
    }

    @Override
    public DominioUsers registraAsistencia(DominioUsers obj) {
        return ejbUsers.registraAsistencia(obj);
    }
}
