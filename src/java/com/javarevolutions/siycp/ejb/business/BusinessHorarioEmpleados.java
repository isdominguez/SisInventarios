/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BusinessHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioHorarioEmpleados;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOHorarioEmpleados;
import com.javarevolutions.siycp.ejb.entity.HorarioEmpleados;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessHorarioEmpleados extends BusinessGeneric implements IfaceHorarioEmpleados {
    @Autowired
    private DAOHorarioEmpleados daoHorarioEmpleados;

    @Override
    public List<DominioHorarioEmpleados> getHorarioEmpleados() throws Exception {
        List<DominioHorarioEmpleados> listaDominio = new ArrayList();
        List<HorarioEmpleados> listaEntity = daoHorarioEmpleados.getLista();
        int cont = 1;
        for(HorarioEmpleados obj: listaEntity) {
            DominioHorarioEmpleados dominio = new DominioHorarioEmpleados();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioHorarioEmpleados> getListaPagination(DominioHorarioEmpleados obj) throws Exception {
        List<DominioHorarioEmpleados> listaDominio = new ArrayList();
        List<HorarioEmpleados> listaEntity = daoHorarioEmpleados.getListaPaginationByUser(obj.getFirst(), obj.getPageSize(), 
                obj.getUsuarios().getIdUsuario());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(HorarioEmpleados entity: listaEntity) {
            DominioHorarioEmpleados dominio = new DominioHorarioEmpleados();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCountByUser(long idUsuario) throws Exception {
        return daoHorarioEmpleados.getRowCountByUser(idUsuario);
    }
    
    @Override
    public DominioHorarioEmpleados save(DominioHorarioEmpleados obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioHorarioEmpleados update(DominioHorarioEmpleados obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioHorarioEmpleados delete(DominioHorarioEmpleados obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioHorarioEmpleados deleteAll(List<DominioHorarioEmpleados> lista) {
        DominioHorarioEmpleados obj = new DominioHorarioEmpleados();
        List<HorarioEmpleados> entities = new ArrayList();
        try {
            for(DominioHorarioEmpleados dominio: lista)  {
                HorarioEmpleados entity = new HorarioEmpleados();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoHorarioEmpleados.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioHorarioEmpleados getById(DominioHorarioEmpleados obj) {
        HorarioEmpleados entity;
        try {
            entity = (HorarioEmpleados)daoHorarioEmpleados.getById(obj, obj.getIdHorario());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioHorarioEmpleados getTransaction(DominioHorarioEmpleados obj, String accion) {
        HorarioEmpleados entity = new HorarioEmpleados();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoHorarioEmpleados, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
}
