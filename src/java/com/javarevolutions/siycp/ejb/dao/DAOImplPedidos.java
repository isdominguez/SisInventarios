/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplPedidos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Pedidos;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplPedidos extends DAOGeneric implements DAOPedidos {
    @Override
    public List<Pedidos> getLista() {
        return getList("from Pedidos order by idPedido desc");
    }
    
    @Override
    @Transactional
    public List<Pedidos> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Pedidos order by idPedido desc");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Pedidos");
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
        return getObject("from Pedidos where idPedido = ?", value);
    }

    @Override
    @Transactional
    public List<Pedidos> getPedidosByDia(Date dia, int first, int pageSize) throws Exception {
        return getList("from Pedidos where fechaPedido = ? order by idPedido desc", dia);
    }

    @Override
    @Transactional
    public List<Pedidos> getPedidosByFechas(Date diaInicio, Date diaFin, int first, int pageSize) throws Exception {
        return getList("from Pedidos where fechaPedido >= ? and fechaPedido <= ? order by idPedido desc",
                diaInicio, diaFin);
    }

    @Override
    @Transactional
    public int getRowCountByDia(Date dia) throws Exception {
        return getCountLista("select count(*) from Pedidos where fechaPedido = '"+convertFecha(dia)+"'");
    }

    @Override
    @Transactional
    public int getRowCountByFechas(Date diaInicio, Date diaFin) throws Exception {
        return getCountWithParams("select count(*) from Pedidos where fechaPedido >= ? and fechaPedido <= ?",
                new Object[]{diaInicio, diaFin});
    } 
    
    private java.sql.Date convertFecha(Date fecha) {
        return new java.sql.Date(fecha.getTime());
    }
}
