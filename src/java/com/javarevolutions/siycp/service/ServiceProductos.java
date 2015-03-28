/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: ServiceProductos.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.service;

import com.javarevolutions.siycp.dominio.DominioProductos;
import com.javarevolutions.siycp.ejb.session.SessionBeanProductos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceProductos implements IfaceProductos {
    @Autowired
    private SessionBeanProductos ejbProductos;
    
    @Override
    public List<DominioProductos> getLista() throws Exception {
        return ejbProductos.getProductos();
    }
    
    @Override
    public List getListaPagination(Object obj) throws Exception {
        return ejbProductos.getProductosPagination((DominioProductos)obj);
    }

    @Override
    public int getRowCount() throws Exception {
        return ejbProductos.getRowCountBProductos();
    }
    
    @Override
    public Object getById(Object obj) {
        return ejbProductos.getById((DominioProductos)obj);
    }

    @Override
    public Object save(Object obj) {
        return ejbProductos.save((DominioProductos)obj);
    }

    @Override
    public Object update(Object obj) {
        return ejbProductos.update((DominioProductos)obj);
    }

    @Override
    public Object delete(Object obj) {
        return ejbProductos.delete((DominioProductos)obj);
    }
    
    @Override
    public Object deleteAll(List lista) {
        return ejbProductos.deleteAll(lista);
    }

    @Override
    public List<DominioProductos> getListaByTipo(DominioProductos obj) throws Exception {
        return ejbProductos.getListaByTipo(obj);
    }

    @Override
    public int getRowsByTipo(DominioProductos obj) throws Exception {
        return ejbProductos.getRowsByTipo(obj);
    }

    @Override
    public List<DominioProductos> getProductosPaginationByProveedor(DominioProductos obj) throws Exception {
        return ejbProductos.getProductosPaginationByProveedor((DominioProductos)obj);
    }

    @Override
    public int getRowsByProveedor(DominioProductos obj) throws Exception {
        return ejbProductos.getRowsByProveedor(obj);
    }
}
