/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanInventarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import com.javarevolutions.siycp.ejb.business.IfaceInventarios;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsInventarios")
public class SessionBeanInventarios {
    @Autowired
    private IfaceInventarios businessInventarios;
    
    @WebMethod(operationName = "getInventarios")
    public List<DominioInventarios> getInventarios() throws Exception {
        return businessInventarios.getInventarios();
    }
    
    @WebMethod(operationName = "getInventariosPagination")
    public List<DominioInventarios> getInventariosPagination(DominioInventarios obj) throws Exception {
        return businessInventarios.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBInventarios")
    public int getRowCountBInventarios() throws Exception {
        return businessInventarios.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioInventarios save(DominioInventarios obj) {
        return businessInventarios.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioInventarios update(DominioInventarios obj) {
        return businessInventarios.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioInventarios delete(DominioInventarios obj) {
        return businessInventarios.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioInventarios deleteAll(List<DominioInventarios> lista) {
        return businessInventarios.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioInventarios getById(DominioInventarios obj) {
        return businessInventarios.getById(obj);
    }
    
    @WebMethod(operationName = "createAllProductos")
    public DominioInventarios createAllProductos() {
        return businessInventarios.createAllProductos();
    }
    
    @WebMethod(operationName = "updateCantidades")
    public DominioInventarios updateCantidades(List<DominioInventarios> lista) {
        return businessInventarios.updateCantidades(lista);
    }
    
    @WebMethod(operationName = "getStatusInventario")
    public DominioInventarios getStatusInventario() {
        return businessInventarios.getStatusInventario();
    }
}
