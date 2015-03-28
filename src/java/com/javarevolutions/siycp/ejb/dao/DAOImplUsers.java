/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.HorarioEmpleados;
import com.javarevolutions.siycp.ejb.entity.Users;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplUsers extends DAOGeneric implements DAOUsers {
    @Override
    public List<Users> getLista() {
        return getList("from Users");
    }
    
    @Override
    @Transactional
    public List<Users> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Users");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Users");
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
        return getObject("from Users where user = ?", value);
    }

    @Override
    public Object validateLogin(Users entity) throws Exception {
        return getObject("from Users where user = ? and password = ? and statusUser = 'a'", entity.getUser(), entity.getPassword());
    }

    @Override
    @Transactional
    public void registraAsistencia(HorarioEmpleados entity) throws Exception {
        insert(entity);
    }

    @Override
    public Object validateAsistenciaLogin(Users entity) throws Exception {
        return getObject("from Users where user = ? and password = ?", entity.getUser(), entity.getPassword());
    }
}
