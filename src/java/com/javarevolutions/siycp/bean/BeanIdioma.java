/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanIdioma.java
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
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BeanIdioma {
    private String pathGifIdioma;
    private String titleGifIdioma;
    private String lengua;
    private String rutaMenu;
    
    private void estableceIdioma() {
        HttpServletRequest request = Global.getRequest();
        if(getLengua() != null && getLengua().equals("espanol")) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
            setPathGifIdioma(request.getContextPath()+"/resources/images/espanol_mexico1.gif");
            setLengua("ingles");
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("es"));
            setPathGifIdioma(request.getContextPath()+"/resources/images/ingles_usa.gif");
            setLengua("espanol");
        }
        setTitleGifIdioma(Global.getPropRB("com.jr.props.titulo.change.language"));
    }
    
    public String changeIdioma() {
        estableceIdioma();
        return "";
    }

    /**
     * @return the pathGifIdioma
     */
    public String getPathGifIdioma() {
        return pathGifIdioma;
    }

    /**
     * @param pathGifIdioma the pathGifIdioma to set
     */
    public void setPathGifIdioma(String pathGifIdioma) {
        this.pathGifIdioma = pathGifIdioma;
    }

    /**
     * @return the titleGifIdioma
     */
    public String getTitleGifIdioma() {
        return titleGifIdioma;
    }

    /**
     * @param titleGifIdioma the titleGifIdioma to set
     */
    public void setTitleGifIdioma(String titleGifIdioma) {
        this.titleGifIdioma = titleGifIdioma;
    }

    /**
     * @return the lengua
     */
    public String getLengua() {
        return lengua;
    }

    /**
     * @param lengua the lengua to set
     */
    public void setLengua(String lengua) {
        this.lengua = lengua;
    }

    /**
     * @return the rutaMenu
     */
    public String getRutaMenu() {
        return rutaMenu;
    }

    /**
     * @param rutaMenu the rutaMenu to set
     */
    public void setRutaMenu(String rutaMenu) {
        this.rutaMenu = rutaMenu;
    }
}
