/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceProductos;
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

public class BeanProductos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanProductos.class);
    private DominioProductos dominio;
    private LazyDataModel<DominioProductos> listaModel;
    private IfaceProductos serviceProductos;
    private List<DominioProductos> listaProductos;
    private int auxPosicion;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioProductos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioProductos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioProductos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioProductos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceProductos
     */
    public IfaceProductos getServiceProductos() {
        return serviceProductos;
    }

    /**
     * @param serviceProductos the serviceProductos to set
     */
    public void setServiceProductos(IfaceProductos serviceProductos) {
        this.serviceProductos = serviceProductos;
    }

    /**
     * @return the listaProductos
     */
    public List<DominioProductos> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(List<DominioProductos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the auxPosicion
     */
    public int getAuxPosicion() {
        return auxPosicion;
    }

    /**
     * @param auxPosicion the auxPosicion to set
     */
    public void setAuxPosicion(int auxPosicion) {
        this.auxPosicion = auxPosicion;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioProductos>() {
            @Override
            public List<DominioProductos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioProductos> result = new ArrayList();
                try {
                    DominioProductos auxDominio = new DominioProductos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    auxDominio.setTipo("p");
                    auxDominio.setEntidadPersistir("Productos");
                    result = serviceProductos.getListaPagination(auxDominio);
                    setListaProductos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            dominio.setTipo("p");
            listaModel.setRowCount(serviceProductos.getRowsByTipo(dominio));
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        dominio.setTipo("p");
        dominio.setCantidad(new Long(0));
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioProductos obj = (DominioProductos)serviceProductos.save(dominio);
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
        DominioProductos obj = (DominioProductos)serviceProductos.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
            
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioProductos obj = ((DominioProductos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioProductos)serviceProductos.update(obj);
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
        if(dominio.getIdProducto() != null) {
            DominioProductos obj = (DominioProductos)serviceProductos.getById(dominio);
            obj = (DominioProductos)serviceProductos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdProducto' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioProductos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioProductos)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioProductos> listaBorrar = new ArrayList();
        for(DominioProductos dom: getListaProductos()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioProductos obj = (DominioProductos)serviceProductos.deleteAll(listaBorrar);
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
    
    public void showProductos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioProductos)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioProductos getById(DominioProductos obj) {
        return (DominioProductos)serviceProductos.getById(obj);
    }
}
