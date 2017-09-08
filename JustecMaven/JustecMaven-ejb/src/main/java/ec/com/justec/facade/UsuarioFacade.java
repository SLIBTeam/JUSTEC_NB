/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import ec.com.justec.enumeradores.EstadoEnum;
import ec.com.justec.facade.local.UsuarioFacadeLocal;
import ec.com.justec.modelo.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario obtenerPorNombre(String nombre) {
        Query q = em.createQuery("Select u from Usuario u where u.nombreUs = :nombre and u.estadoUs = :estado");
        q.setParameter("nombre", nombre);
        q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Usuario) q.getResultList().get(0);
        }
    }

}
