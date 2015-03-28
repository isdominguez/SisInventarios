/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: ServiceVentas.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioVentas;
import com.javarevolutions.siycp.ejb.session.SessionBeanVentas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceVentas implements IfaceVentas {
    @Autowired
    private SessionBeanVentas ejbVentas;
    
    @Override
    public List<DominioVentas> getLista() throws Exception {
        return ejbVentas.getVentas();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbVentas.getVentasPagination((DominioVentas)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbVentas.getRowCountBVentas();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbVentas.getById((DominioVentas)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbVentas.save((DominioVentas)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbVentas.update((DominioVentas)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbVentas.delete((DominioVentas)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbVentas.deleteAll(lista);
    }
    
    @Override
    public List<DominioVentas> getVentasByDia(DominioVentas obj) throws Exception {
        return ejbVentas.getVentasByDia(obj);
    }

    @Override
    public List<DominioVentas> getVentasByFechas(DominioVentas obj) throws Exception {
        return ejbVentas.getVentasByFechas(obj);
    }

    @Override
    public int getRowCountByDia(DominioVentas obj) throws Exception {
        return ejbVentas.getRowCountByDia(obj);
    }

    @Override
    public int getRowCountByFechas(DominioVentas obj) throws Exception {
        return ejbVentas.getRowCountByFechas(obj);
    }
}
