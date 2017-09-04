/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import ec.com.justec.facade.local.TipoPublicacionFacadeLocal;
import ec.com.justec.modelo.TipoPublicacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class TipoPublicacionFacade extends AbstractFacade<TipoPublicacion> implements TipoPublicacionFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPublicacionFacade() {
        super(TipoPublicacion.class);
    }
    
}
