/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessTiposMedidas.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioTiposMedidas;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOTiposMedidas;
import com.javarevolutions.siycp.ejb.entity.TiposMedidas;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessTiposMedidas extends BusinessGeneric implements IfaceTiposMedidas {
    @Autowired
    private DAOTiposMedidas daoTiposMedidas;

    @Override
    public List<DominioTiposMedidas> getTiposMedidas() throws Exception {
        List<DominioTiposMedidas> listaDominio = new ArrayList();
        List<TiposMedidas> listaEntity = daoTiposMedidas.getLista();
        int cont = 1;
        for(TiposMedidas obj: listaEntity) {
            DominioTiposMedidas dominio = new DominioTiposMedidas();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioTiposMedidas> getListaPagination(DominioTiposMedidas obj) throws Exception {
        List<DominioTiposMedidas> listaDominio = new ArrayList();
        List<TiposMedidas> listaEntity = daoTiposMedidas.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(TiposMedidas entity: listaEntity) {
            DominioTiposMedidas dominio = new DominioTiposMedidas();
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
        return daoTiposMedidas.getRowCount();
    }
    
    @Override
    public DominioTiposMedidas save(DominioTiposMedidas obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioTiposMedidas update(DominioTiposMedidas obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioTiposMedidas delete(DominioTiposMedidas obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioTiposMedidas deleteAll(List<DominioTiposMedidas> lista) {
        DominioTiposMedidas obj = new DominioTiposMedidas();
        List<TiposMedidas> entities = new ArrayList();
        try {
            for(DominioTiposMedidas dominio: lista)  {
                TiposMedidas entity = new TiposMedidas();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoTiposMedidas.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioTiposMedidas getById(DominioTiposMedidas obj) {
        TiposMedidas entity;
        try {
            entity = (TiposMedidas)daoTiposMedidas.getById(obj, obj.getIdTipoMedida());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioTiposMedidas getTransaction(DominioTiposMedidas obj, String accion) {
        TiposMedidas entity = new TiposMedidas();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoTiposMedidas, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
}
