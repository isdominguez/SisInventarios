/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: SessionBeanSeguimientoPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioSeguimientoPagos;
import com.javarevolutions.siycp.ejb.business.IfaceSeguimientoPagos;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsSeguimientoPagos")
public class SessionBeanSeguimientoPagos {
    @Autowired
    private IfaceSeguimientoPagos businessSeguimientoPagos;
    
    @WebMethod(operationName = "getSeguimientoPagos")
    public List<DominioSeguimientoPagos> getSeguimientoPagos() throws Exception {
        return businessSeguimientoPagos.getSeguimientoPagos();
    }
    
    @WebMethod(operationName = "getSeguimientoPagosPagination")
    public List<DominioSeguimientoPagos> getSeguimientoPagosPagination(DominioSeguimientoPagos obj) throws Exception {
        return businessSeguimientoPagos.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBSeguimientoPagos")
    public int getRowCountBSeguimientoPagos() throws Exception {
        return businessSeguimientoPagos.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioSeguimientoPagos save(DominioSeguimientoPagos obj) {
        return businessSeguimientoPagos.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioSeguimientoPagos update(DominioSeguimientoPagos obj) {
        return businessSeguimientoPagos.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioSeguimientoPagos delete(DominioSeguimientoPagos obj) {
        return businessSeguimientoPagos.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioSeguimientoPagos deleteAll(List<DominioSeguimientoPagos> lista) {
        return businessSeguimientoPagos.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioSeguimientoPagos getById(DominioSeguimientoPagos obj) {
        return businessSeguimientoPagos.getById(obj);
    }
}
