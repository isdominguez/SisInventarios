/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServicePedidosProductos.java
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
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import com.javarevolutions.siycp.ejb.session.SessionBeanPedidosProductos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicePedidosProductos implements IfacePedidosProductos {
    @Autowired
    private SessionBeanPedidosProductos ejbPedidosProductos;
    
    @Override
    public List<DominioPedidosProductos> getLista() throws Exception {
        return ejbPedidosProductos.getPedidosProductos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbPedidosProductos.getPedidosProductosPagination((DominioPedidosProductos)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbPedidosProductos.getRowCountBPedidosProductos();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbPedidosProductos.getById((DominioPedidosProductos)obj);
    }

    @Override
    public DominioPedidosProductos save(DominioPedidosProductos obj) {
        return ejbPedidosProductos.save(obj);
    }

    @Override
    public DominioPedidosProductos update(DominioPedidosProductos obj) {
        return ejbPedidosProductos.update(obj);
    }

    @Override
    public DominioPedidosProductos delete(DominioPedidosProductos obj) {
        return ejbPedidosProductos.delete(obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbPedidosProductos.deleteAll(lista);
    }

    @Override
    public DominioPedidosProductos saveAllProdsPedidos(List<DominioPedidosProductos> lista, DominioPedidos pedido) {
        return ejbPedidosProductos.saveAllProdsPedidos(lista, pedido);
    }

    
    @Override
    public DominioPedidosProductos updateAllProdsPedidos(List<DominioPedidosProductos> listaAdd,
            List<DominioPedidosProductos> listaDel, DominioPedidos pedido) {
        return ejbPedidosProductos.updateAllProdsPedidos(listaAdd, listaDel, pedido);
    }

    @Override
    public Object save(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DominioPedidosProductos> getListaBorrar(DominioPedidosProductos obj) throws Exception {
        return ejbPedidosProductos.getListaBorrar(obj);
    }
}
