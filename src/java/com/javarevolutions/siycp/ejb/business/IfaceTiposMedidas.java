/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceTiposMedidas.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioTiposMedidas;
import java.util.List;

public interface IfaceTiposMedidas {
    List<DominioTiposMedidas> getTiposMedidas() throws Exception;
    List<DominioTiposMedidas> getListaPagination(DominioTiposMedidas obj) throws Exception;
    int getRowCount() throws Exception;
    DominioTiposMedidas save(DominioTiposMedidas obj);
    DominioTiposMedidas update(DominioTiposMedidas obj);
    DominioTiposMedidas delete(DominioTiposMedidas obj);
    DominioTiposMedidas deleteAll(List<DominioTiposMedidas> lista);
    DominioTiposMedidas getById(DominioTiposMedidas obj);
}
