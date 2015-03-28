/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: SessionBeanVentasProductos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioVentas;
import com.javarevolutions.siycp.dominio.DominioVentasProductos;
import com.javarevolutions.siycp.ejb.business.IfaceVentasProductos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsVentasProductos")
public class SessionBeanVentasProductos {
    @Autowired
    private IfaceVentasProductos businessVentasProductos;
    
    @WebMethod(operationName = "getVentasProductos")
    public List<DominioVentasProductos> getVentasProductos() throws Exception {
        return businessVentasProductos.getVentasProductos();
    }
    
    @WebMethod(operationName = "getVentasProductosPagination")
    public List<DominioVentasProductos> getVentasProductosPagination(DominioVentasProductos obj) throws Exception {
        return businessVentasProductos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBVentasProductos")
    public int getRowCountBVentasProductos() throws Exception {
        return businessVentasProductos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioVentasProductos save(DominioVentasProductos obj) {
        return businessVentasProductos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioVentasProductos update(DominioVentasProductos obj) {
        return businessVentasProductos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioVentasProductos delete(DominioVentasProductos obj) {
        return businessVentasProductos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioVentasProductos deleteAll(List<DominioVentasProductos> lista) {
        return businessVentasProductos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioVentasProductos getById(DominioVentasProductos obj) {
        return businessVentasProductos.getById(obj);
    }
    
    @WebMethod(operationName = "saveAllProdsPedidos")
    public DominioVentasProductos saveAllProdsPedidos(List<DominioVentasProductos> lista, DominioVentas venta) {
        return businessVentasProductos.saveAllProdsPedidos(lista, venta);
    }

        
    @WebMethod(operationName = "updateAllProdsPedidos")
    public DominioVentasProductos updateAllProdsPedidos(List<DominioVentasProductos> listaAdd, 
            List<DominioVentasProductos> listaDel, DominioVentas venta) {
        return businessVentasProductos.updateAllProdsPedidos(listaAdd, listaDel, venta);
    }

    @WebMethod(operationName = "getListaBorrar")
    public List<DominioVentasProductos> getListaBorrar(DominioVentasProductos obj) throws Exception {
        return businessVentasProductos.getListaBorrar(obj);
    }
}
