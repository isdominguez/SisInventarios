/**
 * Todos los Derechos Reservados © 2013 Java Revolutions.
 * Sistema de Inventarios y Control de Pedidos.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema de Inventarios y Control de Pedidos
 * Nombre de archivo: SessionBeanAccesoriosxproducto.java
 * Fecha de creacion : Noviembre, 2013
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioAccesoriosxproducto;
import com.javarevolutions.siycp.ejb.business.IfaceAccesoriosxproducto;
import java.util.List;;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsAccesoriosxproducto")
public class SessionBeanAccesoriosxproducto {
    @Autowired
    private IfaceAccesoriosxproducto businessAccesoriosxproducto;
    
    @WebMethod(operationName = "getAccesoriosxproducto")
    public List<DominioAccesoriosxproducto> getAccesoriosxproducto() throws Exception {
        return businessAccesoriosxproducto.getAccesoriosxproducto();
    }
    
    @WebMethod(operationName = "getAccesoriosxproductoPagination")
    public List<DominioAccesoriosxproducto> getAccesoriosxproductoPagination(DominioAccesoriosxproducto obj) throws Exception {
        return businessAccesoriosxproducto.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBAccesoriosxproducto")
    public int getRowCountBAccesoriosxproducto() throws Exception {
        return businessAccesoriosxproducto.getRowCount();
    }
    
    @WebMethod(operationName = "save")
    public DominioAccesoriosxproducto save(DominioAccesoriosxproducto obj) {
        return businessAccesoriosxproducto.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioAccesoriosxproducto update(DominioAccesoriosxproducto obj) {
        return businessAccesoriosxproducto.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioAccesoriosxproducto delete(DominioAccesoriosxproducto obj) {
        return businessAccesoriosxproducto.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioAccesoriosxproducto deleteAll(List<DominioAccesoriosxproducto> lista) {
        return businessAccesoriosxproducto.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioAccesoriosxproducto getById(DominioAccesoriosxproducto obj) {
        return businessAccesoriosxproducto.getById(obj);
    }
}
