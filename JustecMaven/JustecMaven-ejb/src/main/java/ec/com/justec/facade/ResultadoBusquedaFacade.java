/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import ec.com.justec.facade.local.ResultadoBusquedaFacadeLocal;
import ec.com.justec.modelo.ResultadoBusqueda;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class ResultadoBusquedaFacade extends AbstractFacade<ResultadoBusqueda> implements ResultadoBusquedaFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoBusquedaFacade() {
        super(ResultadoBusqueda.class);
    }
    
}
