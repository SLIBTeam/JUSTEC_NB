/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.com.justec.facade.local.PaisFacadeLocal;
import ec.com.justec.modelo.Pais;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class PaisFacade extends AbstractFacade<Pais> implements PaisFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisFacade() {
        super(Pais.class);
    }
    
    public Pais obtenerPorCodigoUnico(String codigoUnicoPais) {
    	Pais resultado = null;
    	try {
    		 Query q = em.createQuery("Select s from Pais s where s.codigoUnicoPais = :codigoUnicoPais ");
    	     q.setParameter("codigoUnicoPais", codigoUnicoPais);
    	     
    	     resultado = (Pais) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = null;
		}
       
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
	public List<Pais> listarPaisesPorCodigoIndicador(Integer codigoIndicador, String estado) {
		List<Pais> resultado = new ArrayList<Pais>();
		try {
			Query q = em.createQuery(
					"Select distinct s.pais from IndicadorValores s where s.indicador.codigoIndicador = :codigoIndicador "
							+ " and s.estado = :estado ");

			q.setParameter("codigoIndicador", codigoIndicador);
			q.setParameter("estado", estado);

			resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = new ArrayList<Pais>();
		}

		return resultado;
	}
    
}
