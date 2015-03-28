/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOImplVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Inventarios;
import com.javarevolutions.siycp.ejb.entity.Productos;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import com.javarevolutions.siycp.ejb.entity.VentasProductos;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplVentasProductos extends DAOGeneric implements DAOVentasProductos {
    @Override
    public List<VentasProductos> getLista() {
        return getList("from VentasProductos");
    }
    
    @Override
    @Transactional
    public List<VentasProductos> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from VentasProductos");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from VentasProductos");
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
        return getObject("from VentasProductos where idVentaProducto = ?", value);
    }

    @Override
    @Transactional
    public void saveAllProdsPedidos(List<VentasProductos> lista, Ventas venta) throws Exception {
        insert(venta);
        for(VentasProductos obj: lista) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()-obj.getCantidad());
            update(inventario);
            obj.setVentas(venta);
            insert(obj);
        }
    }
    
    @Override
    @Transactional
    public void updateAllProdsPedidos(List<VentasProductos> listaAdd, List<VentasProductos> listaDel, 
            Ventas venta) throws Exception {
        actualizar(venta);
        for(VentasProductos obj: listaDel) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()+obj.getCantidad());
            update(inventario);
            borrar(obj);
        }
        for(VentasProductos obj: listaAdd) {
            Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                    obj.getProductos().getIdProducto());
            inventario.setCantidad(inventario.getCantidad()-obj.getCantidad());
            update(inventario);
            guardar(obj);
        }
    }

    @Override
    public List<VentasProductos> getListaBorrar(VentasProductos entity) throws Exception {
        return getList("from VentasProductos where ventas.idVenta = ?", entity.getVentas().getIdVenta());
    }

    @Override
    @Transactional
    public Usuarios getUserById(Object value) throws Exception {
        Object obj = getObject("from Usuarios where idUsuario = ?", value);
        if(obj != null) {
            return (Usuarios)obj;
        }
        return null;
    }

    @Override
    @Transactional
    public long getExistenciaInventarios(Productos obj) throws Exception {
        Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", obj.getIdProducto());
        return inventario.getCantidad();
    }
}
