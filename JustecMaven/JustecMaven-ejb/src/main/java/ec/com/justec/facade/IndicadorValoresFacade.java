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

import ec.com.justec.facade.local.IndicadorValoresFacadeLocal;
import ec.com.justec.modelo.IndicadorValores;

@Stateless
public class IndicadorValoresFacade extends AbstractFacade<IndicadorValores> implements IndicadorValoresFacadeLocal {

    @PersistenceContext(unitName = "ec.gob.educacion_JustecMaven-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicadorValoresFacade() {
        super(IndicadorValores.class);
    }

    /*
     * (non-Javadoc)
     * @see ec.com.justec.facade.local.IndicadorValoresServiceLocal#obtenerPorCodGeneradoIndicadorAnioCodPais(java.lang.String, int, java.lang.String, int)
     */
    @Override
    public IndicadorValores obtenerPorCodGeneradoIndicadorAnioCodPais(String codigoGeneradoIndicador, int anio, String codPais, String estado) {
    	IndicadorValores resultado = null;
    	try {
    		 Query q = em.createQuery("Select s from IndicadorValores s where s.indicador.codigoGeneradoIndicador = :codigoGeneradoIndicador "
    		 		+ " and s.anio = :anio "
    		 		+ " and s.pais.codigoUnicoPais = :codPais "
    		 		+ " and s.estado = :estado ");
    		 
    	     q.setParameter("codigoGeneradoIndicador", codigoGeneradoIndicador);
    	     q.setParameter("anio", anio);
    	     q.setParameter("codPais", codPais);
    	     q.setParameter("estado", estado);
    	     
    	     resultado = (IndicadorValores) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = null;
		}
       
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
	public List<Integer> listarAniosPorCodigoIndicador(Integer codigoIndicador, String estado){
    	List<Integer> resultado = new ArrayList<Integer>();
    	try {
    		 Query q = em.createQuery("Select distinct s.anio from IndicadorValores s where s.indicador.codigoIndicador = :codigoIndicador "
    		 		+ " and s.estado = :estado order by s.anio asc ");
    		 
    	     q.setParameter("codigoIndicador", codigoIndicador);
    	     q.setParameter("estado", estado);
    	     
    	     resultado = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			resultado = new ArrayList<Integer>();
		}
       
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
	public List<IndicadorValores> listarvaloresPorCodigoIndicador(Integer codigoIndicador, String estado){
    	List<IndicadorValores> resultado = new ArrayList<IndicadorValores>();
    	try {
    		 Query q = em.createQuery("Select s from IndicadorValores s where s.indicador.codigoIndicador = :codigoIndicador "
    		 		+ " and s.estado = :estado order by s.valor desc ");
    		 
    	     q.setParameter("codigoIndicador", codigoIndicador);
    	     q.setParameter("estado", estado);
    	     
    	     resultado = q.getResultList();
    	     for (IndicadorValores indicadorValores : resultado) {
				indicadorValores.getIndicador().getCodigoGeneradoIndicador();
				indicadorValores.getIndicador().getCodigoSec();
				indicadorValores.getPais().getCodigoUnicoPais();
				indicadorValores.getPais().getCodigoPais();
				indicadorValores.getPais().getNombrePais();
			}
		} catch (Exception e) {
			// TODO: handle exception
			resultado = new ArrayList<IndicadorValores>();
		}
       
        return resultado;
    }

}
