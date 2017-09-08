/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import ec.com.justec.enumeradores.EstadoEnum;
import ec.com.justec.facade.local.DocumentoFacadeLocal;
import ec.com.justec.modelo.Documento;
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
public class DocumentoFacade extends AbstractFacade<Documento> implements DocumentoFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentoFacade() {
        super(Documento.class);
    }

    @Override
    public List<Documento> obtenerTodoDocumento() {
        Query q = em.createQuery("Select d from Documento d left join fetch d.codigoTn left join fetch d.codigoTp left join fetch d.codigoMat  where d.estadoDoc = :estado");
        q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
        return q.getResultList();
    }

}
