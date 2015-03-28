/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplPedidosProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Inventarios;
import com.javarevolutions.siycp.ejb.entity.Pedidos;
import com.javarevolutions.siycp.ejb.entity.PedidosProductos;
import com.javarevolutions.siycp.ejb.entity.SeguimientoPagos;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplPedidosProductos extends DAOGeneric implements DAOPedidosProductos {
    @Override
    public List<PedidosProductos> getLista() {
        return getList("from PedidosProductos");
    }
    
    @Override
    @Transactional
    public List<PedidosProductos> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from PedidosProductos");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from PedidosProductos");
    }
    
    @Override
    @Transactional
    public void guardar(Object entity) throws Exception {
        insert(entity);
    }

    @Override
    @Transactional
    public Object actualizar(Object entity) throws Exception {
        return update(entity);
    }

    @Override
    @Transactional
    public void borrar(Object entity) throws Exception {
        delete(entity);
    }
    
    @Override
    @Transactional
    public void borrarAll(List lista) throws Exception {
        try {
            for(Object obj: lista) {
                delete(obj);
            }
        } catch(HibernateException e) {
            System.out.println("ERROR IN DAO deleteAll : "+e);
        }
    }
    
    @Override
    @Transactional
    public Object getById(Object entity, Object value) throws Exception {
        return getObject("from PedidosProductos where idPedidoProducto = ?", value);
    }

    @Override
    @Transactional
    public void saveAllProdsPedidos(List<PedidosProductos> lista, Pedidos pedido) throws Exception {
        insert(pedido);
        double totalPedido = 0;
        for(PedidosProductos obj: lista) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()+obj.getCantidad());
            update(inventario);
            obj.setPedidos(pedido);
            insert(obj);
            totalPedido += (obj.getCantidad() * obj.getProductos().getCosto());
        }
        SeguimientoPagos sp = null;
        Object obj = getObject("from SeguimientoPagos where usuarios.idUsuario = ?", pedido.getUsuarios().getIdUsuario());
        if(obj == null) {
            sp = new SeguimientoPagos();
            sp.setTotalDeuda(totalPedido);
            sp.setUsuarios(pedido.getUsuarios());
            insert(sp);
        } else {
            sp = (SeguimientoPagos)obj;
            totalPedido += sp.getTotalDeuda();
            sp.setTotalDeuda(totalPedido);
            update(sp);
        }
    }
    
    @Override
    @Transactional
    public void updateAllProdsPedidos(List<PedidosProductos> listaAdd, List<PedidosProductos> listaDel, 
            Pedidos pedido) throws Exception {
        actualizar(pedido);
        double totalPedido = 0;
        for(PedidosProductos obj: listaDel) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()-obj.getCantidad());
            update(inventario);
            borrar(obj);
            totalPedido += (obj.getCantidad() * obj.getProductos().getCosto());
        }
        SeguimientoPagos sp = null;
        Object auxObj = getObject("from SeguimientoPagos where usuarios.idUsuario = ?", pedido.getUsuarios().getIdUsuario());
        sp = (SeguimientoPagos)auxObj;
        sp.setTotalDeuda(sp.getTotalDeuda()-totalPedido);
        update(sp);
        totalPedido = 0;
        for(PedidosProductos obj: listaAdd) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()+obj.getCantidad());
            update(inventario);
            guardar(obj);
            totalPedido += (obj.getCantidad() * obj.getProductos().getCosto());
        }
        totalPedido += sp.getTotalDeuda();
        sp.setTotalDeuda(totalPedido);
        update(sp);
    }

    @Override
    public List<PedidosProductos> getListaBorrar(PedidosProductos entity) throws Exception {
        return getList("from PedidosProductos where pedidos.idPedido = ?", entity.getPedidos().getIdPedido());
    }
}
