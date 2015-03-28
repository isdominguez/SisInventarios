/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanTiposMedidas.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioTiposMedidas;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceTiposMedidas;
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

public class BeanTiposMedidas extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanTiposMedidas.class);
    private DominioTiposMedidas dominio;
    private LazyDataModel<DominioTiposMedidas> listaModel;
    private IfaceTiposMedidas serviceTiposMedidas;
    private List<DominioTiposMedidas> listaTiposMedidas;
    private List<DominioTiposMedidas> lista;

    /**
     * @return the lista
     */
    public List<DominioTiposMedidas> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<DominioTiposMedidas> lista) {
        this.lista = lista;
    }
    
    @PostConstruct
    public void llenaTiposMedidas() {
        try {
            setLista(serviceTiposMedidas.getLista());
        } catch(Exception e) {
            log.error("ERROR llenaTiposMedidas : "+e);
        }
    }
    
    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioTiposMedidas> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioTiposMedidas> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioTiposMedidas getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioTiposMedidas dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceTiposMedidas
     */
    public IfaceTiposMedidas getServiceTiposMedidas() {
        return serviceTiposMedidas;
    }

    /**
     * @param serviceTiposMedidas the serviceTiposMedidas to set
     */
    public void setServiceTiposMedidas(IfaceTiposMedidas serviceTiposMedidas) {
        this.serviceTiposMedidas = serviceTiposMedidas;
    }

    /**
     * @return the listaTiposMedidas
     */
    public List<DominioTiposMedidas> getListaTiposMedidas() {
        return listaTiposMedidas;
    }

    /**
     * @param listaTiposMedidas the listaTiposMedidas to set
     */
    public void setListaTiposMedidas(List<DominioTiposMedidas> listaTiposMedidas) {
        this.listaTiposMedidas = listaTiposMedidas;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioTiposMedidas>() {
            @Override
            public List<DominioTiposMedidas> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioTiposMedidas> result = new ArrayList();
                try {
                    DominioTiposMedidas auxDominio = new DominioTiposMedidas();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceTiposMedidas.getListaPagination(auxDominio);
                    setListaTiposMedidas(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceTiposMedidas.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioTiposMedidas obj = (DominioTiposMedidas)serviceTiposMedidas.save(dominio);
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
        DominioTiposMedidas obj = (DominioTiposMedidas)serviceTiposMedidas.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioTiposMedidas obj = ((DominioTiposMedidas)event.getObject());
        int contador = obj.getCont();
        obj = (DominioTiposMedidas)serviceTiposMedidas.update(obj);
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
        if(dominio.getIdTipoMedida() != null) {
            DominioTiposMedidas obj = (DominioTiposMedidas)serviceTiposMedidas.getById(dominio);
            obj = (DominioTiposMedidas)serviceTiposMedidas.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdTipoMedida' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioTiposMedidas();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioTiposMedidas)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioTiposMedidas> listaBorrar = new ArrayList();
        for(DominioTiposMedidas dom: getListaTiposMedidas()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioTiposMedidas obj = (DominioTiposMedidas)serviceTiposMedidas.deleteAll(listaBorrar);
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
    
    public void showTiposMedidas(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioTiposMedidas)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioTiposMedidas getById(DominioTiposMedidas obj) {
        return (DominioTiposMedidas)serviceTiposMedidas.getById(obj);
    }
}
