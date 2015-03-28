/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanLogin.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioUsers;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceUsers;
import com.javarevolutions.siycp.utils.JRUtil;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class BeanLogin implements Serializable {
    private final static Logger log = Logger.getLogger(BeanLogin.class);
    private String user;
    private String password;
    private String idioma = "espanol";
    private IfaceUsers serviceLogin;
    private BeanIdioma lengua;
    
    private void estableceIdioma() {
        HttpServletRequest request = Global.getRequest();
        lengua = (BeanIdioma)request.getSession().getAttribute("beanIdioma");
        if(lengua == null) {
            lengua = new BeanIdioma();
        }
        if(getIdioma() != null && getIdioma().equals("espanol")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
            lengua.setPathGifIdioma(request.getContextPath()+"/resources/images/ingles_usa.gif");
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
            lengua.setPathGifIdioma(request.getContextPath()+"/resources/images/espanol_mexico1.gif");
        }
        lengua.setLengua(getIdioma());
        lengua.setTitleGifIdioma(Global.getPropRB("com.jr.props.titulo.change.language"));
        lengua.setRutaMenu(Global.getPropRB("com.jr.props.menu.inicio"));
        request.getSession().setAttribute("beanIdioma", lengua);
    }
    
    public void changeIdioma(ValueChangeEvent e) {
        setIdioma(""+e.getNewValue());
        estableceIdioma();
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    public String registraAsistencia() throws Exception {
        HttpServletRequest request = Global.getRequest();
        if(getUser().length() <= 0) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.user"));
            return "asistencia";
        }
        if(getPassword().length() <= 0) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.password"));
            return "asistencia";
        }
        DominioUsers dominio = new DominioUsers();
        JRUtil.copyProperties(dominio, this);
        dominio.setValidaAsistencia(true);
        dominio = getServiceLogin().validateLogin(dominio);
        if(dominio.isStatus()) {
            dominio = getServiceLogin().registraAsistencia(dominio);
            if(dominio.isStatus()) {
                request.setAttribute("errorIndex", 
                        Global.getPropRB("com.jr.props.title.modulo.asistencia.registro.exitoso")+" "+
                                Global.getFechaHoraHoy());
                return "asistencia";
            }
        }
        if(dominio.getMsg() != null && dominio.getMsg().equals("datosInvalidos")) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.login.error"));
        } else {
            request.setAttribute("errorIndex", dominio.getMsg());
            log.error("ERROR : "+dominio.getMsg());
        }
        return "asistencia";
    }
    
    public String validateLogin() throws Exception {
        HttpServletRequest request = Global.getRequest();
        if(getUser().length() <= 0) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.user"));
            return "index";
        }
        if(getPassword().length() <= 0) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.password"));
            return "index";
        }
        DominioUsers dominio = new DominioUsers();
        JRUtil.copyProperties(dominio, this);
        dominio.setValidaAsistencia(false);
        dominio = getServiceLogin().validateLogin(dominio);
        if(dominio.isStatus()) {
            estableceIdioma();
            request.getSession().setAttribute("userInSession", dominio);
            Global.getFC().getExternalContext().redirect("sistema/welcome.jr");
            return null;
        }
        if(dominio.getMsg() != null && dominio.getMsg().equals("datosInvalidos")) {
            request.setAttribute("errorIndex", Global.getPropRB("com.jr.props.msg.login.error"));
        } else {
            request.setAttribute("errorIndex", dominio.getMsg());
            log.error("ERROR : "+dominio.getMsg());
        }
        return "index";
    }

    /**
     * @return the serviceLogin
     */
    public IfaceUsers getServiceLogin() {
        return serviceLogin;
    }

    /**
     * @param serviceLogin the serviceLogin to set
     */
    public void setServiceLogin(IfaceUsers serviceLogin) {
        this.serviceLogin = serviceLogin;
    }
}
