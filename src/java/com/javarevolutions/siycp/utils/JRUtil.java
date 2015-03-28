/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.javarevolutions.siycp.utils;

import com.javarevolutions.siycp.dominio.DominioMensajes;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JRUtil {
    private static List<DominioMensajes> mensajes = new ArrayList();
    
    /**
     * Este método copia todas las propiedades en cascada que sean identicas en nombre  y tipo de retorno de un bean a un entity o viceversa
     * @param to
     * @param from
     */
    public static void copyProperties(Object to, Object from) {
        Method[] metodos = from.getClass().getMethods();
        Class<?>[] types = new Class[]{};
        Object[] args = new Object[]{};
        for(Method metodo: metodos) {
            String name = metodo.getName();
            Method metodoGetTo = null;
            Method metodoSetTo = null;
            Method metodoGetFrom = null;
            Object objFrom = null;
            Object objAux = null;
            String get = null;
            if(name.startsWith("set")) {
                try {
                    get = name.replaceFirst("set", "get");
                    metodoGetTo = to.getClass().getMethod(get, types);
                    metodoSetTo = to.getClass().getMethod(name, metodoGetTo.getReturnType());
                    metodoGetFrom = from.getClass().getMethod(get, types);
                    objFrom = metodoGetFrom.invoke(from, args);
                    metodoSetTo.invoke(to, objFrom);
                } catch(NoSuchMethodException nsme) {
                } catch(IllegalArgumentException ilegal) {
                    try {
                        objAux = metodoGetTo.getReturnType().newInstance();
                        copyProperties(objAux, objFrom);
                        metodoSetTo.invoke(to, objAux);
                    } catch(Exception ex) {
                    }
                } catch(Exception e) {
                }
            }
        }
    }

    /**
     * @return the mensajes
     */
    public static List<DominioMensajes> getMensajes() {
        return mensajes;
    }

    /**
     * @param aMensajes the mensajes to set
     */
    public static void setMensajes(List<DominioMensajes> aMensajes) {
        mensajes = aMensajes;
    }
}
