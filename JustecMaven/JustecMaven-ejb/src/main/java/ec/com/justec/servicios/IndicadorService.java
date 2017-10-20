/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.justec.facade.local.IndicadorFacadeLocal;
import ec.com.justec.modelo.Indicador;
import ec.com.justec.servicios.local.IndicadorServiceLocal;


@Stateless
public class IndicadorService implements IndicadorServiceLocal {

    @EJB
    private IndicadorFacadeLocal indicadorFacadeLocal;

    public Indicador obtenerPorCodGenerado(String codigoGeneradoIndicador, String estado) {
    	return indicadorFacadeLocal.obtenerPorCodGenerado(codigoGeneradoIndicador, estado);
    }
    
    public List<Indicador> listarPorCodSeccion(Integer codigoSec, String estado){
    	return indicadorFacadeLocal.listarPorCodSeccion(codigoSec, estado);
    }
   
}
