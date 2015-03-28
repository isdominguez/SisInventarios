
package com.javarevolutions.siycp.globals;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Global {
    public final static String GENERAL = "General";
    public final static String INTERMEDIO = "Intermedio";
    public final static String ESPECIAL = "Especial";
    
    public static HttpSession getSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        return (HttpSession)fc.getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
        return request;
    }
    
    public static FacesContext getFC() {
        return FacesContext.getCurrentInstance();
    }
    
    public static void addMsg(String titulo, String msg) {
        FacesMessage mensaje = new FacesMessage(titulo, msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public static void addMsgNulos(String msg) {
        FacesMessage mensaje = new FacesMessage("Campos Requeridos", msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
    }
    
    public static String getPropRB(String key) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ResourceBundle bundle = fc.getApplication().getResourceBundle(fc, "props");
        return bundle.getString(key);
    }
    
    public static java.util.Date getFechaHoy() {
        return new java.util.Date();
    }
    
    public static java.sql.Timestamp getFechaHoraHoy() {
        long ms = getFechaHoy().getTime();
        java.sql.Timestamp ts = new java.sql.Timestamp(ms);
        return ts;
    }
}
