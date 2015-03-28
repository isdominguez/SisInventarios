/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BusinessSeguimientoPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioSeguimientoPagos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOSeguimientoPagos;
import com.javarevolutions.siycp.ejb.entity.SeguimientoPagos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessSeguimientoPagos extends BusinessGeneric implements IfaceSeguimientoPagos {
    @Autowired
    private DAOSeguimientoPagos daoSeguimientoPagos;

    @Override
    public List<DominioSeguimientoPagos> getSeguimientoPagos() throws Exception {
        List<DominioSeguimientoPagos> listaDominio = new ArrayList();
        List<SeguimientoPagos> listaEntity = daoSeguimientoPagos.getLista();
        int cont = 1;
        for(SeguimientoPagos obj: listaEntity) {
            DominioSeguimientoPagos dominio = new DominioSeguimientoPagos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioSeguimientoPagos> getListaPagination(DominioSeguimientoPagos obj) throws Exception {
        List<DominioSeguimientoPagos> listaDominio = new ArrayList();
        List<SeguimientoPagos> listaEntity = daoSeguimientoPagos.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(SeguimientoPagos entity: listaEntity) {
            DominioSeguimientoPagos dominio = new DominioSeguimientoPagos();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoSeguimientoPagos.getRowCount();
    }
    
    @Override
    public DominioSeguimientoPagos save(DominioSeguimientoPagos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioSeguimientoPagos update(DominioSeguimientoPagos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioSeguimientoPagos delete(DominioSeguimientoPagos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioSeguimientoPagos deleteAll(List<DominioSeguimientoPagos> lista) {
        DominioSeguimientoPagos obj = new DominioSeguimientoPagos();
        List<SeguimientoPagos> entities = new ArrayList();
        try {
            for(DominioSeguimientoPagos dominio: lista)  {
                SeguimientoPagos entity = new SeguimientoPagos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoSeguimientoPagos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioSeguimientoPagos getById(DominioSeguimientoPagos obj) {
        SeguimientoPagos entity;
        try {
            entity = (SeguimientoPagos)daoSeguimientoPagos.getById(obj, obj.getIdSeguimientoPago());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioSeguimientoPagos getTransaction(DominioSeguimientoPagos obj, String accion) {
        SeguimientoPagos entity = new SeguimientoPagos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoSeguimientoPagos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
}
