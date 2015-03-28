/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplProductos.java
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
import com.javarevolutions.siycp.ejb.entity.Productos;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplProductos extends DAOGeneric implements DAOProductos {
    @Override
    public List<Productos> getLista() {
        return getList("from Productos order by nombre, descripcion asc");
    }
    
    @Override
    @Transactional
    public List<Productos> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Productos order by nombre, descripcion asc");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Productos");
    }
    
    @Override
    @Transactional
    public void guardar(Object entity) throws Exception {
        Productos producto = (Productos)entity;
        Inventarios inventario = new Inventarios();
        insert(producto);
        inventario.setProductos(producto);
        inventario.setCantidad(0);
        inventario.setMinimoExistencia(10);
        insert(inventario);
    }

    @Override
    @Transactional
    public Object actualizar(Object entity) throws Exception {
        return update(entity);
    }

    @Override
    @Transactional
    public void borrar(Object entity) throws Exception {
        Productos producto = (Productos)entity;
        Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", 
                producto.getIdProducto());
        delete(inventario);
        Productos nuevo = (Productos)getById(producto, producto.getIdProducto());
        delete(nuevo);
    }
    
    @Override
    @Transactional
    public void borrarAll(List lista) throws Exception {
        for(Object obj: lista) {
            borrar(obj);
        }
    }
    
    @Override
    @Transactional
    public Object getById(Object entity, Object value) throws Exception {
        return getObject("from Productos where idProducto = ?", value);
    }

    @Override
    @Transactional
    public List<Productos> getListaByTipo(Productos obj) {
        return getList("from Productos where tipo like ? order by nombre, descripcion asc", obj.getTipo());
    }

    @Override
    @Transactional
    public List<Productos> getListaPaginationByTipo(int first, int pageSize, Productos obj) {
        return getListByParam(first, pageSize, 
                "from Productos where tipo like ? order by nombre, descripcion asc", obj.getTipo());
    }

    @Override
    @Transactional
    public int getRowsByTipo(Productos obj) throws Exception {
        return getCountLista("select count(*) from Productos where tipo like '"+obj.getTipo()+"'");
    }

    @Override
    @Transactional
    public List<Productos> getListaPaginationByProveedor(int first, int pageSize, Productos obj) {
        return getListWithParams(first, pageSize, 
                "from Productos where tipo like ? and usuarios.idUsuario = ? order by nombre, descripcion asc", 
                obj.getTipo(), obj.getUsuarios().getIdUsuario());
    }

    @Override
    public int getRowsByProveedor(Productos obj) throws Exception {
        return getCountLista("select count(*) from Productos where tipo like '"+obj.getTipo()+"' and usuarios.idUsuario = "+
                obj.getUsuarios().getIdUsuario());
    }

    @Override
    @Transactional
    public long getExistenciaInventarios(Productos obj) throws Exception {
        Inventarios inventario = (Inventarios)getObject("from Inventarios where productos.idProducto = ?", obj.getIdProducto());
        return inventario.getCantidad();
    }

    @Override
    @Transactional
    public Object getUserById(Object entity, Object value) throws Exception {
        return getObject("from Usuarios where idUsuario = ?", value);
    }
}
