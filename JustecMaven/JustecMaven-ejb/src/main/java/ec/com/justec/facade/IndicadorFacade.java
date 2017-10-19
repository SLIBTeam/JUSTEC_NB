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

import ec.com.justec.facade.local.IndicadorFacadeLocal;
import ec.com.justec.modelo.Indicador;

@Stateless
public class IndicadorFacade extends AbstractFacade<Indicador> implements IndicadorFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorFacade() {
        super(Indicador.class);
    }

   
    @Override
    public Indicador obtenerPorCodGenerado(String codigoGeneradoIndicador, String estado) {
    	Indicador resultado = null;
    	try {
    		 Query q = em.createQuery("Select s from Indicador s where s.codigoGeneradoIndicador = :codigoGeneradoIndicador "
    		 		+ " and s.estado = :estado ");
    	     q.setParameter("codigoGeneradoIndicador", codigoGeneradoIndicador);
    	     q.setParameter("estado", estado);
    	     
    	     resultado = (Indicador) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = null;
		}
       
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
	public List<Indicador> listarPorCodSeccion(Integer codigoSec, String estado){
    	List<Indicador> resultado = new ArrayList<Indicador>();
    	try {
    		 Query q = em.createQuery("Select s from Indicador s where s.codigoSec.codigoSec = :codigoSec "
    		 		+ " and s.estado = :estado ");
    	     q.setParameter("codigoSec", codigoSec);
    	     q.setParameter("estado", estado);
    	     
    	     resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = new ArrayList<Indicador>();
		}
       
        return resultado;
    }

}
