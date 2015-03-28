/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceAccesoriosxproducto.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioAccesoriosxproducto;
import com.javarevolutions.siycp.ejb.session.SessionBeanAccesoriosxproducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAccesoriosxproducto implements IfaceAccesoriosxproducto {
    @Autowired
    private SessionBeanAccesoriosxproducto ejbAccesoriosxproducto;
    
    @Override
    public List<DominioAccesoriosxproducto> getLista() throws Exception {
        return ejbAccesoriosxproducto.getAccesoriosxproducto();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbAccesoriosxproducto.getAccesoriosxproductoPagination((DominioAccesoriosxproducto)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbAccesoriosxproducto.getRowCountBAccesoriosxproducto();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbAccesoriosxproducto.getById((DominioAccesoriosxproducto)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbAccesoriosxproducto.save((DominioAccesoriosxproducto)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbAccesoriosxproducto.update((DominioAccesoriosxproducto)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbAccesoriosxproducto.delete((DominioAccesoriosxproducto)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbAccesoriosxproducto.deleteAll(lista);
    }
}
