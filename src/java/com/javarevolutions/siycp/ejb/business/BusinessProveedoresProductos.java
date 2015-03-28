/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessProveedoresProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioProveedoresProductos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOProveedoresProductos;
import com.javarevolutions.siycp.ejb.entity.ProveedoresProductos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessProveedoresProductos extends BusinessGeneric implements IfaceProveedoresProductos {
    @Autowired
    private DAOProveedoresProductos daoProveedoresProductos;

    @Override
    public List<DominioProveedoresProductos> getProveedoresProductos() throws Exception {
        List<DominioProveedoresProductos> listaDominio = new ArrayList();
        List<ProveedoresProductos> listaEntity = daoProveedoresProductos.getLista();
        int cont = 1;
        for(ProveedoresProductos obj: listaEntity) {
            DominioProveedoresProductos dominio = new DominioProveedoresProductos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioProveedoresProductos> getListaPagination(DominioProveedoresProductos obj) throws Exception {
        List<DominioProveedoresProductos> listaDominio = new ArrayList();
        List<ProveedoresProductos> listaEntity = daoProveedoresProductos.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(ProveedoresProductos entity: listaEntity) {
            DominioProveedoresProductos dominio = new DominioProveedoresProductos();
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
        return daoProveedoresProductos.getRowCount();
    }
    
    @Override
    public DominioProveedoresProductos save(DominioProveedoresProductos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioProveedoresProductos update(DominioProveedoresProductos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioProveedoresProductos delete(DominioProveedoresProductos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioProveedoresProductos deleteAll(List<DominioProveedoresProductos> lista) {
        DominioProveedoresProductos obj = new DominioProveedoresProductos();
        List<ProveedoresProductos> entities = new ArrayList();
        try {
            for(DominioProveedoresProductos dominio: lista)  {
                ProveedoresProductos entity = new ProveedoresProductos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoProveedoresProductos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioProveedoresProductos getById(DominioProveedoresProductos obj) {
        ProveedoresProductos entity;
        try {
            entity = (ProveedoresProductos)daoProveedoresProductos.getById(obj, obj.getIdProveedorProducto());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioProveedoresProductos getTransaction(DominioProveedoresProductos obj, String accion) {
        ProveedoresProductos entity = new ProveedoresProductos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoProveedoresProductos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
}
