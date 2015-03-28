/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplInventarios.java
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
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplInventarios extends DAOGeneric implements DAOInventarios {
    @Override
    public List<Inventarios> getLista() {
        return getList("from Inventarios order by productos.nombre, productos.descripcion asc");
    }
    
    @Override
    @Transactional
    public List<Inventarios> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Inventarios order by productos.nombre, productos.descripcion asc");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Inventarios");
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
        return getObject("from Inventarios where idInventario = ?", value);
    }

    @Override
    @Transactional
    public void createAllProductos() throws Exception {
        List<Productos> lista = getList("from Productos where tipo like ?", "p");
        for(Productos entity: lista) {
            Inventarios obj = new Inventarios();
            obj.setProductos(entity);
            obj.setCantidad(0);
            insert(obj);
        }
    }
    
    @Override
    @Transactional
    public void updateCantidades(List<Inventarios> lista) throws Exception {
        for(Inventarios entity: lista) {
            update(entity);
        }
    }

    @Override
    public int getStatusInventario(long limite) {
        return getCountLista("select count(*) from Inventarios where cantidad <= minimoExistencia");
    }
}
