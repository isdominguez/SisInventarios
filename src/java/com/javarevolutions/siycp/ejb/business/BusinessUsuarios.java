/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessUsuarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOUsuarios;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessUsuarios extends BusinessGeneric implements IfaceUsuarios {
    @Autowired
    private DAOUsuarios daoUsuarios;

    @Override
    public List<DominioUsuarios> getUsuarios() throws Exception {
        List<DominioUsuarios> listaDominio = new ArrayList();
        List<Usuarios> listaEntity = daoUsuarios.getLista();
        int cont = 1;
        for(Usuarios obj: listaEntity) {
            DominioUsuarios dominio = new DominioUsuarios();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioUsuarios> getListaPagination(DominioUsuarios obj) throws Exception {
        Usuarios entity = new Usuarios();
        JRUtil.copyProperties(entity, obj);
        List<DominioUsuarios> listaDominio = new ArrayList();
        List<Usuarios> listaEntity = daoUsuarios.getListaPaginationByTipo(obj.getFirst(), 
                obj.getPageSize(), entity);
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Usuarios auxEntity: listaEntity) {
            DominioUsuarios dominio = new DominioUsuarios();
            JRUtil.copyProperties(dominio, auxEntity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoUsuarios.getRowCount();
    }
    
    @Override
    public DominioUsuarios save(DominioUsuarios obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioUsuarios update(DominioUsuarios obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioUsuarios delete(DominioUsuarios obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioUsuarios deleteAll(List<DominioUsuarios> lista) {
        DominioUsuarios obj = new DominioUsuarios();
        List<Usuarios> entities = new ArrayList();
        try {
            for(DominioUsuarios dominio: lista)  {
                Usuarios entity = new Usuarios();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoUsuarios.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioUsuarios getById(DominioUsuarios obj) {
        Usuarios entity;
        try {
            entity = (Usuarios)daoUsuarios.getById(obj, obj.getIdUsuario());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioUsuarios getTransaction(DominioUsuarios obj, String accion) {
        Usuarios entity = new Usuarios();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoUsuarios, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public List<DominioUsuarios> getListaByTipo(DominioUsuarios obj) throws Exception {
        Usuarios entity = new Usuarios();
        JRUtil.copyProperties(entity, obj);
        List<DominioUsuarios> listaDominio = new ArrayList();
        List<Usuarios> listaEntity = daoUsuarios.getListaByTipo(entity);
        int cont = 1;
        for(Usuarios user: listaEntity) {
            DominioUsuarios dominio = new DominioUsuarios();
            JRUtil.copyProperties(dominio, user);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowsByTipo(DominioUsuarios obj) throws Exception {
        Usuarios entity = new Usuarios();
        JRUtil.copyProperties(entity, obj);
        return daoUsuarios.getRowsByTipo(entity);
    }
}
