/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessAccesoriosxproducto.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioAccesoriosxproducto;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOAccesoriosxproducto;
import com.javarevolutions.siycp.ejb.entity.Accesoriosxproducto;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessAccesoriosxproducto extends BusinessGeneric implements IfaceAccesoriosxproducto {
    @Autowired
    private DAOAccesoriosxproducto daoAccesoriosxproducto;

    @Override
    public List<DominioAccesoriosxproducto> getAccesoriosxproducto() throws Exception {
        List<DominioAccesoriosxproducto> listaDominio = new ArrayList();
        List<Accesoriosxproducto> listaEntity = daoAccesoriosxproducto.getLista();
        int cont = 1;
        for(Accesoriosxproducto obj: listaEntity) {
            DominioAccesoriosxproducto dominio = new DominioAccesoriosxproducto();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioAccesoriosxproducto> getListaPagination(DominioAccesoriosxproducto obj) throws Exception {
        List<DominioAccesoriosxproducto> listaDominio = new ArrayList();
        List<Accesoriosxproducto> listaEntity = daoAccesoriosxproducto.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Accesoriosxproducto entity: listaEntity) {
            DominioAccesoriosxproducto dominio = new DominioAccesoriosxproducto();
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
        return daoAccesoriosxproducto.getRowCount();
    }
    
    @Override
    public DominioAccesoriosxproducto save(DominioAccesoriosxproducto obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioAccesoriosxproducto update(DominioAccesoriosxproducto obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioAccesoriosxproducto delete(DominioAccesoriosxproducto obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioAccesoriosxproducto deleteAll(List<DominioAccesoriosxproducto> lista) {
        DominioAccesoriosxproducto obj = new DominioAccesoriosxproducto();
        List<Accesoriosxproducto> entities = new ArrayList();
        try {
            for(DominioAccesoriosxproducto dominio: lista)  {
                Accesoriosxproducto entity = new Accesoriosxproducto();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoAccesoriosxproducto.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioAccesoriosxproducto getById(DominioAccesoriosxproducto obj) {
        Accesoriosxproducto entity;
        try {
            entity = (Accesoriosxproducto)daoAccesoriosxproducto.getById(obj, obj.getIdAccesorioxproducto());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioAccesoriosxproducto getTransaction(DominioAccesoriosxproducto obj, String accion) {
        Accesoriosxproducto entity = new Accesoriosxproducto();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoAccesoriosxproducto, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
}
