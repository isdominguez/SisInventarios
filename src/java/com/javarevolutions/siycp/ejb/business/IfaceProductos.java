/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import java.util.List;

public interface IfaceProductos {
    List<DominioProductos> getProductos() throws Exception;
    List<DominioProductos> getListaPagination(DominioProductos obj) throws Exception;
    List<DominioProductos> getListaPaginationByProveedor(DominioProductos obj) throws Exception;
    int getRowCount() throws Exception;
    DominioProductos save(DominioProductos obj);
    DominioProductos update(DominioProductos obj);
    DominioProductos delete(DominioProductos obj);
    DominioProductos deleteAll(List<DominioProductos> lista);
    DominioProductos getById(DominioProductos obj);
    List<DominioProductos> getListaByTipo(DominioProductos obj) throws Exception;
    int getRowsByTipo(DominioProductos obj) throws Exception;
    int getRowsByProveedor(DominioProductos obj) throws Exception;
    DominioUsuarios getUserById(DominioUsuarios obj);
}
