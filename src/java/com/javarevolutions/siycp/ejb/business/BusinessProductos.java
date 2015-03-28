/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOProductos;
import com.javarevolutions.siycp.ejb.entity.Productos;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessProductos extends BusinessGeneric implements IfaceProductos {
    @Autowired
    private DAOProductos daoProductos;

    @Override
    public List<DominioProductos> getProductos() throws Exception {
        List<DominioProductos> listaDominio = new ArrayList();
        List<Productos> listaEntity = daoProductos.getLista();
        int cont = 1;
        for(Productos obj: listaEntity) {
            DominioProductos dominio = new DominioProductos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioProductos> getListaPagination(DominioProductos obj) throws Exception {
        Productos entity = new Productos();
        JRUtil.copyProperties(entity, obj);
        List<DominioProductos> listaDominio = new ArrayList();
        List<Productos> listaEntity = daoProductos.getListaPaginationByTipo(
                obj.getFirst(), obj.getPageSize(), entity);
        int cont = obj.getFirst()+1;
        int pos = 0;
        Usuarios tipoUsuario = new Usuarios();
        tipoUsuario.setIdUsuario(obj.getUsuarios().getIdUsuario());
        if(obj.getUsuarios().getIdUsuario() != null) {
            Object auxObj = daoProductos.getUserById(tipoUsuario, tipoUsuario.getIdUsuario());
            if(auxObj != null) {
                tipoUsuario = (Usuarios)auxObj;
            }
        }
        for(Productos auxEntity: listaEntity) {
            DominioProductos dominio = new DominioProductos();
            JRUtil.copyProperties(dominio, auxEntity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            if(obj.getEntidadPersistir().equals("Ventas")) {
                dominio.setExistencia(daoProductos.getExistenciaInventarios(auxEntity));
                //General, Intermedio, Especial
                if(tipoUsuario.getTipoCliente() != null) {
                    if(tipoUsuario.getTipoCliente().equals(Global.GENERAL)) {
                        dominio.setCosto(dominio.getPrecioGeneral());
                    }
                    if(tipoUsuario.getTipoCliente().equals(Global.INTERMEDIO)) {
                        dominio.setCosto(dominio.getPrecioIntermedio());
                    }
                    if(tipoUsuario.getTipoCliente().equals(Global.ESPECIAL)) {
                        dominio.setCosto(dominio.getPrecioEspecial());
                    }
                } else {
                    dominio.setCosto(dominio.getPrecioGeneral());
                }
            }
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoProductos.getRowCount();
    }
    
    @Override
    public DominioProductos save(DominioProductos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioProductos update(DominioProductos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioProductos delete(DominioProductos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioProductos deleteAll(List<DominioProductos> lista) {
        DominioProductos obj = new DominioProductos();
        List<Productos> entities = new ArrayList();
        try {
            for(DominioProductos dominio: lista)  {
                Productos entity = new Productos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoProductos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO deleteAll : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioProductos getById(DominioProductos obj) {
        Productos entity;
        try {
            entity = (Productos)daoProductos.getById(obj, obj.getIdProducto());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioProductos getTransaction(DominioProductos obj, String accion) {
        Productos entity = new Productos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoProductos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public List<DominioProductos> getListaByTipo(DominioProductos obj) throws Exception {
        Productos entity = new Productos();
        JRUtil.copyProperties(entity, obj);
        List<DominioProductos> listaDominio = new ArrayList();
        List<Productos> listaEntity = daoProductos.getListaByTipo(entity);
        int cont = 1;
        for(Productos aux: listaEntity) {
            DominioProductos dominio = new DominioProductos();
            JRUtil.copyProperties(dominio, aux);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowsByTipo(DominioProductos obj) throws Exception {
        Productos entity = new Productos();
        JRUtil.copyProperties(entity, obj);
        return daoProductos.getRowsByTipo(entity);
    }

    @Override
    public List<DominioProductos> getListaPaginationByProveedor(DominioProductos obj) throws Exception {
        Productos entity = new Productos();
        JRUtil.copyProperties(entity, obj);
        List<DominioProductos> listaDominio = new ArrayList();
        List<Productos> listaEntity = daoProductos.getListaPaginationByProveedor(
                obj.getFirst(), obj.getPageSize(), entity);
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Productos auxEntity: listaEntity) {
            DominioProductos dominio = new DominioProductos();
            JRUtil.copyProperties(dominio, auxEntity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowsByProveedor(DominioProductos obj) throws Exception {
        Productos entity = new Productos();
        JRUtil.copyProperties(entity, obj);
        return daoProductos.getRowsByProveedor(entity);
    }

    @Override
    public DominioUsuarios getUserById(DominioUsuarios obj) {
        Usuarios entity;
        try {
            entity = (Usuarios)daoProductos.getById(obj, obj.getIdUsuario());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
}
