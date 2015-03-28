/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: IfaceInventarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import java.util.List;

public interface IfaceInventarios {
    List<DominioInventarios> getInventarios() throws Exception;
    List<DominioInventarios> getListaPagination(DominioInventarios obj) throws Exception;
    int getRowCount() throws Exception;
    DominioInventarios save(DominioInventarios obj);
    DominioInventarios update(DominioInventarios obj);
    DominioInventarios delete(DominioInventarios obj);
    DominioInventarios deleteAll(List<DominioInventarios> lista);
    DominioInventarios getById(DominioInventarios obj);
    DominioInventarios createAllProductos();
    DominioInventarios updateCantidades(List<DominioInventarios> lista);
    DominioInventarios getStatusInventario();
}
