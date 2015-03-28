/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: BeanMensajes.java
 * Fecha de creacion : Diciembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.bean;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import com.javarevolutions.siycp.dominio.DominioMensajes;
import com.javarevolutions.siycp.globals.Global;
import com.javarevolutions.siycp.service.IfaceInventarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;

public class BeanMensajes implements Serializable {
    private List<DominioMensajes> mensajes;
    private DominioMensajes dominio;
    private IfaceInventarios serviceInventarios;
    private boolean showMessages;

    /**
     * @return the mensajes
     */
    public List<DominioMensajes> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public void setMensajes(List<DominioMensajes> mensajes) {
        this.mensajes = mensajes;
    }

    /**
     * @return the dominio
     */
    public DominioMensajes getDominio() {
        return dominio;
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(DominioMensajes dominio) {
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
     * @return the showMessages
     */
    public boolean isShowMessages() {
        return showMessages;
    }

    /**
     * @param showMessages the showMessages to set
     */
    public void setShowMessages(boolean showMessages) {
        this.showMessages = showMessages;
    }
    
        
    public void llenaMensajes() {
        setShowMessages(false);
        mensajes = new ArrayList();
        DominioInventarios dominioInventarios = serviceInventarios.getStatusInventario();
        if(!dominioInventarios.isStatus()) {
            dominio = new DominioMensajes();
            dominio.setTitulo(Global.getPropRB("com.jr.props.menu.cat.inventarios.msg.titulo.importante"));
            dominio.setMensaje(Global.getPropRB("com.jr.props.menu.cat.inventarios.msg.mensaje.importante"));
            mensajes.add(dominio);
            setShowMessages(true);
        }
    }
}
