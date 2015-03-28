/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BusinessUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.dominio.Generic;
import com.javarevolutions.siycp.ejb.dao.DAOUsers;
import com.javarevolutions.siycp.ejb.entity.HorarioEmpleados;
import com.javarevolutions.siycp.ejb.entity.Users;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.utils.JRUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessUsers extends BusinessGeneric implements IfaceUsers {
    @Autowired
    private DAOUsers daoUsers;

    @Override
    public List<DominioUsers> getUsers() throws Exception {
        List<DominioUsers> listaDominio = new ArrayList();
        List<Users> listaEntity = daoUsers.getLista();
        int cont = 1;
        for(Users obj: listaEntity) {
            DominioUsers dominio = new DominioUsers();
            JRUtil.copyProperties(dominio, obj);
            dominio.setCont(cont++);
            listaDominio.add(dominio);
        }
        return listaDominio;
    }
    
    @Override
    public List<DominioUsers> getListaPagination(DominioUsers obj) throws Exception {
        List<DominioUsers> listaDominio = new ArrayList();
        List<Users> listaEntity = daoUsers.getListaPagination(obj.getFirst(), obj.getPageSize());
        int cont = obj.getFirst()+1;
        int pos = 0;
        for(Users entity: listaEntity) {
            DominioUsers dominio = new DominioUsers();
            JRUtil.copyProperties(dominio, entity);
            dominio.setCont(cont++);
            dominio.setPosicion(pos++);
            obj.setPosicion(dominio.getPosicion());
            //i = Inactivo, a = Activo
            if(dominio.getStatusUser().equals("i")) {
                dominio.setStatusUser("Inactivo");
                dominio.setLblActivarDesactivar("Activar");
            } else {
                dominio.setStatusUser("Activo");
                dominio.setLblActivarDesactivar("Inactivar");
            }
            listaDominio.add(dominio);
        }
        return listaDominio;
    }

    @Override
    public int getRowCount() throws Exception {
        return daoUsers.getRowCount();
    }
    
    @Override
    public DominioUsers save(DominioUsers obj) {
        return getTransaction(obj, "save");
    }

    @Override
    public DominioUsers update(DominioUsers obj) {
        return getTransaction(obj, "update");
    }

    @Override
    public DominioUsers delete(DominioUsers obj) {
        return getTransaction(obj, "delete");
    }

    @Override
    public DominioUsers deleteAll(List<DominioUsers> lista) {
        DominioUsers obj = new DominioUsers();
        List<Users> entities = new ArrayList();
        try {
            for(DominioUsers dominio: lista)  {
                Users entity = new Users();
                JRUtil.copyProperties(entity, dominio);
                entities.add(entity);
            }
            daoUsers.borrarAll(entities);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("ERROR IN DAO : "+e);
        }
        return obj;
    }
    
    @Override
    public DominioUsers getById(DominioUsers obj) {
        Users entity;
        try {
            entity = (Users)daoUsers.getById(obj, obj.getUser());
            JRUtil.copyProperties(obj, entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg(""+e);
        }
        return obj;
    }
    
    private DominioUsers getTransaction(DominioUsers obj, String accion) {
        Users entity = new Users();
        Generic generic = new Generic();
        JRUtil.copyProperties(entity, obj);
        executeTransaction(entity, generic, daoUsers, accion);
        obj.setStatus(generic.isStatus());
        obj.setMsg(generic.getMsg());
        return obj;
    }

    @Override
    public DominioUsers validateLogin(DominioUsers obj) {
        Users user = new Users();
        JRUtil.copyProperties(user, obj);
        try {
            Object aux;
            if(obj.getValidaAsistencia()) {
                aux = daoUsers.validateAsistenciaLogin(user);
            } else {
                aux = daoUsers.validateLogin(user);
            }
            if(aux != null) {
                JRUtil.copyProperties(obj, aux);
                JRUtil.copyProperties(user, aux);
                user.setFechaAcceso(new java.util.Date());
                daoUsers.actualizar(user);
            } else {
                user = null;
            }
        } catch(Exception e) {
            user = null;
            obj.setStatus(false);
            obj.setMsg("ERROR in validateLogin : "+e);
        }
        if(user != null) {
            obj.setStatus(true);
            obj.setMsg("Welcome");
        } else {
            obj.setStatus(false);
            if(obj.getMsg() == null) {
                obj.setMsg("datosInvalidos");
            }
        }
        return obj;
    }

    @Override
    public DominioUsers registraAsistencia(DominioUsers obj) {
        HorarioEmpleados entity = new HorarioEmpleados();
        try {
            entity.setHoraRegistro(new java.util.Date());
            Usuarios user = new Usuarios();
            JRUtil.copyProperties(user, obj.getUsuarios());
            entity.setUsuarios(user);
            daoUsers.registraAsistencia(entity);
            obj.setStatus(true);
        } catch(Exception e) {
            obj.setStatus(false);
            obj.setMsg("No se pudo registrar tu asistencia : "+e);
        }
        return obj;
    }
}
