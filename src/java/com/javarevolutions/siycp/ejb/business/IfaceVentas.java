/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: IfaceVentas.java
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
import java.util.List;

public interface IfaceVentas {
    List<DominioVentas> getVentas() throws Exception;
    List<DominioVentas> getListaPagination(DominioVentas obj) throws Exception;
    int getRowCount() throws Exception;
    DominioVentas save(DominioVentas obj);
    DominioVentas update(DominioVentas obj);
    DominioVentas delete(DominioVentas obj);
    DominioVentas deleteAll(List<DominioVentas> lista);
    DominioVentas getById(DominioVentas obj);
    List<DominioVentas> getVentasByDia(DominioVentas obj) throws Exception;
    List<DominioVentas> getVentasByFechas(DominioVentas obj) throws Exception;
    int getRowCountByDia(DominioVentas obj) throws Exception;
    int getRowCountByFechas(DominioVentas obj) throws Exception;
}
