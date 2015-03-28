/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.ejb.business.IfaceProductos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsProductos")
public class SessionBeanProductos {
    @Autowired
    private IfaceProductos businessProductos;
    
    @WebMethod(operationName = "getProductos")
    public List<DominioProductos> getProductos() throws Exception {
        return businessProductos.getProductos();
    }
    
    @WebMethod(operationName = "getProductosPagination")
    public List<DominioProductos> getProductosPagination(DominioProductos obj) throws Exception {
        return businessProductos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getProductosPaginationByProveedor")
    public List<DominioProductos> getProductosPaginationByProveedor(DominioProductos obj) throws Exception {
        return businessProductos.getListaPaginationByProveedor(obj);
    }
    
    @WebMethod(operationName = "getRowCountBProductos")
    public int getRowCountBProductos() throws Exception {
        return businessProductos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioProductos save(DominioProductos obj) {
        return businessProductos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioProductos update(DominioProductos obj) {
        return businessProductos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioProductos delete(DominioProductos obj) {
        return businessProductos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioProductos deleteAll(List<DominioProductos> lista) {
        return businessProductos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioProductos getById(DominioProductos obj) {
        return businessProductos.getById(obj);
    }
    
    @WebMethod(operationName = "getListaByTipo")
    public List<DominioProductos> getListaByTipo(DominioProductos obj) throws Exception {
        return businessProductos.getListaByTipo(obj);
    }
    
    @WebMethod(operationName = "getRowsByTipo")
    public int getRowsByTipo(DominioProductos obj) throws Exception {
        return businessProductos.getRowsByTipo(obj);
    }
    
    @WebMethod(operationName = "getRowsByProveedor")
    public int getRowsByProveedor(DominioProductos obj) throws Exception {
        return businessProductos.getRowsByProveedor(obj);
    }
}
