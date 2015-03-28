/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOVentas.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Usuarios;
import com.javarevolutions.siycp.ejb.entity.Ventas;
import java.util.Date;
import java.util.List;

public interface DAOVentas extends DAOIfaceGeneric {
    Usuarios getUserById(Long idUsuario) throws Exception;
    List<Ventas> getVentasByDia(Date dia, int first, int pageSize) throws Exception;
    List<Ventas> getVentasByFechas(Date diaInicio, Date diaFin, int first, int pageSize) throws Exception;
    int getRowCountByDia(Date dia) throws Exception;
    int getRowCountByFechas(Date diaInicio, Date diaFin) throws Exception;
}
