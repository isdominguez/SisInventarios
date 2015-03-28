/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceInventarios.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioInventarios;
import com.javarevolutions.siycp.ejb.session.SessionBeanInventarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceInventarios implements IfaceInventarios {
    @Autowired
    private SessionBeanInventarios ejbInventarios;
    
    @Override
    public List<DominioInventarios> getLista() throws Exception {
        return ejbInventarios.getInventarios();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbInventarios.getInventariosPagination((DominioInventarios)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbInventarios.getRowCountBInventarios();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbInventarios.getById((DominioInventarios)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbInventarios.save((DominioInventarios)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbInventarios.update((DominioInventarios)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbInventarios.delete((DominioInventarios)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbInventarios.deleteAll(lista);
    }

    @Override
    public DominioInventarios createAllProductos() throws Exception {
        return ejbInventarios.createAllProductos();
    }
    
    @Override
    public DominioInventarios updateCantidades(List<DominioInventarios> lista) throws Exception {
        return ejbInventarios.updateCantidades(lista);
    }

    @Override
    public DominioInventarios getStatusInventario() {
        return ejbInventarios.getStatusInventario();
    }
}
