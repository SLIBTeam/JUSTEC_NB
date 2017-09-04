/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import ec.com.justec.facade.local.SeccionFacadeLocal;
import ec.com.justec.modelo.Seccion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class SeccionFacade extends AbstractFacade<Seccion> implements SeccionFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeccionFacade() {
        super(Seccion.class);
    }

    @Override
    public List<Seccion> obtenerSeccionesActivas() {
        Query q = em.createQuery("Select s from Seccion s where s.estadoSec = 'A'");
        return q.getResultList();
    }

}
