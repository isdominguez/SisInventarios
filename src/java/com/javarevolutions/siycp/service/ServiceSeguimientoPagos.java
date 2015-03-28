/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: ServiceSeguimientoPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioSeguimientoPagos;
import com.javarevolutions.siycp.ejb.session.SessionBeanSeguimientoPagos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSeguimientoPagos implements IfaceSeguimientoPagos {
    @Autowired
    private SessionBeanSeguimientoPagos ejbSeguimientoPagos;
    
    @Override
    public List<DominioSeguimientoPagos> getLista() throws Exception {
        return ejbSeguimientoPagos.getSeguimientoPagos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbSeguimientoPagos.getSeguimientoPagosPagination((DominioSeguimientoPagos)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbSeguimientoPagos.getRowCountBSeguimientoPagos();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbSeguimientoPagos.getById((DominioSeguimientoPagos)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbSeguimientoPagos.save((DominioSeguimientoPagos)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbSeguimientoPagos.update((DominioSeguimientoPagos)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbSeguimientoPagos.delete((DominioSeguimientoPagos)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbSeguimientoPagos.deleteAll(lista);
    }
}
