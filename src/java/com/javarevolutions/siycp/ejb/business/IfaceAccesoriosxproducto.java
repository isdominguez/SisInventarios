/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceAccesoriosxproducto.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioAccesoriosxproducto;
import java.util.List;

public interface IfaceAccesoriosxproducto {
    List<DominioAccesoriosxproducto> getAccesoriosxproducto() throws Exception;
    List<DominioAccesoriosxproducto> getListaPagination(DominioAccesoriosxproducto obj) throws Exception;
    int getRowCount() throws Exception;
    DominioAccesoriosxproducto save(DominioAccesoriosxproducto obj);
    DominioAccesoriosxproducto update(DominioAccesoriosxproducto obj);
    DominioAccesoriosxproducto delete(DominioAccesoriosxproducto obj);
    DominioAccesoriosxproducto deleteAll(List<DominioAccesoriosxproducto> lista);
    DominioAccesoriosxproducto getById(DominioAccesoriosxproducto obj);
}
