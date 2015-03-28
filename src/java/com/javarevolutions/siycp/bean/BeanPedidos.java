/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanPedidos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioGraficaPedido;
import com.javarevolutions.siycp.dominio.DominioPedidos;
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfacePedidos;
import com.javarevolutions.siycp.service.IfacePedidosProductos;
import com.javarevolutions.siycp.service.IfaceProductos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class BeanPedidos extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanPedidos.class);
    private DominioPedidos dominio;
    private LazyDataModel<DominioPedidos> listaModel;
    private IfacePedidos servicePedidos;
    private IfacePedidosProductos servicePedidosProductos;
    private List<DominioPedidos> listaPedidos;
    private List<DominioProductos> listaProductos;
    private DominioUsuarios proveedor;
    private LazyDataModel<DominioProductos> listaModelProductos;
    private IfaceProductos serviceProductos;
    private List<DominioProductos> auxListProductos;
    private double totalVenta;
    private BeanCorteCaja beanCorteCaja;
    private boolean proveedorEditable;
    
    @PostConstruct
    public void llenaFechaPedidos() {
        estableceInit();
    }
    
    private void estableceInit() {
        dominio.setFechaPedido(new java.util.Date());
        dominio.setFechaFin(dominio.getFechaPedido());
        java.util.Date fechaInicio = new java.util.Date(dominio.getFechaFin().getTime()-
                (7 * 24 * 60 * 60 * 1000));
        dominio.setFechaInicio(fechaInicio);
        beanCorteCaja.setShowGraficaXDia(false);
    }
    
    public void changeProveedor() {
        llenaListaProductos();
        listaProductos = new ArrayList();
    }
    
    public void llenaListaProductos() {
        listaModelProductos = new LazyDataModel<DominioProductos>() {
            @Override
            public List<DominioProductos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioProductos> result = new ArrayList();
                try {
                    DominioProductos auxDominio = new DominioProductos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    auxDominio.setTipo("p");
                    auxDominio.setUsuarios(getProveedor());
                    result = getServiceProductos().getProductosPaginationByProveedor(auxDominio);
                    setAuxListProductos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            DominioProductos aux = new DominioProductos();
            aux.setTipo("p");
            aux.setUsuarios(getProveedor());
            listaModelProductos.setRowCount(getServiceProductos().getRowsByProveedor(aux));
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModelProductos.setPageSize(10);
    }

    /**
     * @return the proveedor
     */
    public DominioUsuarios getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(DominioUsuarios proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the listaModelProductos
     */
    public LazyDataModel<DominioProductos> getListaModelProductos() {
        return listaModelProductos;
    }

    /**
     * @param listaModelProductos the listaModelProductos to set
     */
    public void setListaModelProductos(LazyDataModel<DominioProductos> listaModelProductos) {
        this.listaModelProductos = listaModelProductos;
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
     * @return the listaModel
     */
    public LazyDataModel<DominioPedidos> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioPedidos> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioPedidos getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioPedidos dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the servicePedidos
     */
    public IfacePedidos getServicePedidos() {
        return servicePedidos;
    }

    /**
     * @param servicePedidos the servicePedidos to set
     */
    public void setServicePedidos(IfacePedidos servicePedidos) {
        this.servicePedidos = servicePedidos;
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
     * @return the auxListProductos
     */
    public List<DominioProductos> getAuxListProductos() {
        return auxListProductos;
    }

    /**
     * @param auxListProductos the auxListProductos to set
     */
    public void setAuxListProductos(List<DominioProductos> auxListProductos) {
        this.auxListProductos = auxListProductos;
    }

    /**
     * @return the totalVenta
     */
    public double getTotalVenta() {
        return totalVenta;
    }

    /**
     * @param totalVenta the totalVenta to set
     */
    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    /**
     * @return the beanCorteCaja
     */
    public BeanCorteCaja getBeanCorteCaja() {
        return beanCorteCaja;
    }

    /**
     * @param beanCorteCaja the beanCorteCaja to set
     */
    public void setBeanCorteCaja(BeanCorteCaja beanCorteCaja) {
        this.beanCorteCaja = beanCorteCaja;
    }

    /**
     * @return the proveedorEditable
     */
    public boolean isProveedorEditable() {
        return proveedorEditable;
    }

    /**
     * @param proveedorEditable the proveedorEditable to set
     */
    public void setProveedorEditable(boolean proveedorEditable) {
        this.proveedorEditable = proveedorEditable;
    }

    /**
     * @return the listaPedidos
     */
    public List<DominioPedidos> getListaPedidos() {
        return listaPedidos;
    }

    /**
     * @param listaPedidos the listaPedidos to set
     */
    public void setListaPedidos(List<DominioPedidos> listaPedidos) {
        this.listaPedidos = listaPedidos;
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

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioPedidos>() {
            @Override
            public List<DominioPedidos> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioPedidos> result = new ArrayList();
                try {
                    DominioPedidos auxDominio = new DominioPedidos();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = servicePedidos.getListaPagination(auxDominio);
                    setListaPedidos(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(servicePedidos.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
    }
    
    private List<DominioGraficaPedido> listaGraficaPedidos;
    private void executeTotalVenta(List<DominioPedidos> allPedidos) {
        try {
            listaGraficaPedidos = new ArrayList();
            for(DominioPedidos obj: allPedidos) {
                DominioPedidosProductos auxDPP = new DominioPedidosProductos();
                auxDPP.setPedidos(obj);
                List<DominioPedidosProductos> listaBorrar = servicePedidosProductos.getListaBorrar(auxDPP);
                double totalXPedido = 0;
                for(DominioPedidosProductos aux: listaBorrar) {
                    totalVenta += (aux.getCantidad() * aux.getProductos().getCosto());
                    totalXPedido += (aux.getCantidad() * aux.getProductos().getCosto());
                }
                DominioGraficaPedido aux = new DominioGraficaPedido();
                aux.setIdPedido(obj.getIdPedido());
                aux.setTotalVenta(totalXPedido);
                listaGraficaPedidos.add(aux);
            }
            //beanCorteCaja.creaGraficaXDia(listaGraficaPedidos);
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void getPedidosXDia(ActionEvent ae) {
        RequestContext contextAux = RequestContext.getCurrentInstance();
        if(dominio.getFechaPedido().getTime() > Global.getFechaHoy().getTime()) {
            contextAux.addCallbackParam("statusTransaction", false);
            contextAux.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.menu.corte.fecha_pedido_erronea"));
            Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                    Global.getPropRB("com.jr.props.menu.corte.fecha_pedido_erronea"));
            return;
        }
        totalVenta = 0;
        try {
            listaPedidos = servicePedidos.getPedidosByDia(dominio);
            if(listaPedidos.size() > 0) {
                executeTotalVenta(listaPedidos);
                contextAux.addCallbackParam("statusTransaction", true);
            } else {
                contextAux.addCallbackParam("statusTransaction", false);
                contextAux.addCallbackParam("errorTransaction", 
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.pedidos"));
                Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.pedidos"));
                beanCorteCaja.setShowGraficaXDia(false);
            }
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void getPedidosXSemana(ActionEvent ae) {
        RequestContext contextAux = RequestContext.getCurrentInstance();
        if(dominio.getFechaFin().getTime() < dominio.getFechaInicio().getTime()
                || dominio.getFechaFin().getTime() > Global.getFechaHoy().getTime()) {
            contextAux.addCallbackParam("statusTransaction", false);
            contextAux.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.menu.corte.fechas_erroneas"));
            Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                    Global.getPropRB("com.jr.props.menu.corte.fechas_erroneas"));
            return;
        }
        totalVenta = 0;
        try {
            listaPedidos = servicePedidos.getPedidosByFechas(dominio);
            if(listaPedidos.size() > 0) {
                executeTotalVenta(listaPedidos);
                contextAux.addCallbackParam("statusTransaction", true);
            } else {
                contextAux.addCallbackParam("statusTransaction", false);
                contextAux.addCallbackParam("errorTransaction", 
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.pedidos"));
                Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.pedidos"));
                beanCorteCaja.setShowGraficaXDia(false);
            }
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    @Override
    public void save(ActionEvent ae) {
        boolean status = false;
        RequestContext context = RequestContext.getCurrentInstance();
        if(getProveedor().getIdUsuario() <= 0) {
            String error = "Debes de seleccionar un proveedor";
            Global.addMsg("Selecciona un proveedor", error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        }
        else if(getListaProductos().size() <= 0) {
            String error = Global.getPropRB("com.jr.props.menu.cat.pedidos.null");
            Global.addMsg(Global.getPropRB("com.jr.props.menu.cat.pedidos.error"), error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        } else {
            dominio.setFechaPedido(new java.util.Date());
            dominio.setHoraPedido(dominio.getFechaPedido());
            dominio.setUsuarios(proveedor);
            setAccion("save");
            List<DominioPedidosProductos> auxPedPro = new ArrayList();
            for(DominioProductos prods: listaProductos) {
                DominioPedidosProductos nuevo = new DominioPedidosProductos();
                nuevo.setPedidos(dominio);
                nuevo.setProductos(prods);
                nuevo.setCantidad(prods.getCantidad());
                auxPedPro.add(nuevo);
            }
            DominioPedidosProductos dom = servicePedidosProductos.saveAllProdsPedidos(auxPedPro, dominio);
            if(dom.isStatus()) {
                status = true;
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.insert"), Global.getPropRB("com.jr.props.msg.registro.el")+" '1' "+Global.getPropRB("com.jr.props.msg.registro.el.insert"));
                context.addCallbackParam("mensajeTransaction", Global.getPropRB("com.jr.props.msg.registro.el")+" '1' "+Global.getPropRB("com.jr.props.msg.registro.el.insert"));
            } else {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), dom.getMsg());
                context.addCallbackParam("errorTransaction", dom.getMsg());
                log.error("ERROR : "+dom.getMsg());
            }
            context.addCallbackParam("statusTransaction", status);
        }
    }
    
    @Override
    public void updateRow(ActionEvent ae) {
        boolean status = false;
        RequestContext context = RequestContext.getCurrentInstance();
        setAccion("update");
        int contador = dominio.getCont();
        if(getProveedor().getIdUsuario() <= 0) {
            String error = "Debes de seleccionar un proveedor";
            Global.addMsg("Selecciona un proveedor", error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        }
        else if(getListaProductos().size() <= 0) {
            String error = Global.getPropRB("com.jr.props.menu.cat.pedidos.null");
            Global.addMsg(Global.getPropRB("com.jr.props.menu.cat.pedidos.error"), error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        } else {
            String condiciones = dominio.getCondiciones();
            dominio = (DominioPedidos)servicePedidos.getById(dominio);
            dominio.setFechaModificacion(new java.util.Date());
            dominio.setUsuarios(proveedor);
            dominio.setCondiciones(condiciones);
            DominioPedidosProductos auxDPP = new DominioPedidosProductos();
            auxDPP.setPedidos(dominio);            
            List<DominioPedidosProductos> listaBorrar = null;
            try {
                listaBorrar = servicePedidosProductos.getListaBorrar(auxDPP);
            } catch(Exception e)  {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                context.addCallbackParam("errorTransaction", "ERROR : "+e);
                log.error("ERROR : "+e);
            }
            if(listaBorrar != null) {
                List<DominioPedidosProductos> auxPedPro = new ArrayList();
                for(DominioProductos prods: listaProductos) {
                    DominioPedidosProductos nuevo = new DominioPedidosProductos();
                    nuevo.setPedidos(dominio);
                    nuevo.setProductos(prods);
                    nuevo.setCantidad(prods.getCantidad());
                    auxPedPro.add(nuevo);
                }
                DominioPedidosProductos response = servicePedidosProductos.
                        updateAllProdsPedidos(auxPedPro, listaBorrar,dominio);
                if(response.isStatus()) {
                    status = true;
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
                    context.addCallbackParam("mensajeTransaction", Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
                } else {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), response.getMsg());
                    context.addCallbackParam("errorTransaction", response.getMsg());
                    log.error("ERROR : "+response.getMsg());
                }
            }
            context.addCallbackParam("statusTransaction", status);
        }
    }
    
    @Override
    public void update(RowEditEvent event) {
        DominioPedidos obj = ((DominioPedidos)event.getObject());
        int contador = obj.getCont();
        obj = (DominioPedidos)servicePedidos.update(obj);
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
        if(dominio.getIdPedido() != null) {
            DominioPedidos obj = (DominioPedidos)servicePedidos.getById(dominio);
            obj = (DominioPedidos)servicePedidos.delete(obj);
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
            context.addCallbackParam("errorTransaction", "The Field 'IdPedido' is required");
        }
    }
    
    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            setProveedorEditable(false);
            listaProductos = new ArrayList();
            setTituloPantalla("Nuevo Pedido");
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioPedidos();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
            setProveedor(new DominioUsuarios());
            llenaListaProductos();
            setTotalVenta(0);
        } else {
            setProveedorEditable(true);
            setTituloPantalla("Modificar Pedido");
            setShowSave(false);
            setShowUpdate(true);
            setDatosProductos(obj);
        }
    }
    
    private void setDatosProductos(Object obj) {
        listaProductos = new ArrayList();
        dominio = (DominioPedidos)obj;
        setProveedor(dominio.getUsuarios());
        llenaListaProductos();
        DominioPedidosProductos auxDPP = new DominioPedidosProductos();
        auxDPP.setPedidos(dominio);
        double auxTotPagar = 0;
        try {
            List<DominioPedidosProductos> listaBorrar = servicePedidosProductos.getListaBorrar(auxDPP);
            for(DominioPedidosProductos aux: listaBorrar) {
                listaProductos.add(aux.getProductos());
                auxTotPagar += (aux.getProductos().getCosto() * aux.getProductos().getCantidad());
            }
            dominio.setTotalPagar(auxTotPagar);
            setTotalVenta(dominio.getTotalPagar());
        } catch(Exception e)  {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void showPedidos(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.menu.cat.pedidos.consulta"));
        dominio = (DominioPedidos)se.getObject();
        setDatosProductos(dominio);
    }
    
    public DominioPedidos getById(DominioPedidos obj) {
        return (DominioPedidos)servicePedidos.getById(obj);
    }
    
    public void addProducto(SelectEvent se) {
        DominioProductos producto = (DominioProductos)se.getObject();
        for(DominioProductos obj: listaProductos) {
            if(obj.getIdProducto().equals(producto.getIdProducto())) {
                return;
            }
        }
        listaProductos.add(producto);
    }
    
    public void removeProducto(DominioProductos obj) {
        listaProductos.remove(obj);
        obtenTotalCuenta();
    }
    
    public void onShowProductoByPedido(Object obj) {
        setTituloPantalla(Global.getPropRB("com.jr.props.menu.cat.pedidos.consulta"));
        dominio = (DominioPedidos)obj;
        setDatosProductos(dominio);
    }
    
    public void changeTabCorteCaja(TabChangeEvent event) {
        resetPedidos();
        estableceInit();
    }
    
    public void resetPedidos() {
        dominio = new DominioPedidos();
        totalVenta = 0;
        listaPedidos = new ArrayList();
    }

    @Override
    public void deleteAllSelect(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void changeCantidad(DominioProductos obj) {
        obtenTotalCuenta();
    }
    
    public void obtenTotalCuenta() {
        totalVenta = 0;
        for(DominioProductos dom: listaProductos) {
            totalVenta += (dom.getCantidad()*dom.getCosto());
        }
    }
}
