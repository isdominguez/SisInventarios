/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceUsuarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.ejb.session.SessionBeanUsuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceUsuarios implements IfaceUsuarios {
    @Autowired
    private SessionBeanUsuarios ejbUsuarios;
    
    @Override
    public List<DominioUsuarios> getLista() throws Exception {
        return ejbUsuarios.getUsuarios();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbUsuarios.getUsuariosPagination((DominioUsuarios)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbUsuarios.getRowCountBUsuarios();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbUsuarios.getById((DominioUsuarios)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbUsuarios.save((DominioUsuarios)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbUsuarios.update((DominioUsuarios)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbUsuarios.delete((DominioUsuarios)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbUsuarios.deleteAll(lista);
    }
    
    @Override
    public List<DominioUsuarios> getListaByTipo(DominioUsuarios obj) throws Exception {
        return ejbUsuarios.getListaByTipo(obj);
    }

    @Override
    public int getRowsByTipo(DominioUsuarios obj) throws Exception {
        return ejbUsuarios.getRowsByTipo(obj);
    }
}
