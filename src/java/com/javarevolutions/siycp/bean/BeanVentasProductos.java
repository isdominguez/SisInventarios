/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BeanVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceVentasProductos;
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

public class BeanVentasProductos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanVentasProductos.class);
    private DominioVentasProductos dominio;
    private LazyDataModel<DominioVentasProductos> listaModel;
    private IfaceVentasProductos serviceVentasProductos;
    private List<DominioVentasProductos> listaVentasProductos;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioVentasProductos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioVentasProductos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioVentasProductos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioVentasProductos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceVentasProductos
     */
    public IfaceVentasProductos getServiceVentasProductos() {
        return serviceVentasProductos;
    }

    /**
     * @param serviceVentasProductos the serviceVentasProductos to set
     */
    public void setServiceVentasProductos(IfaceVentasProductos serviceVentasProductos) {
        this.serviceVentasProductos = serviceVentasProductos;
    }

    /**
     * @return the listaVentasProductos
     */
    public List<DominioVentasProductos> getListaVentasProductos() {
        return listaVentasProductos;
    }

    /**
     * @param listaVentasProductos the listaVentasProductos to set
     */
    public void setListaVentasProductos(List<DominioVentasProductos> listaVentasProductos) {
        this.listaVentasProductos = listaVentasProductos;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioVentasProductos>() {
            @Override
            public List<DominioVentasProductos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioVentasProductos> result = new ArrayList();
                try {
                    DominioVentasProductos auxDominio = new DominioVentasProductos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceVentasProductos.getListaPagination(auxDominio);
                    setListaVentasProductos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceVentasProductos.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioVentasProductos obj = (DominioVentasProductos)serviceVentasProductos.save(dominio);
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
        DominioVentasProductos obj = (DominioVentasProductos)serviceVentasProductos.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioVentasProductos obj = ((DominioVentasProductos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioVentasProductos)serviceVentasProductos.update(obj);
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
        if(dominio.getIdVentaProducto() != null) {
            DominioVentasProductos obj = (DominioVentasProductos)serviceVentasProductos.getById(dominio);
            obj = (DominioVentasProductos)serviceVentasProductos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdVentaProducto' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioVentasProductos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioVentasProductos)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioVentasProductos> listaBorrar = new ArrayList();
        for(DominioVentasProductos dom: getListaVentasProductos()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioVentasProductos obj = (DominioVentasProductos)serviceVentasProductos.deleteAll(listaBorrar);
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
    
    public void showVentasProductos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioVentasProductos)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioVentasProductos getById(DominioVentasProductos obj) {
        return (DominioVentasProductos)serviceVentasProductos.getById(obj);
    }
}
