/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanUsers.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceUsers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class BeanUsers extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanUsers.class);
    private DominioUsers dominio;
    private LazyDataModel<DominioUsers> listaModel;
    private IfaceUsers serviceUsers;
    private List<DominioUsers> listaUsers;
    private String password;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioUsers> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioUsers> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioUsers getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioUsers dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceUsers
     */
    public IfaceUsers getServiceUsers() {
        return serviceUsers;
    }

    /**
     * @param serviceUsers the serviceUsers to set
     */
    public void setServiceUsers(IfaceUsers serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    /**
     * @return the listaUsers
     */
    public List<DominioUsers> getListaUsers() {
        return listaUsers;
    }

    /**
     * @param listaUsers the listaUsers to set
     */
    public void setListaUsers(List<DominioUsers> listaUsers) {
        this.listaUsers = listaUsers;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioUsers>() {
            @Override
            public List<DominioUsers> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioUsers> result = new ArrayList();
                try {
                    DominioUsers auxDominio = new DominioUsers();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceUsers.getListaPagination(auxDominio);
                    setListaUsers(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceUsers.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        if(!dominio.getPassword().equals(getPassword())) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"),
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            context.addCallbackParam("statusTransaction", false);
            context.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            return;
        }
        dominio.setFechaAcceso(new java.util.Date());
        dominio.setStatusUser("i");
        setAccion("save");
        DominioUsers obj = (DominioUsers)serviceUsers.save(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.insert"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.insert"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            context.addCallbackParam("errorTransaction", obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
        context.addCallbackParam("statusTransaction", obj.isStatus());
    }
    
    @Override
    public void updateRow(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        if(!dominio.getPassword().equals(getPassword())) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"),
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            context.addCallbackParam("statusTransaction", false);
            context.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            return;
        }
        setAccion("update");
        DominioUsers obj = (DominioUsers)serviceUsers.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
            context.addCallbackParam("errorTransaction", obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
        context.addCallbackParam("statusTransaction", obj.isStatus());
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioUsers obj = ((DominioUsers)event.getObject());
        int contador = obj.getCont();
        obj = (DominioUsers)serviceUsers.update(obj);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void delete(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        if(dominio.getUser() != null) {
            DominioUsers obj = (DominioUsers)serviceUsers.getById(dominio);
            obj = (DominioUsers)serviceUsers.delete(obj);
            if(obj.isStatus()) {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.delete"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+dominio.getCont()+"' "+Global.getPropRB("com.jr.props.msg.registro.el.delete"));
            } else {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
                context.addCallbackParam("errorTransaction", obj.getMsg());
                log.error("ERROR : "+obj.getMsg());
            }
            context.addCallbackParam("statusTransaction", obj.isStatus());
        } else {
            context.addCallbackParam("statusTransaction", false);
            context.addCallbackParam("errorTransaction", "The Field 'User' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioUsers();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioUsers)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioUsers> listaBorrar = new ArrayList();
        for(DominioUsers dom: getListaUsers()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioUsers obj = (DominioUsers)serviceUsers.deleteAll(listaBorrar);
        if(obj.isStatus() && cont > 0) {
            if(cont > 1) {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.deleteAll"), 
                    "'"+cont+"' "+Global.getPropRB("com.jr.props.msg.registros")+" "+Global.getPropRB("com.jr.props.msg.registro.el.deleteAll"));
                context.addCallbackParam("errorTransaction", "'"+cont+"' "+Global.getPropRB("com.jr.props.msg.registros")+" "+Global.getPropRB("com.jr.props.msg.registro.el.deleteAll"));
            } else {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.delete"), 
                    "'"+cont+"' "+Global.getPropRB("com.jr.props.msg.registro")+" "+Global.getPropRB("com.jr.props.msg.registro.el.delete"));
                context.addCallbackParam("errorTransaction", "'"+cont+"' "+Global.getPropRB("com.jr.props.msg.registro")+" "+Global.getPropRB("com.jr.props.msg.registro.el.delete"));
            }
        } else {
            if(obj.getMsg() != null) {
                System.out.println("ERROR : "+obj.getMsg());
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
                context.addCallbackParam("errorTransaction", obj.getMsg());
                log.error("ERROR : "+obj.getMsg());
            }
        }
        if(cont == 0 && obj.getMsg() == null) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), 
                    Global.getPropRB("com.jr.props.msg.registro.deleteAll.vacio"));
            context.addCallbackParam("errorTransaction", Global.getPropRB("com.jr.props.msg.registro.deleteAll.vacio"));
        }
    }
    
    public void showUsers(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioUsers)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioUsers getById(DominioUsers obj) {
        return (DominioUsers)serviceUsers.getById(obj);
    }
    
    public void showUsuarioByUser(DominioUsers obj) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show")+
                " "+Global.getPropRB("com.jr.props.de")+
                " "+Global.getPropRB("com.jr.props.menu.cat.usuarios"));
        dominio = obj;
    }
    
    public void showChangePassword() {
        dominio = (DominioUsers)Global.getSession().getAttribute("userInSession");
    }
    
    public void changePassword(ActionEvent e) {
        RequestContext context = RequestContext.getCurrentInstance();
        if(!dominio.getPassword().equals(getPassword())) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"),
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            context.addCallbackParam("statusTransaction", false);
            context.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.msg.user.nomatch.password"));
            return;
        }
        setAccion("update");
        DominioUsers obj = (DominioUsers)serviceUsers.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.change.password.success"));
            context.addCallbackParam("msgSuccess", Global.getPropRB("com.jr.props.change.password.success"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
            context.addCallbackParam("errorTransaction", obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
        context.addCallbackParam("statusTransaction", obj.isStatus());
    }
    
    
    public void activarInactivarUsuario(DominioUsers user) {
        dominio = user;
        Object userInSession = Global.getSession().getAttribute("userInSession");
        if(userInSession != null) {
            DominioUsers userSession = (DominioUsers)userInSession;
            if(dominio.getUser().equals(userSession.getUser())) {
                String headerMsg = "Error de datos";
                String errorMsg = "No puedes "+dominio.getLblActivarDesactivar()+" el usuario con el que estas en sesión";
                Global.addMsg(headerMsg, errorMsg);
                return;
            }
        }
        //a = Activo, i = Inactivo, en la pantalla de consulta mostramos Activo o Inactivo, en la base de datos se guarda a o i
        if(dominio.getStatusUser().equals("Activo")) {
            dominio.setStatusUser("i");
        } else {
            dominio.setStatusUser("a");
        }
        DominioUsers obj = (DominioUsers)serviceUsers.update(dominio);
        if(obj.isStatus()) {
            String statusUser = dominio.getStatusUser().equals("a") ? "Activado" : "Inactivado";
            Global.addMsg("Usuario "+statusUser+" exitosamente",
                    "El Usuario '"+obj.getUsuarios().getNombre()+" "+obj.getUsuarios().getApp()+" "+obj.getUsuarios().getApm()+
                    "' fue "+statusUser+" exitosamente");
        } else {
            Global.addMsg("Error en transacción", obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
}
