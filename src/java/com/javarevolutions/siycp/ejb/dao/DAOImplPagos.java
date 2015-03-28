/**
 * Todos los Derechos Reservados © 2014 Java Revolutions.
 * Sistema Fredy.
 * Este software contiene información propiedad exclusiva de Java Revolutions considerada Confidencial.
 * Queda totalmente prohibido su uso o divulgación en forma parcial o total.
 * ----------------------------------------------------------------------------
 * Nombre de Aplicacion: Sistema Fredy
 * Nombre de archivo: DAOImplPagos.java
 * Fecha de creacion : Febrero, 2014
 * @author : Sergio Alberto Cortés Rios
 * @version 1.0
 *
 * Bitácora de modificaciones:
 * CR/Defecto 		Fecha 			Autor 			Descripción del cambio
 * ----------------------------------------------------------------------------
**/

package com.javarevolutions.siycp.ejb.dao;

import com.javarevolutions.siycp.ejb.entity.Pagos;
import com.javarevolutions.siycp.ejb.entity.SeguimientoPagos;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

public class DAOImplPagos extends DAOGeneric implements DAOPagos {
    @Override
    public List<Pagos> getLista() {
        return getList("from Pagos");
    }
    
    @Override
    @Transactional
    public List<Pagos> getListaPagination(int first, int pageSize) {
        return getList(first, pageSize, "from Pagos");
    }
    
    @Override
    @Transactional
    public void guardar(Object entity) throws Exception {
        insert(entity);
        Pagos pago = (Pagos)entity;
        SeguimientoPagos sp = pago.getSeguimientoPagos();
        sp.setTotalDeuda(sp.getTotalDeuda()-pago.getCantidadPago());
        update(sp);
    }

    @Override
    @Transactional
    public Object actualizar(Object entity) throws Exception {
        return update(entity);
    }

    @Override
    @Transactional
    public void borrar(Object entity) throws Exception {
        delete(entity);
    }
    
    @Override
    @Transactional
    public void borrarAll(List lista) throws Exception {
        try {
            for(Object obj: lista) {
                delete(obj);
            }
        } catch(HibernateException e) {
            System.out.println("ERROR IN DAO deleteAll : "+e);
        }
    }
    
    @Override
    @Transactional
    public Object getById(Object entity, Object value) throws Exception {
        return getObject("from Pagos where idPago = ?", value);
    }

    @Override
    @Transactional
    public List<Pagos> getListaPaginationByIdSeguimientoPago(int first, int pageSize, int idSeguimientoPago) throws Exception {
        return getList(first, pageSize, "from Pagos where seguimientoPagos.idSeguimientoPago = "+idSeguimientoPago);
    }

    @Override
    public int getRowCount(int idSeguimientoPago) throws Exception {
        return getCountLista("select count(*) from Pagos where seguimientoPagos.idSeguimientoPago = "+idSeguimientoPago);
    }

    @Override
    public int getRowCount() throws Exception {
        return getCountLista("select count(*) from Pagos");
    }
}
