/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: DAOPedidos.java
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
import java.util.Date;
import java.util.List;

public interface DAOPedidos extends DAOIfaceGeneric {
    List<Pedidos> getPedidosByDia(Date dia, int first, int pageSize) throws Exception;
    List<Pedidos> getPedidosByFechas(Date diaInicio, Date diaFin, int first, int pageSize) throws Exception;
    int getRowCountByDia(Date dia) throws Exception;
    int getRowCountByFechas(Date diaInicio, Date diaFin) throws Exception;
}
