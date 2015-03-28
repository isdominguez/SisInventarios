/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanUsuarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.ejb.business.IfaceUsuarios;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsUsuarios")
public class SessionBeanUsuarios {
    @Autowired
    private IfaceUsuarios businessUsuarios;
    
    @WebMethod(operationName = "getUsuarios")
    public List<DominioUsuarios> getUsuarios() throws Exception {
        return businessUsuarios.getUsuarios();
    }
    
    @WebMethod(operationName = "getUsuariosPagination")
    public List<DominioUsuarios> getUsuariosPagination(DominioUsuarios obj) throws Exception {
        return businessUsuarios.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBUsuarios")
    public int getRowCountBUsuarios() throws Exception {
        return businessUsuarios.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioUsuarios save(DominioUsuarios obj) {
        return businessUsuarios.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioUsuarios update(DominioUsuarios obj) {
        return businessUsuarios.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioUsuarios delete(DominioUsuarios obj) {
        return businessUsuarios.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioUsuarios deleteAll(List<DominioUsuarios> lista) {
        return businessUsuarios.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioUsuarios getById(DominioUsuarios obj) {
        return businessUsuarios.getById(obj);
    }
    
    @WebMethod(operationName = "getListaByTipo")
    public List<DominioUsuarios> getListaByTipo(DominioUsuarios obj) throws Exception {
        return businessUsuarios.getListaByTipo(obj);
    }
    
    @WebMethod(operationName = "getRowsByTipo")
    public int getRowsByTipo(DominioUsuarios obj) throws Exception {
        return businessUsuarios.getRowsByTipo(obj);
    }
}
