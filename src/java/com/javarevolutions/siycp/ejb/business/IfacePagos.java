/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: IfacePagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioPagos;
import java.util.List;

public interface IfacePagos {
    List<DominioPagos> getPagos() throws Exception;
    List<DominioPagos> getListaPagination(DominioPagos obj) throws Exception;
    int getRowCount(int idSeguimientoPago) throws Exception;
    DominioPagos save(DominioPagos obj);
    DominioPagos update(DominioPagos obj);
    DominioPagos delete(DominioPagos obj);
    DominioPagos deleteAll(List<DominioPagos> lista);
    DominioPagos getById(DominioPagos obj);
}
