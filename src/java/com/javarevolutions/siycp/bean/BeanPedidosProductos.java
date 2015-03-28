/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanPedidosProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioPedidos;
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfacePedidosProductos;
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

public class BeanPedidosProductos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanPedidosProductos.class);
    private DominioPedidosProductos dominio;
    private LazyDataModel<DominioPedidosProductos> listaModel;
    private IfacePedidosProductos servicePedidosProductos;
    private List<DominioPedidosProductos> listaPedidosProductos;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioPedidosProductos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioPedidosProductos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioPedidosProductos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioPedidosProductos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the servicePedidosProductos
     */
    public IfacePedidosProductos getServicePedidosProductos() {
        return servicePedidosProductos;
    }

    /**
     * @param servicePedidosProductos the servicePedidosProductos to set
     */
    public void setServicePedidosProductos(IfacePedidosProductos servicePedidosProductos) {
        this.servicePedidosProductos = servicePedidosProductos;
    }

    /**
     * @return the listaPedidosProductos
     */
    public List<DominioPedidosProductos> getListaPedidosProductos() {
        return listaPedidosProductos;
    }

    /**
     * @param listaPedidosProductos the listaPedidosProductos to set
     */
    public void setListaPedidosProductos(List<DominioPedidosProductos> listaPedidosProductos) {
        this.listaPedidosProductos = listaPedidosProductos;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioPedidosProductos>() {
            @Override
            public List<DominioPedidosProductos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioPedidosProductos> result = new ArrayList();
                try {
                    DominioPedidosProductos auxDominio = new DominioPedidosProductos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = servicePedidosProductos.getListaPagination(auxDominio);
                    setListaPedidosProductos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(servicePedidosProductos.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioPedidosProductos obj = (DominioPedidosProductos)servicePedidosProductos.save(dominio);
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
        DominioPedidosProductos obj = (DominioPedidosProductos)servicePedidosProductos.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioPedidosProductos obj = ((DominioPedidosProductos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioPedidosProductos)servicePedidosProductos.update(obj);
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
        if(dominio.getIdPedidoProducto() != null) {
            DominioPedidosProductos obj = (DominioPedidosProductos)servicePedidosProductos.getById(dominio);
            obj = (DominioPedidosProductos)servicePedidosProductos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdPedidoProducto' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioPedidosProductos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioPedidosProductos)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioPedidosProductos> listaBorrar = new ArrayList();
        for(DominioPedidosProductos dom: getListaPedidosProductos()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioPedidosProductos obj = (DominioPedidosProductos)servicePedidosProductos.deleteAll(listaBorrar);
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
    
    public void showPedidosProductos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioPedidosProductos)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioPedidosProductos getById(DominioPedidosProductos obj) {
        return (DominioPedidosProductos)servicePedidosProductos.getById(obj);
    }
    
    public DominioPedidosProductos saveAllProdsPedidos(List<DominioPedidosProductos> lista, DominioPedidos pedido) {
        return servicePedidosProductos.saveAllProdsPedidos(lista, pedido);
    }
}
