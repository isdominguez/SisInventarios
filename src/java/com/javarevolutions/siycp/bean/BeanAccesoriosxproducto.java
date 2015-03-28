/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanAccesoriosxproducto.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioAccesoriosxproducto;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceAccesoriosxproducto;
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

public class BeanAccesoriosxproducto extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanAccesoriosxproducto.class);
    private DominioAccesoriosxproducto dominio;
    private LazyDataModel<DominioAccesoriosxproducto> listaModel;
    private IfaceAccesoriosxproducto serviceAccesoriosxproducto;
    private List<DominioAccesoriosxproducto> listaAccesoriosxproducto;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioAccesoriosxproducto> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioAccesoriosxproducto> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioAccesoriosxproducto getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioAccesoriosxproducto dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceAccesoriosxproducto
     */
    public IfaceAccesoriosxproducto getServiceAccesoriosxproducto() {
        return serviceAccesoriosxproducto;
    }

    /**
     * @param serviceAccesoriosxproducto the serviceAccesoriosxproducto to set
     */
    public void setServiceAccesoriosxproducto(IfaceAccesoriosxproducto serviceAccesoriosxproducto) {
        this.serviceAccesoriosxproducto = serviceAccesoriosxproducto;
    }

    /**
     * @return the listaAccesoriosxproducto
     */
    public List<DominioAccesoriosxproducto> getListaAccesoriosxproducto() {
        return listaAccesoriosxproducto;
    }

    /**
     * @param listaAccesoriosxproducto the listaAccesoriosxproducto to set
     */
    public void setListaAccesoriosxproducto(List<DominioAccesoriosxproducto> listaAccesoriosxproducto) {
        this.listaAccesoriosxproducto = listaAccesoriosxproducto;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioAccesoriosxproducto>() {
            @Override
            public List<DominioAccesoriosxproducto> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioAccesoriosxproducto> result = new ArrayList();
                try {
                    DominioAccesoriosxproducto auxDominio = new DominioAccesoriosxproducto();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceAccesoriosxproducto.getListaPagination(auxDominio);
                    setListaAccesoriosxproducto(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceAccesoriosxproducto.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioAccesoriosxproducto obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.save(dominio);
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
        DominioAccesoriosxproducto obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioAccesoriosxproducto obj = ((DominioAccesoriosxproducto)event.getObject());
        int contador = obj.getCont();
        obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.update(obj);
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
        if(dominio.getIdAccesorioxproducto() != null) {
            DominioAccesoriosxproducto obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.getById(dominio);
            obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdAccesorioxproducto' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioAccesoriosxproducto();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioAccesoriosxproducto)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioAccesoriosxproducto> listaBorrar = new ArrayList();
        for(DominioAccesoriosxproducto dom: getListaAccesoriosxproducto()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioAccesoriosxproducto obj = (DominioAccesoriosxproducto)serviceAccesoriosxproducto.deleteAll(listaBorrar);
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
    
    public void showAccesoriosxproducto(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioAccesoriosxproducto)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioAccesoriosxproducto getById(DominioAccesoriosxproducto obj) {
        return (DominioAccesoriosxproducto)serviceAccesoriosxproducto.getById(obj);
    }
}
