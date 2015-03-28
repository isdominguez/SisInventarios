/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: IfaceVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioVentas;
import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import java.util.List;

public interface IfaceVentasProductos {
    List<DominioVentasProductos> getVentasProductos() throws Exception;
    List<DominioVentasProductos> getListaPagination(DominioVentasProductos obj) throws Exception;
    int getRowCount() throws Exception;
    DominioVentasProductos save(DominioVentasProductos obj);
    DominioVentasProductos update(DominioVentasProductos obj);
    DominioVentasProductos delete(DominioVentasProductos obj);
    DominioVentasProductos deleteAll(List<DominioVentasProductos> lista);
    DominioVentasProductos getById(DominioVentasProductos obj);
    DominioVentasProductos saveAllProdsPedidos(List<DominioVentasProductos> lista, DominioVentas venta);
    DominioVentasProductos updateAllProdsPedidos(List<DominioVentasProductos> listaAdd, 
            List<DominioVentasProductos> listaDel, DominioVentas venta);
    List<DominioVentasProductos> getListaBorrar(DominioVentasProductos obj) throws Exception;
}
