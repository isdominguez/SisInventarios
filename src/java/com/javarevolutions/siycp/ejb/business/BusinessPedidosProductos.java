/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessPedidosProductos.java
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
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOPedidosProductos;
import com.javarevolutions.siycp.ejb.entity.Pedidos;
import com.javarevolutions.siycp.ejb.entity.PedidosProductos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessPedidosProductos extends BusinessGeneric implements IfacePedidosProductos {
    @Autowired
    private DAOPedidosProductos daoPedidosProductos;

    @Override
    public List<DominioPedidosProductos> getPedidosProductos() throws Exception {
        List<DominioPedidosProductos> listaDominio = new ArrayList();
        List<PedidosProductos> listaEntity = daoPedidosProductos.getLista();
        int cont = 1;
        for(PedidosProductos obj: listaEntity) {
            DominioPedidosProductos dominio = new DominioPedidosProductos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioPedidosProductos> getListaPagination(DominioPedidosProductos obj) throws Exception {
        List<DominioPedidosProductos> listaDominio = new ArrayList();
        List<PedidosProductos> listaEntity = daoPedidosProductos.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(PedidosProductos entity: listaEntity) {
            DominioPedidosProductos dominio = new DominioPedidosProductos();
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
        return daoPedidosProductos.getRowCount();
    }
    
    @Override
    public DominioPedidosProductos save(DominioPedidosProductos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioPedidosProductos update(DominioPedidosProductos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioPedidosProductos delete(DominioPedidosProductos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioPedidosProductos deleteAll(List<DominioPedidosProductos> lista) {
        DominioPedidosProductos obj = new DominioPedidosProductos();
        List<PedidosProductos> entities = new ArrayList();
        try {
            for(DominioPedidosProductos dominio: lista)  {
                PedidosProductos entity = new PedidosProductos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoPedidosProductos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioPedidosProductos getById(DominioPedidosProductos obj) {
        PedidosProductos entity;
        try {
            entity = (PedidosProductos)daoPedidosProductos.getById(obj, obj.getIdPedidoProducto());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioPedidosProductos getTransaction(DominioPedidosProductos obj, String accion) {
        PedidosProductos entity = new PedidosProductos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoPedidosProductos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public DominioPedidosProductos saveAllProdsPedidos(List<DominioPedidosProductos> lista, DominioPedidos pedido) {
        DominioPedidosProductos obj = new DominioPedidosProductos();
        List<PedidosProductos> entities = new ArrayList();
        try {
            for(DominioPedidosProductos dominio: lista)  {
                PedidosProductos entity = new PedidosProductos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            Pedidos auxPedido = new Pedidos();
            JRUtil.copyProperties(auxPedido, pedido);
            daoPedidosProductos.saveAllProdsPedidos(entities, auxPedido);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;        
    }

    @Override
    public DominioPedidosProductos updateAllProdsPedidos(List<DominioPedidosProductos> listaAdd, 
            List<DominioPedidosProductos> listaDel, DominioPedidos pedido) {
        DominioPedidosProductos obj = new DominioPedidosProductos();
        List<PedidosProductos> entitiesAdd = new ArrayList();
        List<PedidosProductos> entitiesDel = new ArrayList();
        try {
            for(DominioPedidosProductos dominio: listaAdd)  {
                PedidosProductos entity = new PedidosProductos();
                JRUtil.copyProperties(entity, dominio);
                entitiesAdd.add(entity);
            }
            for(DominioPedidosProductos dominio: listaDel)  {
                PedidosProductos entity = new PedidosProductos();
                JRUtil.copyProperties(entity, dominio);
                entitiesDel.add(entity);
            }
            Pedidos auxPedido = new Pedidos();
            JRUtil.copyProperties(auxPedido, pedido);
            daoPedidosProductos.updateAllProdsPedidos(entitiesAdd, entitiesDel, auxPedido);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;        
    }
    
    @Override
    public List<DominioPedidosProductos> getListaBorrar(DominioPedidosProductos obj) throws Exception {
        PedidosProductos auxEntity = new PedidosProductos();
        List<DominioPedidosProductos> listaDominio = new ArrayList();
        JRUtil.copyProperties(auxEntity, obj);
        List<PedidosProductos> listaEntity = daoPedidosProductos.getListaBorrar(auxEntity);
        for(PedidosProductos entity: listaEntity) {
            DominioPedidosProductos dominio = new DominioPedidosProductos();
            JRUtil.copyProperties(dominio, entity);
            dominio.getProductos().setCantidad(entity.getCantidad());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
}
