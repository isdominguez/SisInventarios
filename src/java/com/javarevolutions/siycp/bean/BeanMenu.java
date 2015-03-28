/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanMenu.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.globals.Global;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;

public class BeanMenu implements Serializable {
    private String pathMenu;

    /**
     * @return the pathMenu
     */
    public String getPathMenu() {
        return pathMenu;
    }

    /**
     * @param pathMenu the pathMenu to set
     */
    public void setPathMenu(String pathMenu) {
        this.pathMenu = pathMenu;
    }
    
    public void inicia() {
        BeanIdioma idioma = (BeanIdioma)Global.getSession().getAttribute("beanIdioma");
        if(idioma != null && idioma.getLengua() != null && idioma.getLengua().equals("espanol")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
        }
        estableceRutaMenu();
    }
    
    private void estableceRutaMenu() {
        BeanIdioma idioma = (BeanIdioma)Global.getSession().getAttribute("beanIdioma");
        if(getPathMenu().equals("inicio")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.inicio"));
            return;
        }
        if(getPathMenu().equals("catAccesoriosxproducto")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.accesoriosxproducto"));
            return;
        }
        if(getPathMenu().equals("catInventarios")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.invent.home"));
            return;
        }
        if(getPathMenu().equals("catPedidos")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.pedidos.home"));
            return;
        }
        if(getPathMenu().equals("catPedidosProductos")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.pedidos_productos"));
            return;
        }
        if(getPathMenu().equals("catProductos")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.productos"));
            return;
        }
        if(getPathMenu().equals("catProveedoresProductos")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.proveedores_productos"));
            return;
        }
        if(getPathMenu().equals("catTiposMedidas")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.tipos_medidas"));
            return;
        }
        if(getPathMenu().equals("catUsers")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.users"));
            return;
        }
        if(getPathMenu().equals("catUsuarios")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.usuarios"));
            return;
        }
        if(getPathMenu().equals("catClientes")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.dir.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.clientes"));
            return;
        }
        if(getPathMenu().equals("catProveedores")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.dir.home")+" -> "+Global.getPropRB("com.jr.props.menu.cat.proveedores"));
            return;
        }
        if(getPathMenu().equals("changePassword")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.tools.home")+" -> "+Global.getPropRB("com.jr.props.menu.tools.cc"));
            return;
        }
        if(getPathMenu().equals("catCorteCaja")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.corte.ventas"));
            return;
        }
        if(getPathMenu().equals("catSeguimientoPagos")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.seguimiento_pagos"));
            return;
        }
        if(getPathMenu().equals("catVentas")) {
            idioma.setRutaMenu(Global.getPropRB("com.jr.props.menu.cat.ventas"));
        }
    }
    
    public void changeRutaMenu(String menu) {
        setPathMenu(menu);
    }
    
    public String onlyTime(java.util.Date fecha) {
        try {
            String aux = fecha.toString();
            String[] datos = aux.split("\\ ");
            aux = datos[1];
            return aux;
        } catch(Exception e) {
            return null;
        }
    }
}
