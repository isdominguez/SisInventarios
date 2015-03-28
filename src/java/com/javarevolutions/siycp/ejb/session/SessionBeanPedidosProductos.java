/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanPedidosProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioPedidos;
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import com.javarevolutions.siycp.ejb.business.IfacePedidosProductos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsPedidosProductos")
public class SessionBeanPedidosProductos {
    @Autowired
    private IfacePedidosProductos businessPedidosProductos;
    
    @WebMethod(operationName = "getPedidosProductos")
    public List<DominioPedidosProductos> getPedidosProductos() throws Exception {
        return businessPedidosProductos.getPedidosProductos();
    }
    
    @WebMethod(operationName = "getPedidosProductosPagination")
    public List<DominioPedidosProductos> getPedidosProductosPagination(DominioPedidosProductos obj) throws Exception {
        return businessPedidosProductos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBPedidosProductos")
    public int getRowCountBPedidosProductos() throws Exception {
        return businessPedidosProductos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioPedidosProductos save(DominioPedidosProductos obj) {
        return businessPedidosProductos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioPedidosProductos update(DominioPedidosProductos obj) {
        return businessPedidosProductos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioPedidosProductos delete(DominioPedidosProductos obj) {
        return businessPedidosProductos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioPedidosProductos deleteAll(List<DominioPedidosProductos> lista) {
        return businessPedidosProductos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioPedidosProductos getById(DominioPedidosProductos obj) {
        return businessPedidosProductos.getById(obj);
    }
    
    @WebMethod(operationName = "saveAllProdsPedidos")
    public DominioPedidosProductos saveAllProdsPedidos(List<DominioPedidosProductos> lista, DominioPedidos pedido) {
        return businessPedidosProductos.saveAllProdsPedidos(lista, pedido);
    }

        
    @WebMethod(operationName = "updateAllProdsPedidos")
    public DominioPedidosProductos updateAllProdsPedidos(List<DominioPedidosProductos> listaAdd, 
            List<DominioPedidosProductos> listaDel, DominioPedidos pedido) {
        return businessPedidosProductos.updateAllProdsPedidos(listaAdd, listaDel, pedido);
    }

    @WebMethod(operationName = "getListaBorrar")
    public List<DominioPedidosProductos> getListaBorrar(DominioPedidosProductos obj) throws Exception {
        return businessPedidosProductos.getListaBorrar(obj);
    }
}
