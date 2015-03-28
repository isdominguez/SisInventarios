/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanUsuarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceUsuarios;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class BeanUsuarios extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanUsuarios.class);
    private DominioUsuarios dominio;
    private LazyDataModel<DominioUsuarios> listaModel;
    private IfaceUsuarios serviceUsuarios;
    private List<DominioUsuarios> listaUsuarios;
    private List<DominioUsuarios> lista;

    /**
     * @return the lista
     */
    public List<DominioUsuarios> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<DominioUsuarios> lista) {
        this.lista = lista;
    }

    @PostConstruct
    public void llenaUsuarios() {
        try {
            dominio = new DominioUsuarios();
            dominio.setTipoPersona("u");
            List<DominioUsuarios> auxLista = new ArrayList<>();
            auxLista.add(null);
            auxLista.addAll(serviceUsuarios.getListaByTipo(dominio));
            setLista(auxLista);
        } catch(Exception e) {
            log.error("ERROR llenaUsuarios : "+e);
        }
    }
    
    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioUsuarios> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioUsuarios> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioUsuarios getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioUsuarios dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceUsuarios
     */
    public IfaceUsuarios getServiceUsuarios() {
        return serviceUsuarios;
    }

    /**
     * @param serviceUsuarios the serviceUsuarios to set
     */
    public void setServiceUsuarios(IfaceUsuarios serviceUsuarios) {
        this.serviceUsuarios = serviceUsuarios;
    }

    /**
     * @return the listaUsuarios
     */
    public List<DominioUsuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    /**
     * @param listaUsuarios the listaUsuarios to set
     */
    public void setListaUsuarios(List<DominioUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioUsuarios>() {
            @Override
            public List<DominioUsuarios> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioUsuarios> result = new ArrayList();
                try {
                    DominioUsuarios auxDominio = new DominioUsuarios();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    auxDominio.setTipoPersona("u");
                    result = serviceUsuarios.getListaPagination(auxDominio);
                    setListaUsuarios(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            dominio.setTipoPersona("u");
            listaModel.setRowCount(serviceUsuarios.getRowsByTipo(dominio));
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        dominio.setTipoPersona("u");
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioUsuarios obj = (DominioUsuarios)serviceUsuarios.save(dominio);
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
        setAccion("update");
        DominioUsuarios obj = (DominioUsuarios)serviceUsuarios.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioUsuarios obj = ((DominioUsuarios)event.getObject());
        int contador = obj.getCont();
        obj = (DominioUsuarios)serviceUsuarios.update(obj);
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
        if(dominio.getIdUsuario() != null) {
            DominioUsuarios obj = (DominioUsuarios)serviceUsuarios.getById(dominio);
            obj = (DominioUsuarios)serviceUsuarios.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdUsuario' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioUsuarios();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioUsuarios)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioUsuarios> listaBorrar = new ArrayList();
        for(DominioUsuarios dom: getListaUsuarios()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioUsuarios obj = (DominioUsuarios)serviceUsuarios.deleteAll(listaBorrar);
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
    
    public void showUsuarios(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioUsuarios)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioUsuarios getById(DominioUsuarios obj) {
        return (DominioUsuarios)serviceUsuarios.getById(obj);
    }
}
