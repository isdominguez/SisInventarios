/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOImplHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.HorarioEmpleados;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplHorarioEmpleados extends DAOGeneric implements DAOHorarioEmpleados {
    @Override
    public List<HorarioEmpleados> getLista() {
        return getList("from HorarioEmpleados");
    }
    
    @Override
    @Transactional
    public List<HorarioEmpleados> getListaPaginationByUser(int first, int pageSize, long idUsuario) {
        return getListByParam(first, pageSize, "from HorarioEmpleados where usuarios.idUsuario = ? order by horaRegistro desc", idUsuario);
    }
    
    @Override
    @Transactional
    public List<HorarioEmpleados> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from HorarioEmpleados");
    }
    
    @Override
    @Transactional
    public int getRowCountByUser(long idUsuario) {
        return getCountWithParams("select count(*) from HorarioEmpleados where usuarios.idUsuario = ?", idUsuario);
    }

    @Override
    public int getRowCount() {
        return getCountLista("select count(*) from HorarioEmpleados");
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
        return getObject("from HorarioEmpleados where idHorario = ?", value);
    }
}
