/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: ServiceVentasProductos.java
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
import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import com.javarevolutions.siycp.ejb.session.SessionBeanVentasProductos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceVentasProductos implements IfaceVentasProductos {
    @Autowired
    private SessionBeanVentasProductos ejbVentasProductos;
    
    @Override
    public List<DominioVentasProductos> getLista() throws Exception {
        return ejbVentasProductos.getVentasProductos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbVentasProductos.getVentasProductosPagination((DominioVentasProductos)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbVentasProductos.getRowCountBVentasProductos();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbVentasProductos.getById((DominioVentasProductos)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbVentasProductos.save((DominioVentasProductos)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbVentasProductos.update((DominioVentasProductos)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbVentasProductos.delete((DominioVentasProductos)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbVentasProductos.deleteAll(lista);
    }

    @Override
    public DominioVentasProductos saveAllProdsPedidos(List<DominioVentasProductos> lista, DominioVentas venta) {
        return ejbVentasProductos.saveAllProdsPedidos(lista, venta);
    }

    @Override
    public DominioVentasProductos updateAllProdsPedidos(List<DominioVentasProductos> listaAdd, List<DominioVentasProductos> listaDel, DominioVentas venta) {
        return ejbVentasProductos.updateAllProdsPedidos(listaAdd, listaDel, venta);
    }

    @Override
    public List<DominioVentasProductos> getListaBorrar(DominioVentasProductos obj) throws Exception {
        return ejbVentasProductos.getListaBorrar(obj);
    }
}
