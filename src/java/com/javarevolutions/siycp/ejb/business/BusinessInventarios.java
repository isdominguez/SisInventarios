/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessInventarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOInventarios;
import com.javarevolutions.siycp.ejb.entity.Inventarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessInventarios extends BusinessGeneric implements IfaceInventarios {
    @Autowired
    private DAOInventarios daoInventarios;

    @Override
    public List<DominioInventarios> getInventarios() throws Exception {
        List<DominioInventarios> listaDominio = new ArrayList();
        List<Inventarios> listaEntity = daoInventarios.getLista();
        int cont = 1;
        for(Inventarios obj: listaEntity) {
            DominioInventarios dominio = new DominioInventarios();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioInventarios> getListaPagination(DominioInventarios obj) throws Exception {
        List<DominioInventarios> listaDominio = new ArrayList();
        List<Inventarios> listaEntity = daoInventarios.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Inventarios entity: listaEntity) {
            DominioInventarios dominio = new DominioInventarios();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            if(dominio.getCantidad() <= dominio.getMinimoExistencia()) {
                dominio.setStyle("font-size: 18px; color: red; font-weight: bolder;");
            } else {
                dominio.setStyle("font-size: 14px;");
            }
            dominio.setExistencia(dominio.getCantidad());
            dominio.setCantidad(new Long(0));
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoInventarios.getRowCount();
    }
    
    @Override
    public DominioInventarios save(DominioInventarios obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioInventarios update(DominioInventarios obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioInventarios delete(DominioInventarios obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioInventarios deleteAll(List<DominioInventarios> lista) {
        DominioInventarios obj = new DominioInventarios();
        List<Inventarios> entities = new ArrayList();
        try {
            for(DominioInventarios dominio: lista)  {
                Inventarios entity = new Inventarios();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoInventarios.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioInventarios getById(DominioInventarios obj) {
        Inventarios entity;
        try {
            entity = (Inventarios)daoInventarios.getById(obj, obj.getIdInventario());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioInventarios getTransaction(DominioInventarios obj, String accion) {
        Inventarios entity = new Inventarios();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoInventarios, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public DominioInventarios createAllProductos() {
        DominioInventarios obj = new DominioInventarios();
        try {
            daoInventarios.createAllProductos();
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR in DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioInventarios updateCantidades(List<DominioInventarios> lista) {
        DominioInventarios obj = new DominioInventarios();
        List<Inventarios> auxInventarios = new ArrayList();
        try {
            for(DominioInventarios dom: lista) {
                Inventarios entity = new Inventarios();
                JRUtil.copyProperties(entity, dom);
                if(dom.getExistencia() < 0 && dom.getCantidad() > 0) {
                    entity.setCantidad(dom.getCantidad());
                } else {
                    entity.setCantidad(dom.getExistencia()+dom.getCantidad());
                }
                auxInventarios.add(entity);
            }
            daoInventarios.updateCantidades(auxInventarios);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR in DAO : "+e);
        }
        return obj;
    }

    @Override
    public DominioInventarios getStatusInventario() {
        DominioInventarios dom = new DominioInventarios();
        dom.setStatus(true);
        int rows = daoInventarios.getStatusInventario(10);
        if(rows > 0) {
            dom.setStatus(false);
        }
        return dom;
    }
}
