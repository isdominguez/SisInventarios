/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceTiposMedidas.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioTiposMedidas;
import com.javarevolutions.siycp.ejb.session.SessionBeanTiposMedidas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTiposMedidas implements IfaceTiposMedidas {
    @Autowired
    private SessionBeanTiposMedidas ejbTiposMedidas;
    
    @Override
    public List<DominioTiposMedidas> getLista() throws Exception {
        return ejbTiposMedidas.getTiposMedidas();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbTiposMedidas.getTiposMedidasPagination((DominioTiposMedidas)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbTiposMedidas.getRowCountBTiposMedidas();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbTiposMedidas.getById((DominioTiposMedidas)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbTiposMedidas.save((DominioTiposMedidas)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbTiposMedidas.update((DominioTiposMedidas)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbTiposMedidas.delete((DominioTiposMedidas)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbTiposMedidas.deleteAll(lista);
    }
}
