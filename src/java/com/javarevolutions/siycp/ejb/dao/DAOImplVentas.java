/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOImplVentas.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplVentas extends DAOGeneric implements DAOVentas {
    @Override
    public List<Ventas> getLista() {
        return getList("from Ventas");
    }
    
    @Override
    @Transactional
    public List<Ventas> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Ventas order by idVenta desc");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Ventas");
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
        return getObject("from Ventas where idVenta = ?", value);
    }

    @Override
    @Transactional
    public Usuarios getUserById(Long idUsuario) throws Exception {
        return (Usuarios)getObject("from Usuarios where idUsuario = ?", idUsuario);
    }
    
    @Override
    @Transactional
    public List<Ventas> getVentasByDia(Date dia, int first, int pageSize) throws Exception {
        return getList("from Ventas where fechaVenta = ? order by idVenta desc", dia);
    }

    @Override
    @Transactional
    public List<Ventas> getVentasByFechas(Date diaInicio, Date diaFin, int first, int pageSize) throws Exception {
        return getList("from Ventas where fechaVenta >= ? and fechaVenta <= ? order by idVenta desc",
                diaInicio, diaFin);
    }

    @Override
    @Transactional
    public int getRowCountByDia(Date dia) throws Exception {
        return getCountLista("select count(*) from Ventas where fechaVenta = '"+convertFecha(dia)+"'");
    }

    @Override
    @Transactional
    public int getRowCountByFechas(Date diaInicio, Date diaFin) throws Exception {
        return getCountWithParams("select count(*) from Ventas where fechaVenta >= ? and fechaVenta <= ?",
                new Object[]{diaInicio, diaFin});
    }
    
    private java.sql.Date convertFecha(Date fecha) {
        return new java.sql.Date(fecha.getTime());
    }
}
