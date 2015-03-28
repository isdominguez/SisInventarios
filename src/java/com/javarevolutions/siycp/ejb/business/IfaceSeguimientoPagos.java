/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: IfaceSeguimientoPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioSeguimientoPagos;
import java.util.List;

public interface IfaceSeguimientoPagos {
    List<DominioSeguimientoPagos> getSeguimientoPagos() throws Exception;
    List<DominioSeguimientoPagos> getListaPagination(DominioSeguimientoPagos obj) throws Exception;
    int getRowCount() throws Exception;
    DominioSeguimientoPagos save(DominioSeguimientoPagos obj);
    DominioSeguimientoPagos update(DominioSeguimientoPagos obj);
    DominioSeguimientoPagos delete(DominioSeguimientoPagos obj);
    DominioSeguimientoPagos deleteAll(List<DominioSeguimientoPagos> lista);
    DominioSeguimientoPagos getById(DominioSeguimientoPagos obj);
}
