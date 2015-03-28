/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: ServiceHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioHorarioEmpleados;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.ejb.session.SessionBeanHorarioEmpleados;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceHorarioEmpleados implements IfaceHorarioEmpleados {
    @Autowired
    private SessionBeanHorarioEmpleados ejbHorarioEmpleados;
    
    @Override
    public List<DominioHorarioEmpleados> getLista() throws Exception {
        return ejbHorarioEmpleados.getHorarioEmpleados();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbHorarioEmpleados.getHorarioEmpleadosPagination((DominioHorarioEmpleados)obj);
    }

    @Override
    public int getRowCountByUser(DominioUsuarios user) throws Exception {
        return ejbHorarioEmpleados.getRowCountBHorarioEmpleados(user);
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbHorarioEmpleados.getById((DominioHorarioEmpleados)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbHorarioEmpleados.save((DominioHorarioEmpleados)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbHorarioEmpleados.update((DominioHorarioEmpleados)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbHorarioEmpleados.delete((DominioHorarioEmpleados)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbHorarioEmpleados.deleteAll(lista);
    }

    @Override
    public int getRowCount() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
