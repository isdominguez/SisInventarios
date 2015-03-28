/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfacePedidos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioPedidos;
import java.util.List;

public interface IfacePedidos {
    List<DominioPedidos> getPedidos() throws Exception;
    List<DominioPedidos> getListaPagination(DominioPedidos obj) throws Exception;
    int getRowCount() throws Exception;
    DominioPedidos save(DominioPedidos obj);
    DominioPedidos update(DominioPedidos obj);
    DominioPedidos delete(DominioPedidos obj);
    DominioPedidos deleteAll(List<DominioPedidos> lista);
    DominioPedidos getById(DominioPedidos obj);
    List<DominioPedidos> getPedidosByDia(DominioPedidos obj) throws Exception;
    List<DominioPedidos> getPedidosByFechas(DominioPedidos obj) throws Exception;
    int getRowCountByDia(DominioPedidos obj) throws Exception;
    int getRowCountByFechas(DominioPedidos obj) throws Exception;
}
