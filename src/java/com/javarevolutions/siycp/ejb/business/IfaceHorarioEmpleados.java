/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: IfaceHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.business;

import com.javarevolutions.siycp.dominio.DominioHorarioEmpleados;
import java.util.List;

public interface IfaceHorarioEmpleados {
    List<DominioHorarioEmpleados> getHorarioEmpleados() throws Exception;
    List<DominioHorarioEmpleados> getListaPagination(DominioHorarioEmpleados obj) throws Exception;
    int getRowCountByUser(long idUsuario) throws Exception;
    DominioHorarioEmpleados save(DominioHorarioEmpleados obj);
    DominioHorarioEmpleados update(DominioHorarioEmpleados obj);
    DominioHorarioEmpleados delete(DominioHorarioEmpleados obj);
    DominioHorarioEmpleados deleteAll(List<DominioHorarioEmpleados> lista);
    DominioHorarioEmpleados getById(DominioHorarioEmpleados obj);
}
