/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceGeneric.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import java.io.Serializable;
import java.util.List;

public interface IfaceGeneric<T> extends Serializable {
    List<T> getLista() throws Exception;
    List<T> getListaPagination(T obj) throws Exception;
    int getRowCount() throws Exception;
    T save(T obj);
    T update(T obj);
    T delete(T obj);
    T deleteAll(List<T> lista);
    T getById(T obj);
}
