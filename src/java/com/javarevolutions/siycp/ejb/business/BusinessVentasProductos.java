/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BusinessVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.dominio.DominioVentas;
import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOVentasProductos;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import com.javarevolutions.siycp.ejb.entity.VentasProductos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessVentasProductos extends BusinessGeneric implements IfaceVentasProductos {
    @Autowired
    private DAOVentasProductos daoVentasProductos;

    @Override
    public List<DominioVentasProductos> getVentasProductos() throws Exception {
        List<DominioVentasProductos> listaDominio = new ArrayList();
        List<VentasProductos> listaEntity = daoVentasProductos.getLista();
        int cont = 1;
        for(VentasProductos obj: listaEntity) {
            DominioVentasProductos dominio = new DominioVentasProductos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioVentasProductos> getListaPagination(DominioVentasProductos obj) throws Exception {
        List<DominioVentasProductos> listaDominio = new ArrayList();
        List<VentasProductos> listaEntity = daoVentasProductos.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(VentasProductos entity: listaEntity) {
            DominioVentasProductos dominio = new DominioVentasProductos();
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
        return daoVentasProductos.getRowCount();
    }
    
    @Override
    public DominioVentasProductos save(DominioVentasProductos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioVentasProductos update(DominioVentasProductos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioVentasProductos delete(DominioVentasProductos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioVentasProductos deleteAll(List<DominioVentasProductos> lista) {
        DominioVentasProductos obj = new DominioVentasProductos();
        List<VentasProductos> entities = new ArrayList();
        try {
            for(DominioVentasProductos dominio: lista)  {
                VentasProductos entity = new VentasProductos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoVentasProductos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioVentasProductos getById(DominioVentasProductos obj) {
        VentasProductos entity;
        try {
            entity = (VentasProductos)daoVentasProductos.getById(obj, obj.getIdVentaProducto());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioVentasProductos getTransaction(DominioVentasProductos obj, String accion) {
        VentasProductos entity = new VentasProductos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoVentasProductos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public DominioVentasProductos saveAllProdsPedidos(List<DominioVentasProductos> lista, DominioVentas venta) {
        DominioVentasProductos obj = new DominioVentasProductos();
        List<VentasProductos> entities = new ArrayList();
        try {
            for(DominioVentasProductos dominio: lista)  {
                VentasProductos entity = new VentasProductos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            Ventas auxPedido = new Ventas();
            JRUtil.copyProperties(auxPedido, venta);
            daoVentasProductos.saveAllProdsPedidos(entities, auxPedido);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;        
    }

    @Override
    public DominioVentasProductos updateAllProdsPedidos(List<DominioVentasProductos> listaAdd, 
            List<DominioVentasProductos> listaDel, DominioVentas venta) {
        DominioVentasProductos obj = new DominioVentasProductos();
        List<VentasProductos> entitiesAdd = new ArrayList();
        List<VentasProductos> entitiesDel = new ArrayList();
        try {
            for(DominioVentasProductos dominio: listaAdd)  {
                VentasProductos entity = new VentasProductos();
                JRUtil.copyProperties(entity, dominio);
                entitiesAdd.add(entity);
            }
            for(DominioVentasProductos dominio: listaDel)  {
                VentasProductos entity = new VentasProductos();
                JRUtil.copyProperties(entity, dominio);
                entitiesDel.add(entity);
            }
            Ventas auxPedido = new Ventas();
            JRUtil.copyProperties(auxPedido, venta);
            daoVentasProductos.updateAllProdsPedidos(entitiesAdd, entitiesDel, auxPedido);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;        
    }
    
    @Override
    public List<DominioVentasProductos> getListaBorrar(DominioVentasProductos obj) throws Exception {
        VentasProductos auxEntity = new VentasProductos();
        List<DominioVentasProductos> listaDominio = new ArrayList();
        JRUtil.copyProperties(auxEntity, obj);
        List<VentasProductos> listaEntity = daoVentasProductos.getListaBorrar(auxEntity);
        for(VentasProductos entity: listaEntity) {
            DominioVentasProductos dominio = new DominioVentasProductos();
            JRUtil.copyProperties(dominio, entity);
            dominio.getProductos().setCantidad(entity.getCantidad());
            Usuarios userEntity = daoVentasProductos.getUserById(entity.getVentas().getIdUsuario());
            if(userEntity != null) {
                DominioUsuarios auxUsuario = new DominioUsuarios();
                JRUtil.copyProperties(auxUsuario, userEntity);
                dominio.getVentas().setUsuarios(auxUsuario);
            }
            dominio.getProductos().setExistencia(daoVentasProductos.getExistenciaInventarios(entity.getProductos()));
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
}
