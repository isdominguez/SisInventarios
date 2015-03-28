/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOPedidosProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Pedidos;
import com.javarevolutions.siycp.ejb.entity.PedidosProductos;
import java.util.List;

public interface DAOPedidosProductos extends DAOIfaceGeneric {
    void saveAllProdsPedidos(List<PedidosProductos> lista, Pedidos pedido) throws Exception;
    void updateAllProdsPedidos(List<PedidosProductos> listaAdd, List<PedidosProductos> listaDel, 
            Pedidos pedido) throws Exception;
    List<PedidosProductos> getListaBorrar(PedidosProductos entity) throws Exception;
}
