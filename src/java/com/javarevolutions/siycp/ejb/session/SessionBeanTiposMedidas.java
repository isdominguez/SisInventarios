/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanTiposMedidas.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioTiposMedidas;
import com.javarevolutions.siycp.ejb.business.IfaceTiposMedidas;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsTiposMedidas")
public class SessionBeanTiposMedidas {
    @Autowired
    private IfaceTiposMedidas businessTiposMedidas;
    
    @WebMethod(operationName = "getTiposMedidas")
    public List<DominioTiposMedidas> getTiposMedidas() throws Exception {
        return businessTiposMedidas.getTiposMedidas();
    }
    
    @WebMethod(operationName = "getTiposMedidasPagination")
    public List<DominioTiposMedidas> getTiposMedidasPagination(DominioTiposMedidas obj) throws Exception {
        return businessTiposMedidas.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBTiposMedidas")
    public int getRowCountBTiposMedidas() throws Exception {
        return businessTiposMedidas.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioTiposMedidas save(DominioTiposMedidas obj) {
        return businessTiposMedidas.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioTiposMedidas update(DominioTiposMedidas obj) {
        return businessTiposMedidas.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioTiposMedidas delete(DominioTiposMedidas obj) {
        return businessTiposMedidas.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioTiposMedidas deleteAll(List<DominioTiposMedidas> lista) {
        return businessTiposMedidas.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioTiposMedidas getById(DominioTiposMedidas obj) {
        return businessTiposMedidas.getById(obj);
    }
}
