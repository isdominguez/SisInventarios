/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Productos;
import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import com.javarevolutions.siycp.ejb.entity.VentasProductos;
import java.util.List;

public interface DAOVentasProductos extends DAOIfaceGeneric {
    void saveAllProdsPedidos(List<VentasProductos> lista, Ventas venta) throws Exception;
    void updateAllProdsPedidos(List<VentasProductos> listaAdd, List<VentasProductos> listaDel, 
            Ventas venta) throws Exception;
    List<VentasProductos> getListaBorrar(VentasProductos entity) throws Exception;
    Usuarios getUserById(Object value) throws Exception;
    long getExistenciaInventarios(Productos obj) throws Exception;
}