/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessPedidos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioPedidos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOPedidos;
import com.javarevolutions.siycp.ejb.entity.Pedidos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessPedidos extends BusinessGeneric implements IfacePedidos {
    @Autowired
    private DAOPedidos daoPedidos;

    @Override
    public List<DominioPedidos> getPedidos() throws Exception {
        List<DominioPedidos> listaDominio = new ArrayList();
        List<Pedidos> listaEntity = daoPedidos.getLista();
        int cont = 1;
        for(Pedidos obj: listaEntity) {
            DominioPedidos dominio = new DominioPedidos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioPedidos> getListaPagination(DominioPedidos obj) throws Exception {
        List<DominioPedidos> listaDominio = new ArrayList();
        List<Pedidos> listaEntity = daoPedidos.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Pedidos entity: listaEntity) {
            DominioPedidos dominio = new DominioPedidos();
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
        return daoPedidos.getRowCount();
    }
    
    @Override
    public DominioPedidos save(DominioPedidos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioPedidos update(DominioPedidos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioPedidos delete(DominioPedidos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioPedidos deleteAll(List<DominioPedidos> lista) {
        DominioPedidos obj = new DominioPedidos();
        List<Pedidos> entities = new ArrayList();
        try {
            for(DominioPedidos dominio: lista)  {
                Pedidos entity = new Pedidos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoPedidos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioPedidos getById(DominioPedidos obj) {
        Pedidos entity;
        try {
            entity = (Pedidos)daoPedidos.getById(obj, obj.getIdPedido());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioPedidos getTransaction(DominioPedidos obj, String accion) {
        Pedidos entity = new Pedidos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoPedidos, accion);
        JRUtil.copyProperties(obj, entity);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public List<DominioPedidos> getPedidosByDia(DominioPedidos obj) throws Exception {
        List<DominioPedidos> listaDominio = new ArrayList();
        List<Pedidos> listaEntity = daoPedidos.getPedidosByDia(obj.getFechaPedido(), obj.getFirst(), 
                obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Pedidos entity: listaEntity) {
            DominioPedidos dominio = new DominioPedidos();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public List<DominioPedidos> getPedidosByFechas(DominioPedidos obj) throws Exception {
        List<DominioPedidos> listaDominio = new ArrayList();
        List<Pedidos> listaEntity = daoPedidos.getPedidosByFechas(obj.getFechaInicio(), obj.getFechaFin(), 
                obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Pedidos entity: listaEntity) {
            DominioPedidos dominio = new DominioPedidos();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCountByDia(DominioPedidos obj) throws Exception {
        return daoPedidos.getRowCountByDia(obj.getFechaPedido());
    }

    @Override
    public int getRowCountByFechas(DominioPedidos obj) throws Exception {
        return daoPedidos.getRowCountByFechas(obj.getFechaInicio(), obj.getFechaFin());
    }
}
