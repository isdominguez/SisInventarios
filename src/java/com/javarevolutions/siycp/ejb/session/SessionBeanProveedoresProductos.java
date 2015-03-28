/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanProveedoresProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioProveedoresProductos;
import com.javarevolutions.siycp.ejb.business.IfaceProveedoresProductos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsProveedoresProductos")
public class SessionBeanProveedoresProductos {
    @Autowired
    private IfaceProveedoresProductos businessProveedoresProductos;
    
    @WebMethod(operationName = "getProveedoresProductos")
    public List<DominioProveedoresProductos> getProveedoresProductos() throws Exception {
        return businessProveedoresProductos.getProveedoresProductos();
    }
    
    @WebMethod(operationName = "getProveedoresProductosPagination")
    public List<DominioProveedoresProductos> getProveedoresProductosPagination(DominioProveedoresProductos obj) throws Exception {
        return businessProveedoresProductos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBProveedoresProductos")
    public int getRowCountBProveedoresProductos() throws Exception {
        return businessProveedoresProductos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioProveedoresProductos save(DominioProveedoresProductos obj) {
        return businessProveedoresProductos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioProveedoresProductos update(DominioProveedoresProductos obj) {
        return businessProveedoresProductos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioProveedoresProductos delete(DominioProveedoresProductos obj) {
        return businessProveedoresProductos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioProveedoresProductos deleteAll(List<DominioProveedoresProductos> lista) {
        return businessProveedoresProductos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioProveedoresProductos getById(DominioProveedoresProductos obj) {
        return businessProveedoresProductos.getById(obj);
    }
}
