/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BeanHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioHorarioEmpleados;
import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceHorarioEmpleados;
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

public class BeanHorarioEmpleados extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanHorarioEmpleados.class);
    private DominioHorarioEmpleados dominio;
    private LazyDataModel<DominioHorarioEmpleados> listaModel;
    private IfaceHorarioEmpleados serviceHorarioEmpleados;
    private List<DominioHorarioEmpleados> listaHorarioEmpleados;
    private DominioUsers user;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioHorarioEmpleados> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioHorarioEmpleados> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioHorarioEmpleados getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioHorarioEmpleados dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceHorarioEmpleados
     */
    public IfaceHorarioEmpleados getServiceHorarioEmpleados() {
        return serviceHorarioEmpleados;
    }

    /**
     * @param serviceHorarioEmpleados the serviceHorarioEmpleados to set
     */
    public void setServiceHorarioEmpleados(IfaceHorarioEmpleados serviceHorarioEmpleados) {
        this.serviceHorarioEmpleados = serviceHorarioEmpleados;
    }

    /**
     * @return the listaHorarioEmpleados
     */
    public List<DominioHorarioEmpleados> getListaHorarioEmpleados() {
        return listaHorarioEmpleados;
    }

    /**
     * @param listaHorarioEmpleados the listaHorarioEmpleados to set
     */
    public void setListaHorarioEmpleados(List<DominioHorarioEmpleados> listaHorarioEmpleados) {
        this.listaHorarioEmpleados = listaHorarioEmpleados;
    }

    /**
     * @return the user
     */
    public DominioUsers getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(DominioUsers user) {
        this.user = user;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioHorarioEmpleados>() {
            @Override
            public List<DominioHorarioEmpleados> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioHorarioEmpleados> result = new ArrayList();
                try {
                    DominioHorarioEmpleados auxDominio = new DominioHorarioEmpleados();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    auxDominio.setUsuarios(getUser().getUsuarios());
                    result = serviceHorarioEmpleados.getListaPagination(auxDominio);
                    setListaHorarioEmpleados(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceHorarioEmpleados.getRowCountByUser(getUser().getUsuarios()));
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioHorarioEmpleados obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.save(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.insert"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+(getListaModel().getRowCount()+1)+"' "+Global.getPropRB("com.jr.props.msg.registro.el.insert"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            context.addCallbackParam("errorTransaction", obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
        context.addCallbackParam("statusTransaction", obj.isStatus());
    }
    
    @Override
    public void updateRow(ActionEvent ae) {
        setAccion("update");
        int contador = dominio.getCont();
        DominioHorarioEmpleados obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioHorarioEmpleados obj = ((DominioHorarioEmpleados)event.getObject());
        int contador = obj.getCont();
        obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.update(obj);
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
        if(dominio.getIdHorario() != null) {
            DominioHorarioEmpleados obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.getById(dominio);
            obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdHorario' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioHorarioEmpleados();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioHorarioEmpleados)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioHorarioEmpleados> listaBorrar = new ArrayList();
        for(DominioHorarioEmpleados dom: getListaHorarioEmpleados()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioHorarioEmpleados obj = (DominioHorarioEmpleados)serviceHorarioEmpleados.deleteAll(listaBorrar);
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
    
    public void showHorarioEmpleados(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioHorarioEmpleados)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioHorarioEmpleados getById(DominioHorarioEmpleados obj) {
        return (DominioHorarioEmpleados)serviceHorarioEmpleados.getById(obj);
    }
    
    public void verRegistroAsistencia(DominioUsers user) {
        this.user = user;
        llenaLista();
    }
}
