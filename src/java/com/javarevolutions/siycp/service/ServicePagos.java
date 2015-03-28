/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: ServicePagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioPagos;
import com.javarevolutions.siycp.ejb.session.SessionBeanPagos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicePagos implements IfacePagos {
    @Autowired
    private SessionBeanPagos ejbPagos;
    
    @Override
    public List<DominioPagos> getLista() throws Exception {
        return ejbPagos.getPagos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbPagos.getPagosPagination((DominioPagos)obj);
    }

    @Override
    public int getRowCount(DominioPagos obj) throws Exception {
        return ejbPagos.getRowCountBPagos(obj);
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbPagos.getById((DominioPagos)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbPagos.save((DominioPagos)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbPagos.update((DominioPagos)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbPagos.delete((DominioPagos)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbPagos.deleteAll(lista);
    }

    @Override
    public int getRowCount() throws Exception {
        return -1;
    }
}
