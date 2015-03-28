/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanPedidos.java
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
import com.javarevolutions.siycp.ejb.business.IfacePedidos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsPedidos")
public class SessionBeanPedidos {
    @Autowired
    private IfacePedidos businessPedidos;
    
    @WebMethod(operationName = "getPedidos")
    public List<DominioPedidos> getPedidos() throws Exception {
        return businessPedidos.getPedidos();
    }
    
    @WebMethod(operationName = "getPedidosPagination")
    public List<DominioPedidos> getPedidosPagination(DominioPedidos obj) throws Exception {
        return businessPedidos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBPedidos")
    public int getRowCountBPedidos() throws Exception {
        return businessPedidos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioPedidos save(DominioPedidos obj) {
        return businessPedidos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioPedidos update(DominioPedidos obj) {
        return businessPedidos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioPedidos delete(DominioPedidos obj) {
        return businessPedidos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioPedidos deleteAll(List<DominioPedidos> lista) {
        return businessPedidos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioPedidos getById(DominioPedidos obj) {
        return businessPedidos.getById(obj);
    }
    
    @WebMethod(operationName = "getPedidosByDia")
    public List<DominioPedidos> getPedidosByDia(DominioPedidos obj) throws Exception {
        return businessPedidos.getPedidosByDia(obj);
    }
    
    @WebMethod(operationName = "getPedidosByFechas")
    public List<DominioPedidos> getPedidosByFechas(DominioPedidos obj) throws Exception {
        return businessPedidos.getPedidosByFechas(obj);
    }
    
    @WebMethod(operationName = "getRowCountByDia")
    public int getRowCountByDia(DominioPedidos obj) throws Exception {
        return businessPedidos.getRowCountByDia(obj);
    }
    
    @WebMethod(operationName = "getRowCountByFechas")
    public int getRowCountByFechas(DominioPedidos obj) throws Exception {
        return businessPedidos.getRowCountByFechas(obj);
    }
}
