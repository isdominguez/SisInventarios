/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanProveedoresProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioProveedoresProductos;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceProveedoresProductos;
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

public class BeanProveedoresProductos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanProveedoresProductos.class);
    private DominioProveedoresProductos dominio;
    private LazyDataModel<DominioProveedoresProductos> listaModel;
    private IfaceProveedoresProductos serviceProveedoresProductos;
    private List<DominioProveedoresProductos> listaProveedoresProductos;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioProveedoresProductos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioProveedoresProductos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioProveedoresProductos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioProveedoresProductos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceProveedoresProductos
     */
    public IfaceProveedoresProductos getServiceProveedoresProductos() {
        return serviceProveedoresProductos;
    }

    /**
     * @param serviceProveedoresProductos the serviceProveedoresProductos to set
     */
    public void setServiceProveedoresProductos(IfaceProveedoresProductos serviceProveedoresProductos) {
        this.serviceProveedoresProductos = serviceProveedoresProductos;
    }

    /**
     * @return the listaProveedoresProductos
     */
    public List<DominioProveedoresProductos> getListaProveedoresProductos() {
        return listaProveedoresProductos;
    }

    /**
     * @param listaProveedoresProductos the listaProveedoresProductos to set
     */
    public void setListaProveedoresProductos(List<DominioProveedoresProductos> listaProveedoresProductos) {
        this.listaProveedoresProductos = listaProveedoresProductos;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioProveedoresProductos>() {
            @Override
            public List<DominioProveedoresProductos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioProveedoresProductos> result = new ArrayList();
                try {
                    DominioProveedoresProductos auxDominio = new DominioProveedoresProductos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceProveedoresProductos.getListaPagination(auxDominio);
                    setListaProveedoresProductos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceProveedoresProductos.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioProveedoresProductos obj = (DominioProveedoresProductos)serviceProveedoresProductos.save(dominio);
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
        DominioProveedoresProductos obj = (DominioProveedoresProductos)serviceProveedoresProductos.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioProveedoresProductos obj = ((DominioProveedoresProductos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioProveedoresProductos)serviceProveedoresProductos.update(obj);
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
        if(dominio.getIdProveedorProducto() != null) {
            DominioProveedoresProductos obj = (DominioProveedoresProductos)serviceProveedoresProductos.getById(dominio);
            obj = (DominioProveedoresProductos)serviceProveedoresProductos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdProveedorProducto' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioProveedoresProductos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioProveedoresProductos)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioProveedoresProductos> listaBorrar = new ArrayList();
        for(DominioProveedoresProductos dom: getListaProveedoresProductos()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioProveedoresProductos obj = (DominioProveedoresProductos)serviceProveedoresProductos.deleteAll(listaBorrar);
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
    
    public void showProveedoresProductos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioProveedoresProductos)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioProveedoresProductos getById(DominioProveedoresProductos obj) {
        return (DominioProveedoresProductos)serviceProveedoresProductos.getById(obj);
    }
}
