/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: SessionBeanVentas.java
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
import com.javarevolutions.siycp.ejb.business.IfaceVentas;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsVentas")
public class SessionBeanVentas {
    @Autowired
    private IfaceVentas businessVentas;
    
    @WebMethod(operationName = "getVentas")
    public List<DominioVentas> getVentas() throws Exception {
        return businessVentas.getVentas();
    }
    
    @WebMethod(operationName = "getVentasPagination")
    public List<DominioVentas> getVentasPagination(DominioVentas obj) throws Exception {
        return businessVentas.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBVentas")
    public int getRowCountBVentas() throws Exception {
        return businessVentas.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioVentas save(DominioVentas obj) {
        return businessVentas.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioVentas update(DominioVentas obj) {
        return businessVentas.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioVentas delete(DominioVentas obj) {
        return businessVentas.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioVentas deleteAll(List<DominioVentas> lista) {
        return businessVentas.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioVentas getById(DominioVentas obj) {
        return businessVentas.getById(obj);
    }
    
    @WebMethod(operationName = "getVentasByDia")
    public List<DominioVentas> getVentasByDia(DominioVentas obj) throws Exception {
        return businessVentas.getVentasByDia(obj);
    }
    
    @WebMethod(operationName = "getVentasByFechas")
    public List<DominioVentas> getVentasByFechas(DominioVentas obj) throws Exception {
        return businessVentas.getVentasByFechas(obj);
    }
    
    @WebMethod(operationName = "getRowCountByDia")
    public int getRowCountByDia(DominioVentas obj) throws Exception {
        return businessVentas.getRowCountByDia(obj);
    }
    
    @WebMethod(operationName = "getRowCountByFechas")
    public int getRowCountByFechas(DominioVentas obj) throws Exception {
        return businessVentas.getRowCountByFechas(obj);
    }
}
