/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: SessionBeanPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioPagos;
import com.javarevolutions.siycp.ejb.business.IfacePagos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsPagos")
public class SessionBeanPagos {
    @Autowired
    private IfacePagos businessPagos;
    
    @WebMethod(operationName = "getPagos")
    public List<DominioPagos> getPagos() throws Exception {
        return businessPagos.getPagos();
    }
    
    @WebMethod(operationName = "getPagosPagination")
    public List<DominioPagos> getPagosPagination(DominioPagos obj) throws Exception {
        return businessPagos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBPagos")
    public int getRowCountBPagos(DominioPagos obj) throws Exception {
        return businessPagos.getRowCount(obj.getSeguimientoPagos().getIdSeguimientoPago());
    }
    
    @WebMethod(operationName = "save")
    public DominioPagos save(DominioPagos obj) {
        return businessPagos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioPagos update(DominioPagos obj) {
        return businessPagos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioPagos delete(DominioPagos obj) {
        return businessPagos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioPagos deleteAll(List<DominioPagos> lista) {
        return businessPagos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioPagos getById(DominioPagos obj) {
        return businessPagos.getById(obj);
    }
}
