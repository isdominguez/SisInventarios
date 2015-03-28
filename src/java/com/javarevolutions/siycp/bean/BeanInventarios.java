/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanInventarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceInventarios;
import com.javarevolutions.siycp.utils.JRUtil;
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

public class BeanInventarios extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanInventarios.class);
    private DominioInventarios dominio;
    private LazyDataModel<DominioInventarios> listaModel;
    private IfaceInventarios serviceInventarios;
    private List<DominioInventarios> listaInventarios;
    private List<DominioInventarios> copiaListaInventarios;

    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioInventarios> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioInventarios> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioInventarios getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioInventarios dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceInventarios
     */
    public IfaceInventarios getServiceInventarios() {
        return serviceInventarios;
    }

    /**
     * @param serviceInventarios the serviceInventarios to set
     */
    public void setServiceInventarios(IfaceInventarios serviceInventarios) {
        this.serviceInventarios = serviceInventarios;
    }

    /**
     * @return the listaInventarios
     */
    public List<DominioInventarios> getListaInventarios() {
        return listaInventarios;
    }

    /**
     * @param listaInventarios the listaInventarios to set
     */
    public void setListaInventarios(List<DominioInventarios> listaInventarios) {
        this.listaInventarios = listaInventarios;
    }

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioInventarios>() {
            @Override
            public List<DominioInventarios> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioInventarios> result = new ArrayList();
                try {
                    DominioInventarios auxDominio = new DominioInventarios();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceInventarios.getListaPagination(auxDominio);
                    setListaInventarios(result);
                    //Creando copia para después compararla a la hora de actualizar los inventarios
                    copiaListaInventarios = new ArrayList<>();
                    for(DominioInventarios invAux: getListaInventarios()) {
                        DominioInventarios nuevo = new DominioInventarios();
                        JRUtil.copyProperties(nuevo, invAux);
                        copiaListaInventarios.add(nuevo);
                    }
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceInventarios.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    @Override
    public void save(ActionEvent ae) {
        setAccion("save");
        RequestContext context = RequestContext.getCurrentInstance();
        DominioInventarios obj = (DominioInventarios)serviceInventarios.save(dominio);
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
        DominioInventarios obj = (DominioInventarios)serviceInventarios.update(dominio);
        if(obj.isStatus()) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
        } else {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), obj.getMsg());
            log.error("ERROR : "+obj.getMsg());
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioInventarios obj = ((DominioInventarios)event.getObject());
        int contador = obj.getCont();
        obj = (DominioInventarios)serviceInventarios.update(obj);
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
        if(dominio.getIdInventario() != null) {
            DominioInventarios obj = (DominioInventarios)serviceInventarios.getById(dominio);
            obj = (DominioInventarios)serviceInventarios.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdInventario' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.new"));
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioInventarios();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
        } else {
            setTituloPantalla(Global.getPropRB("com.jr.props.registro.update"));
            setShowSave(false);
            setShowUpdate(true);
            dominio = (DominioInventarios)obj;
        }
    }
    
    @Override
    public void deleteAllSelect(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        int cont = 0;
        List<DominioInventarios> listaBorrar = new ArrayList();
        for(DominioInventarios dom: getListaInventarios()) {
            if(dom.isSelect()) {
                cont++;
                listaBorrar.add(dom);
            }
        }
        DominioInventarios obj = (DominioInventarios)serviceInventarios.deleteAll(listaBorrar);
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
    
    public void showInventarios(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.registro.show"));
        dominio = (DominioInventarios)se.getObject();
        dominio.setDisabled(true);
    }
    
    public DominioInventarios getById(DominioInventarios obj) {
        return (DominioInventarios)serviceInventarios.getById(obj);
    }
    
    public void actualizarInventario(ActionEvent ae) {
        RequestContext context = RequestContext.getCurrentInstance();
        try {
            boolean error = true;
            if(!copiaListaInventarios.equals(listaInventarios)) {
                error = false;
            }
            if(!error) {
                dominio = serviceInventarios.updateCantidades(listaInventarios);
                context.addCallbackParam("statusTransaction", dominio.isStatus());
                if(!dominio.isStatus()) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), dominio.getMsg());
                    context.addCallbackParam("errorTransaction", dominio.getMsg());
                } else {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update.success"), 
                            Global.getPropRB("com.jr.props.menu.cat.inventarios.update.success"));
                    context.addCallbackParam("errorTransaction", 
                            Global.getPropRB("com.jr.props.menu.cat.inventarios.update.success"));
                }
            } else {
                String msgError = Global.getPropRB("com.jr.props.menu.cat.inventarios.update.success");
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update.success"), msgError);
                context.addCallbackParam("statusTransaction", false);
                context.addCallbackParam("errorTransaction", msgError);
            }
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            context.addCallbackParam("statusTransaction", false);
            context.addCallbackParam("errorTransaction", "Error in actualizarInventario : "+e);
        }
    }
}
