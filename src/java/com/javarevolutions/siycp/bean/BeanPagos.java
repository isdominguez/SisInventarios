/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BeanPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioPagos;
import com.javarevolutions.siycp.dominio.DominioSeguimientoPagos;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfacePagos;
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

public class BeanPagos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanPagos.class);
    private DominioPagos dominio;
    private LazyDataModel<DominioPagos> listaModel;
    private IfacePagos servicePagos;
    private List<DominioPagos> listaPagos;
    private DominioUsuarios usuarios;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioPagos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioPagos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioPagos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioPagos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the servicePagos
     */
    public IfacePagos getServicePagos() {
        return servicePagos;
    }

    /**
     * @param servicePagos the servicePagos to set
     */
    public void setServicePagos(IfacePagos servicePagos) {
        this.servicePagos = servicePagos;
    }

    /**
     * @return the listaPagos
     */
    public List<DominioPagos> getListaPagos() {
        return listaPagos;
    }

    /**
     * @param listaPagos the listaPagos to set
     */
    public void setListaPagos(List<DominioPagos> listaPagos) {
        this.listaPagos = listaPagos;
    }

    /**
     * @return the usuarios
     */
    public DominioUsuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(DominioUsuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    public void llenaPagos(DominioSeguimientoPagos obj) {
        dominio.setSeguimientoPagos(obj);
        setUsuarios(obj.getUsuarios());
        llenaLista();
    }
    
    public void nuevoPago(DominioSeguimientoPagos obj) {
        dominio.setSeguimientoPagos(obj);
        setTituloPantalla(Global.getPropRB("com.jr.props.menu.cat.pagos.nuevo_pago"));
        setUsuarios(obj.getUsuarios());
        dominio.setCantidadPago(null);
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioPagos>() {
            @Override
            public List<DominioPagos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioPagos> result = new ArrayList();
                try {
                    DominioPagos auxDominio = new DominioPagos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    auxDominio.setSeguimientoPagos(dominio.getSeguimientoPagos());
                    result = servicePagos.getListaPagination(auxDominio);
                    setListaPagos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(servicePagos.getRowCount(dominio));
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        dominio.setFechaPago(new java.util.Date());
        DominioPagos obj = (DominioPagos)servicePagos.save(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.menu.cat.pagos.header_exitoso"), 
                    Global.getPropRB("com.jr.props.menu.cat.pagos.exitoso"));
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
        DominioPagos obj = (DominioPagos)servicePagos.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioPagos obj = ((DominioPagos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioPagos)servicePagos.update(obj);
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
        if(dominio.getIdPago() != null) {
            DominioPagos obj = (DominioPagos)servicePagos.getById(dominio);
            obj = (DominioPagos)servicePagos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdPago' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioPagos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioPagos)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioPagos> listaBorrar = new ArrayList();
        for(DominioPagos dom: getListaPagos()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioPagos obj = (DominioPagos)servicePagos.deleteAll(listaBorrar);
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
    
    public void showPagos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioPagos)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioPagos getById(DominioPagos obj) {
        return (DominioPagos)servicePagos.getById(obj);
    }
}
