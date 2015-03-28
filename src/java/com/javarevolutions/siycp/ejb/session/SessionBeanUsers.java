/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.ejb.business.IfaceUsers;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsUsers")
public class SessionBeanUsers {
    @Autowired
    private IfaceUsers businessUsers;
    
    @WebMethod(operationName = "getUsers")
    public List<DominioUsers> getUsers() throws Exception {
        return businessUsers.getUsers();
    }
    
    @WebMethod(operationName = "getUsersPagination")
    public List<DominioUsers> getUsersPagination(DominioUsers obj) throws Exception {
        return businessUsers.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBUser")
    public int getRowCountBUser() throws Exception {
        return businessUsers.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioUsers save(DominioUsers obj) {
        return businessUsers.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioUsers update(DominioUsers obj) {
        return businessUsers.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioUsers delete(DominioUsers obj) {
        return businessUsers.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioUsers deleteAll(List<DominioUsers> lista) {
        return businessUsers.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioUsers getById(DominioUsers obj) {
        return businessUsers.getById(obj);
    }
    
    @WebMethod(operationName = "validateLogin")
    public DominioUsers validateLogin(DominioUsers obj) {
        return businessUsers.validateLogin(obj);
    }
    
    @WebMethod(operationName = "registraAsistencia")
    public DominioUsers registraAsistencia(DominioUsers obj) {
        return businessUsers.registraAsistencia(obj);
    }
}
