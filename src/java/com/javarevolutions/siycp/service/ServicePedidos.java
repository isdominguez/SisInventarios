/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServicePedidos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioPedidos;
import com.javarevolutions.siycp.ejb.session.SessionBeanPedidos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicePedidos implements IfacePedidos {
    @Autowired
    private SessionBeanPedidos ejbPedidos;
    
    @Override
    public List<DominioPedidos> getLista() throws Exception {
        return ejbPedidos.getPedidos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbPedidos.getPedidosPagination((DominioPedidos)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbPedidos.getRowCountBPedidos();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbPedidos.getById((DominioPedidos)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbPedidos.save((DominioPedidos)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbPedidos.update((DominioPedidos)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbPedidos.delete((DominioPedidos)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbPedidos.deleteAll(lista);
    }

    @Override
    public List<DominioPedidos> getPedidosByDia(DominioPedidos obj) throws Exception {
        return ejbPedidos.getPedidosByDia(obj);
    }

    @Override
    public List<DominioPedidos> getPedidosByFechas(DominioPedidos obj) throws Exception {
        return ejbPedidos.getPedidosByFechas(obj);
    }

    @Override
    public int getRowCountByDia(DominioPedidos obj) throws Exception {
        return ejbPedidos.getRowCountByDia(obj);
    }

    @Override
    public int getRowCountByFechas(DominioPedidos obj) throws Exception {
        return ejbPedidos.getRowCountByFechas(obj);
    }
}
