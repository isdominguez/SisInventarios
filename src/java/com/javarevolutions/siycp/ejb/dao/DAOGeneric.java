/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOGeneric.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DAOGeneric extends HibernateDaoSupport {

    public void insert(Object entity) throws Exception {
        getHibernateTemplate().save(entity);
    }
    
    public Object update(Object entity) throws Exception {
        getHibernateTemplate().update(entity);
        return entity;
    }

    public void delete(Object entity) throws Exception {
        getHibernateTemplate().delete(entity);
    }

    public Object getObject(String nameQuery, Object value1) {
        List list = getHibernateTemplate().find(nameQuery, value1);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Object getObject(String nameQuery, Object value1, Object value2) {
        List list = getHibernateTemplate().find(nameQuery, value1, value2);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public Object getObject(String nameQuery, Object[] values) {
        List list = getHibernateTemplate().find(nameQuery, values);
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    public int getCountLista(String query) {
        List lista = getHibernateTemplate().find(query);
        if(lista != null) {
            return Integer.valueOf(""+lista.get(0));
        }
        return 0;
    }
    
    public int getCountWithParams(String sQuery, Object... values) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(sQuery);
        int cont = 0;
        for(Object obj: values) {
            query.setParameter(cont++, obj);
        }
        List lista = query.list();
        if(lista != null) {
            return Integer.valueOf(""+lista.get(0));
        }
        return 0;
    }
    
    public List getListByParam(int first, int pageSize, String cadena, Object param) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(cadena);
        query.setParameter(0, param);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.list();
    }
    
    public List getList(int first, int pageSize, String cadena) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(cadena);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.list();
    }
    
    public List getListWithParams(int first, int pageSize, String cadena, Object... values) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        Query query = session.createQuery(cadena);
        int cont = 0;
        for(Object obj: values) {
            query.setParameter(cont++, obj);
        }
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.list();
    }

    public List getList(String nameQuery) {
        return getHibernateTemplate().find(nameQuery);
    }

    public List getList(String nameQuery, Object value1) {
        return getHibernateTemplate().find(nameQuery, value1);
    }

    public List getList(String nameQuery, Object value1, Object value2) {
        return getHibernateTemplate().find(nameQuery, value1, value2);
    }

    public List getList(String nameQuery, Object[] values) {
        return getHibernateTemplate().find(nameQuery, values);
    }
}
