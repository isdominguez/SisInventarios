/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: SessionBeanHorarioEmpleados.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.session;

import com.javarevolutions.siycp.dominio.DominioHorarioEmpleados;
import com.javarevolutions.siycp.dominio.DominioUsuarios;
import com.javarevolutions.siycp.ejb.business.IfaceHorarioEmpleados;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(serviceName = "wsHorarioEmpleados")
public class SessionBeanHorarioEmpleados {
    @Autowired
    private IfaceHorarioEmpleados businessHorarioEmpleados;
    
    @WebMethod(operationName = "getHorarioEmpleados")
    public List<DominioHorarioEmpleados> getHorarioEmpleados() throws Exception {
        return businessHorarioEmpleados.getHorarioEmpleados();
    }
    
    @WebMethod(operationName = "getHorarioEmpleadosPagination")
    public List<DominioHorarioEmpleados> getHorarioEmpleadosPagination(DominioHorarioEmpleados obj) throws Exception {
        return businessHorarioEmpleados.getListaPagination(obj);
    }
    
    @WebMethod(operationName = "getRowCountBHorarioEmpleados")
    public int getRowCountBHorarioEmpleados(DominioUsuarios user) throws Exception {
        return businessHorarioEmpleados.getRowCountByUser(user.getIdUsuario());
    }
    
    @WebMethod(operationName = "save")
    public DominioHorarioEmpleados save(DominioHorarioEmpleados obj) {
        return businessHorarioEmpleados.save(obj);
    }
    
    @WebMethod(operationName = "update")
    public DominioHorarioEmpleados update(DominioHorarioEmpleados obj) {
        return businessHorarioEmpleados.update(obj);
    }
    
    @WebMethod(operationName = "delete")
    public DominioHorarioEmpleados delete(DominioHorarioEmpleados obj) {
        return businessHorarioEmpleados.delete(obj);
    }
    
    @WebMethod(operationName = "deleteAll")
    public DominioHorarioEmpleados deleteAll(List<DominioHorarioEmpleados> lista) {
        return businessHorarioEmpleados.deleteAll(lista);
    }
    
    @WebMethod(operationName = "getById")
    public DominioHorarioEmpleados getById(DominioHorarioEmpleados obj) {
        return businessHorarioEmpleados.getById(obj);
    }
}
