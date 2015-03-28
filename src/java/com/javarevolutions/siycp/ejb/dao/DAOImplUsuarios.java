/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOImplUsuarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Usuarios;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplUsuarios extends DAOGeneric implements DAOUsuarios {
    @Override
    public List<Usuarios> getLista() {
        return getList("from Usuarios");
    }
    
    @Override
    @Transactional
    public List<Usuarios> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Usuarios");
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from Usuarios");
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
        return getObject("from Usuarios where idUsuario = ?", value);
    }

    @Override
    @Transactional
    public List<Usuarios> getListaByTipo(Usuarios user) {
        return getList("from Usuarios where tipoPersona like ? order by nombre asc", user.getTipoPersona());
    }

    @Override
    @Transactional
    public List<Usuarios> getListaPaginationByTipo(int first, int pageSize, Usuarios user) {
        return getListByParam(first, pageSize, 
                "from Usuarios where tipoPersona like ? order by nombre asc", user.getTipoPersona());
    }

    @Override
    @Transactional
    public int getRowsByTipo(Usuarios user) throws Exception {
        return getCountLista("select count(*) from Usuarios where tipoPersona like '"+user.getTipoPersona()+"'");
    }
}
