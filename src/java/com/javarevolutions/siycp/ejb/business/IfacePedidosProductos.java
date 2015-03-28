/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfacePedidosProductos.java
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
import com.javarevolutions.siycp.dominio.DominioPedidosProductos;
import java.util.List;

public interface IfacePedidosProductos {
    List<DominioPedidosProductos> getPedidosProductos() throws Exception;
    List<DominioPedidosProductos> getListaPagination(DominioPedidosProductos obj) throws Exception;
    int getRowCount() throws Exception;
    DominioPedidosProductos save(DominioPedidosProductos obj);
    DominioPedidosProductos update(DominioPedidosProductos obj);
    DominioPedidosProductos delete(DominioPedidosProductos obj);
    DominioPedidosProductos deleteAll(List<DominioPedidosProductos> lista);
    DominioPedidosProductos saveAllProdsPedidos(List<DominioPedidosProductos> lista, DominioPedidos pedido);
    DominioPedidosProductos updateAllProdsPedidos(List<DominioPedidosProductos> listaAdd, 
            List<DominioPedidosProductos> listaDel, DominioPedidos pedido);
    DominioPedidosProductos getById(DominioPedidosProductos obj);
    List<DominioPedidosProductos> getListaBorrar(DominioPedidosProductos obj) throws Exception;
}
