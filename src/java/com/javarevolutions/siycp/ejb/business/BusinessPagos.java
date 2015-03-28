/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BusinessPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioPagos;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOPagos;
import com.javarevolutions.siycp.ejb.entity.Pagos;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessPagos extends BusinessGeneric implements IfacePagos {
    @Autowired
    private DAOPagos daoPagos;

    @Override
    public List<DominioPagos> getPagos() throws Exception {
        List<DominioPagos> listaDominio = new ArrayList();
        List<Pagos> listaEntity = daoPagos.getLista();
        int cont = 1;
        for(Pagos obj: listaEntity) {
            DominioPagos dominio = new DominioPagos();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioPagos> getListaPagination(DominioPagos obj) throws Exception {
        List<DominioPagos> listaDominio = new ArrayList();
        List<Pagos> listaEntity = daoPagos.getListaPaginationByIdSeguimientoPago(obj.getFirst(), obj.getPageSize(), 
                obj.getSeguimientoPagos().getIdSeguimientoPago());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Pagos entity: listaEntity) {
            DominioPagos dominio = new DominioPagos();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public DominioPagos save(DominioPagos obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioPagos update(DominioPagos obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioPagos delete(DominioPagos obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioPagos deleteAll(List<DominioPagos> lista) {
        DominioPagos obj = new DominioPagos();
        List<Pagos> entities = new ArrayList();
        try {
            for(DominioPagos dominio: lista)  {
                Pagos entity = new Pagos();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoPagos.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioPagos getById(DominioPagos obj) {
        Pagos entity;
        try {
            entity = (Pagos)daoPagos.getById(obj, obj.getIdPago());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioPagos getTransaction(DominioPagos obj, String accion) {
        Pagos entity = new Pagos();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoPagos, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public int getRowCount(int idSeguimientoPago) throws Exception {
        return daoPagos.getRowCount(idSeguimientoPago);
    }
}
