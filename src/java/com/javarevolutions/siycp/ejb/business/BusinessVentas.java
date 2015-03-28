/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BusinessVentas.java
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
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOVentas;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessVentas extends BusinessGeneric implements IfaceVentas {
    @Autowired
    private DAOVentas daoVentas;

    @Override
    public List<DominioVentas> getVentas() throws Exception {
        List<DominioVentas> listaDominio = new ArrayList();
        List<Ventas> listaEntity = daoVentas.getLista();
        int cont = 1;
        for(Ventas obj: listaEntity) {
            DominioVentas dominio = new DominioVentas();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioVentas> getListaPagination(DominioVentas obj) throws Exception {
        List<DominioVentas> listaDominio = new ArrayList();
        List<Ventas> listaEntity = daoVentas.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Ventas entity: listaEntity) {
            DominioVentas dominio = new DominioVentas();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            if(dominio.getIdUsuario() != null && dominio.getIdUsuario() > 0) {
                Usuarios user = daoVentas.getUserById(dominio.getIdUsuario());
                DominioUsuarios domUsuario = new DominioUsuarios();
                JRUtil.copyProperties(domUsuario, user);
                dominio.setUsuarios(domUsuario);
            }
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoVentas.getRowCount();
    }
    
    @Override
    public DominioVentas save(DominioVentas obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioVentas update(DominioVentas obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioVentas delete(DominioVentas obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioVentas deleteAll(List<DominioVentas> lista) {
        DominioVentas obj = new DominioVentas();
        List<Ventas> entities = new ArrayList();
        try {
            for(DominioVentas dominio: lista)  {
                Ventas entity = new Ventas();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoVentas.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioVentas getById(DominioVentas obj) {
        Ventas entity;
        try {
            entity = (Ventas)daoVentas.getById(obj, obj.getIdVenta());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioVentas getTransaction(DominioVentas obj, String accion) {
        Ventas entity = new Ventas();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoVentas, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }
    
    @Override
    public List<DominioVentas> getVentasByDia(DominioVentas obj) throws Exception {
        List<DominioVentas> listaDominio = new ArrayList();
        List<Ventas> listaEntity = daoVentas.getVentasByDia(obj.getFechaVenta(), obj.getFirst(), 
                obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Ventas entity: listaEntity) {
            DominioVentas dominio = new DominioVentas();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            if(dominio.getIdUsuario() != null && dominio.getIdUsuario() > 0) {
                Usuarios user = daoVentas.getUserById(dominio.getIdUsuario());
                DominioUsuarios domUsuario = new DominioUsuarios();
                JRUtil.copyProperties(domUsuario, user);
                dominio.setUsuarios(domUsuario);
            }
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public List<DominioVentas> getVentasByFechas(DominioVentas obj) throws Exception {
        List<DominioVentas> listaDominio = new ArrayList();
        List<Ventas> listaEntity = daoVentas.getVentasByFechas(obj.getFechaInicio(), obj.getFechaFin(), 
                obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Ventas entity: listaEntity) {
            DominioVentas dominio = new DominioVentas();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            if(dominio.getIdUsuario() != null && dominio.getIdUsuario() > 0) {
                Usuarios user = daoVentas.getUserById(dominio.getIdUsuario());
                DominioUsuarios domUsuario = new DominioUsuarios();
                JRUtil.copyProperties(domUsuario, user);
                dominio.setUsuarios(domUsuario);
            }
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCountByDia(DominioVentas obj) throws Exception {
        return daoVentas.getRowCountByDia(obj.getFechaVenta());
    }

    @Override
    public int getRowCountByFechas(DominioVentas obj) throws Exception {
        return daoVentas.getRowCountByFechas(obj.getFechaInicio(), obj.getFechaFin());
    }
}
