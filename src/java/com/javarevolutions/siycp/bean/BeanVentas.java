/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: BeanVentas.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioGraficaVenta;
import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.dominio.DominioVentas;
import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceProductos;
import com.javarevolutions.siycp.service.IfaceVentas;
import com.javarevolutions.siycp.service.IfaceVentasProductos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class BeanVentas extends BeanGeneric {
    private final static Logger log = Logger.getLogger(BeanVentas.class);
    private DominioVentas dominio;
    private LazyDataModel<DominioVentas> listaModel;
    private IfaceVentas serviceVentas;
    private IfaceVentasProductos serviceVentasProductos;
    private List<DominioVentas> listaVentas;
    private List<DominioProductos> listaProductos;
    private DominioUsuarios cliente;
    private LazyDataModel<DominioProductos> listaModelProductos;
    private IfaceProductos serviceProductos;
    private List<DominioProductos> auxListProductos;
    private double totalVenta;
    private double utilidades;
    private BeanCorteCaja beanCorteCaja;
    
    /**
     * @return the listaModel
     */
    public LazyDataModel<DominioVentas> getListaModel() {
        return listaModel;
    }

    /**
     * @param listaModel the listaModel to set
     */
    public void setListaModel(LazyDataModel<DominioVentas> listaModel) {
        this.listaModel = listaModel;
    }
    
    /**
     * @return the dominio
     */
    public DominioVentas getDominio() {
        return dominio;
    }

    /**
     * @param dominio the productos to set
     */
    public void setDominio(DominioVentas dominio) {
        this.dominio = dominio;
    }

    /**
     * @return the serviceVentas
     */
    public IfaceVentas getServiceVentas() {
        return serviceVentas;
    }

    /**
     * @param serviceVentas the serviceVentas to set
     */
    public void setServiceVentas(IfaceVentas serviceVentas) {
        this.serviceVentas = serviceVentas;
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
     * @return the listaVentas
     */
    public List<DominioVentas> getListaVentas() {
        return listaVentas;
    }

    /**
     * @param listaVentas the listaVentas to set
     */
    public void setListaVentas(List<DominioVentas> listaVentas) {
        this.listaVentas = listaVentas;
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
     * @return the cliente
     */
    public DominioUsuarios getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(DominioUsuarios cliente) {
        this.cliente = cliente;
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
     * @return the utilidades
     */
    public double getUtilidades() {
        return utilidades;
    }

    /**
     * @param utilidades the utilidades to set
     */
    public void setUtilidades(double utilidades) {
        this.utilidades = utilidades;
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

    @Override
    public void llenaLista() {
        listaModel = new LazyDataModel<DominioVentas>() {
            @Override
            public List<DominioVentas> load(int first, int pageSize, 
                    String sortField, SortOrder sortOrder, Map<String, String> filters) {
                List<DominioVentas> result = new ArrayList();
                try {
                    DominioVentas auxDominio = new DominioVentas();
                    auxDominio.setFirst(first);
                    auxDominio.setPageSize(pageSize);
                    result = serviceVentas.getListaPagination(auxDominio);
                    setListaVentas(result);
                } catch(Exception e) {
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                    log.error("ERROR : "+e);
                }
                return result;
            }
        };
        try {
            listaModel.setRowCount(serviceVentas.getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModel.setPageSize(10);
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
                    auxDominio.setEntidadPersistir("Ventas");
                    auxDominio.setUsuarios(getCliente());
                    result = getServiceProductos().getListaPagination(auxDominio);
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
            listaModelProductos.setRowCount(getServiceProductos().getRowCount());
        } catch(Exception e) {
            log.error("ERROR en llenaLista : "+e);
        }
        listaModelProductos.setPageSize(10);
    }
    
    private void setDatosProductos(Object obj) {
        setListaProductos((List<DominioProductos>) new ArrayList());
        dominio = (DominioVentas)obj;
        setCliente(dominio.getUsuarios());
        llenaListaProductos();
        DominioVentasProductos auxDPP = new DominioVentasProductos();
        auxDPP.setVentas(dominio);
        double auxTotPagar = 0;
        try {
            List<DominioVentasProductos> listaBorrar = serviceVentasProductos.getListaBorrar(auxDPP);
            for(DominioVentasProductos aux: listaBorrar) {
                if(aux.getVentas().getUsuarios().getTipoCliente() != null) {
                    //General, Intermedio, Especial
                    if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.GENERAL)) {
                        aux.getProductos().setCosto(aux.getProductos().getPrecioGeneral());
                        auxTotPagar += (aux.getProductos().getPrecioGeneral() * aux.getProductos().getCantidad());
                    }
                    if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.INTERMEDIO)) {
                        aux.getProductos().setCosto(aux.getProductos().getPrecioIntermedio());
                        auxTotPagar += (aux.getProductos().getPrecioIntermedio()* aux.getProductos().getCantidad());
                    }
                    if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.ESPECIAL)) {
                        aux.getProductos().setCosto(aux.getProductos().getPrecioEspecial());
                        auxTotPagar += (aux.getProductos().getPrecioEspecial()* aux.getProductos().getCantidad());
                    }
                } else {
                    aux.getProductos().setCosto(aux.getProductos().getPrecioGeneral());
                    auxTotPagar += (aux.getProductos().getPrecioGeneral() * aux.getProductos().getCantidad());
                }
                getListaProductos().add(aux.getProductos());
            }
            dominio.setTotalPagar(auxTotPagar);
            setTotalVenta(dominio.getTotalPagar());
        } catch(Exception e)  {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void showVentas(SelectEvent se) {
        setTituloPantalla(Global.getPropRB("com.jr.props.menu.cat.ventas.consulta"));
        dominio = (DominioVentas)se.getObject();
        setDatosProductos(dominio);
    }
    
    public DominioVentas getById(DominioVentas obj) {
        return (DominioVentas)serviceVentas.getById(obj);
    }
    
    public void addProducto(SelectEvent se) {
        DominioProductos producto = (DominioProductos)se.getObject();
        for(DominioProductos obj: getListaProductos()) {
            if(obj.getIdProducto().equals(producto.getIdProducto())) {
                return;
            }
        }
        getListaProductos().add(producto);
    }
    
    public void removeProducto(DominioProductos obj) {
        getListaProductos().remove(obj);
        obtenTotalCuenta();
    }
    
    public void onShowProductoByVenta(Object obj) {
        setTituloPantalla(Global.getPropRB("com.jr.props.menu.cat.ventas.consulta"));
        dominio = (DominioVentas)obj;
        setDatosProductos(dominio);
    }

    @Override
    public void save(ActionEvent event) {
        boolean status = false;
        RequestContext context = RequestContext.getCurrentInstance();
        if(getListaProductos().size() <= 0) {
            String error = Global.getPropRB("com.jr.props.menu.cat.ventas.null");
            Global.addMsg(Global.getPropRB("com.jr.props.menu.cat.ventas.error"), error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        } else {
            dominio.setFechaVenta(new java.util.Date());
            dominio.setHoraVenta(dominio.getFechaVenta());
            dominio.setUsuarios(cliente);
            dominio.setIdUsuario(cliente.getIdUsuario());
            setAccion("save");
            List<DominioVentasProductos> auxPedPro = new ArrayList();
            for(DominioProductos prods: listaProductos) {
                DominioVentasProductos nuevo = new DominioVentasProductos();
                nuevo.setVentas(dominio);
                nuevo.setProductos(prods);
                nuevo.setCantidad(prods.getCantidad());
                auxPedPro.add(nuevo);
            }
            DominioVentasProductos dom = serviceVentasProductos.saveAllProdsPedidos(auxPedPro, dominio);
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
        if(getListaProductos().size() <= 0) {
            String error = Global.getPropRB("com.jr.props.menu.cat.ventas.null");
            Global.addMsg(Global.getPropRB("com.jr.props.menu.cat.ventas.error"), error);
            context.addCallbackParam("errorTransaction", error);
            context.addCallbackParam("statusTransaction", status);
        } else {
            String condiciones = dominio.getObservaciones();
            dominio = (DominioVentas)serviceVentas.getById(dominio);
            dominio.setFechaModificacion(new java.util.Date());
            dominio.setUsuarios(cliente);
            dominio.setIdUsuario(cliente.getIdUsuario());
            dominio.setObservaciones(condiciones);
            DominioVentasProductos auxDPP = new DominioVentasProductos();
            auxDPP.setVentas(dominio);            
            List<DominioVentasProductos> listaBorrar = null;
            try {
                listaBorrar = serviceVentasProductos.getListaBorrar(auxDPP);
            } catch(Exception e)  {
                Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
                context.addCallbackParam("errorTransaction", "ERROR : "+e);
                log.error("ERROR : "+e);
            }
            if(listaBorrar != null) {
                List<DominioVentasProductos> auxPedPro = new ArrayList();
                for(DominioProductos prods: listaProductos) {
                    DominioVentasProductos nuevo = new DominioVentasProductos();
                    nuevo.setVentas(dominio);
                    nuevo.setProductos(prods);
                    nuevo.setCantidad(prods.getCantidad());
                    auxPedPro.add(nuevo);
                }
                DominioVentasProductos response = serviceVentasProductos.
                        updateAllProdsPedidos(auxPedPro, listaBorrar,dominio);
                if(response.isStatus()) {
                    status = true;
                    Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.update"), Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
                    context.addCallbackParam("mensajeTransaction", Global.getPropRB("com.jr.props.msg.registro.el")+" '"+contador+"' "+Global.getPropRB("com.jr.props.msg.registro.el.update"));
                    onUpdateInsertRow(dominio);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(ActionEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onUpdateInsertRow(Object obj) {
        if(obj == null) {
            listaProductos = new ArrayList();
            setTituloPantalla("Nueva Orden de Venta");
            setShowSave(true);
            setShowUpdate(false);
            dominio = new DominioVentas();
            dominio.setPosicion((listaModel.getRowCount()%listaModel.getPageSize()));
            setCliente(new DominioUsuarios());
            llenaListaProductos();
            setTotalVenta(0);
        } else {
            setTituloPantalla("Modificar Orden de Venta");
            setShowSave(false);
            setShowUpdate(true);
            setDatosProductos(obj);
        }
    }

    @Override
    public void deleteAllSelect(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void validaExistencias(FacesContext fc, UIComponent component, 
            Object value) throws ValidatorException {
        Long existencia = (Long)component.getAttributes().get("valueExistencia");
        Long cantidad = (Long)value;
        if(cantidad > existencia) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en cantidades", 
            "La cantidad a vender no puede rebasar la existencia en inventarios");
            throw new ValidatorException(fm);
        }
    }
    
    @PostConstruct
    public void llenaFechaVentas() {
        estableceInit();
    }
    
    private void estableceInit() {
        dominio.setFechaVenta(new java.util.Date());
        dominio.setFechaFin(dominio.getFechaVenta());
        java.util.Date fechaInicio = new java.util.Date(dominio.getFechaFin().getTime()-
                (7 * 24 * 60 * 60 * 1000));
        dominio.setFechaInicio(fechaInicio);
        getBeanCorteCaja().setShowGraficaXDia(false);
    }
    
    public void changeTabCorteCaja(TabChangeEvent event) {
        resetVentas();
        estableceInit();
    }
    
    public void resetVentas() {
        dominio = new DominioVentas();
        totalVenta = 0;
        utilidades = 0;
        listaVentas = new ArrayList();
    }
    
    private List<DominioGraficaVenta> listaGraficaVentas;
    private void executeTotalVenta(List<DominioVentas> allVentas) {
        try {
            listaGraficaVentas = new ArrayList();
            double costoTotal = 0;
            for(DominioVentas obj: allVentas) {
                DominioVentasProductos auxDPP = new DominioVentasProductos();
                auxDPP.setVentas(obj);
                List<DominioVentasProductos> listaBorrar = serviceVentasProductos.getListaBorrar(auxDPP);
                double totalXVenta = 0;
                for(DominioVentasProductos aux: listaBorrar) {
                    if(aux.getVentas().getUsuarios().getTipoCliente() != null) {
                        //General, Intermedio, Especial
                        if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.GENERAL)) {
                            totalVenta += (aux.getCantidad() * aux.getProductos().getPrecioGeneral());
                            totalXVenta += (aux.getCantidad() * aux.getProductos().getPrecioGeneral());
                        }
                        if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.INTERMEDIO)) {
                            totalVenta += (aux.getCantidad() * aux.getProductos().getPrecioIntermedio());
                            totalXVenta += (aux.getCantidad() * aux.getProductos().getPrecioIntermedio());
                        }
                        if(aux.getVentas().getUsuarios().getTipoCliente().equals(Global.ESPECIAL)) {
                            totalVenta += (aux.getCantidad() * aux.getProductos().getPrecioEspecial());
                            totalXVenta += (aux.getCantidad() * aux.getProductos().getPrecioEspecial());
                        }
                    } else {
                        totalVenta += (aux.getCantidad() * aux.getProductos().getPrecioGeneral());
                        totalXVenta += (aux.getCantidad() * aux.getProductos().getPrecioGeneral());
                    }
                    costoTotal += (aux.getProductos().getCosto() * aux.getProductos().getCantidad());
                }
                DominioGraficaVenta aux = new DominioGraficaVenta();
                aux.setIdVenta(obj.getIdVenta());
                aux.setTotalVenta(totalXVenta);
                listaGraficaVentas.add(aux);
            }
            utilidades = totalVenta - costoTotal;
            beanCorteCaja.creaGraficaXDiaVentas(listaGraficaVentas);
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void getVentasXDia(ActionEvent ae) {
        RequestContext contextAux = RequestContext.getCurrentInstance();
        if(dominio.getFechaVenta().getTime() > Global.getFechaHoy().getTime()) {
            contextAux.addCallbackParam("statusTransaction", false);
            contextAux.addCallbackParam("errorTransaction", 
                    Global.getPropRB("com.jr.props.menu.corte.fecha_venta_erronea"));
            Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                    Global.getPropRB("com.jr.props.menu.corte.fecha_venta_erronea"));
            return;
        }
        totalVenta = 0;
        try {
            listaVentas = serviceVentas.getVentasByDia(dominio);
            if(listaVentas.size() > 0) {
                executeTotalVenta(listaVentas);
                contextAux.addCallbackParam("statusTransaction", true);
            } else {
                contextAux.addCallbackParam("statusTransaction", false);
                contextAux.addCallbackParam("errorTransaction", 
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.ventas"));
                Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.ventas"));
                beanCorteCaja.setShowGraficaXDia(false);
            }
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void getVentasXSemana(ActionEvent ae) {
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
            listaVentas = serviceVentas.getVentasByFechas(dominio);
            if(listaVentas.size() > 0) {
                executeTotalVenta(listaVentas);
                contextAux.addCallbackParam("statusTransaction", true);
            } else {
                contextAux.addCallbackParam("statusTransaction", false);
                contextAux.addCallbackParam("errorTransaction", 
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.ventasxsemana"));
                Global.addMsg(Global.getPropRB("com.jr.props.msg.aviso.alert"),
                        Global.getPropRB("com.jr.props.menu.corte.no.existen.ventasxsemana"));
                beanCorteCaja.setShowGraficaXDia(false);
            }
        } catch(Exception e) {
            Global.addMsg(Global.getPropRB("com.jr.props.msg.registro.error"), "ERROR : "+e);
            log.error("ERROR : "+e);
        }
    }
    
    public void changeCliente() {
        llenaListaProductos();
        listaProductos = new ArrayList();
    }
    
    public void changeCantidad(DominioProductos obj) {
        obtenTotalCuenta();
        if(obj.getCantidad() > obj.getExistencia()) {
            Global.addMsg("Error en cantidades", 
            "La cantidad a vender no puede rebasar la existencia en inventarios");
        }
    }
    
    public void obtenTotalCuenta() {
        totalVenta = 0;
        for(DominioProductos dom: listaProductos) {
            totalVenta += (dom.getCantidad()*dom.getCosto());
        }
    }
}
