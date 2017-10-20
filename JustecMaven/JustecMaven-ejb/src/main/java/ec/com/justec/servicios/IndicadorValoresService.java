/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.justec.modelo.IndicadorValores;
import ec.com.justec.servicios.local.IndicadorValoresServiceLocal;


@Stateless
public class IndicadorValoresService implements IndicadorValoresServiceLocal {

    @EJB
    private ec.com.justec.facade.local.IndicadorValoresFacadeLocal indicadorValoresFacade;

    public IndicadorValores obtenerPorCodGeneradoIndicadorAnioCodPais(String codigoGeneradoIndicador, int anio, String codPais, String estado) {
    	return indicadorValoresFacade.obtenerPorCodGeneradoIndicadorAnioCodPais(codigoGeneradoIndicador, anio, codPais, estado);
    }
    
    public void guardar(IndicadorValores valor) {
    	try {
    		indicadorValoresFacade.create(valor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    public void actualizar(IndicadorValores valor) {
    	try {
    		indicadorValoresFacade.edit(valor);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
    
    public List<Integer> listarAniosPorCodigoIndicador(Integer codigoIndicador, String estado){
    	return indicadorValoresFacade.listarAniosPorCodigoIndicador(codigoIndicador, estado);
    }
    
    public List<IndicadorValores> listarvaloresPorCodigoIndicador(Integer codigoIndicador, String estado){
    	return indicadorValoresFacade.listarvaloresPorCodigoIndicador(codigoIndicador, estado);
    }
}
