/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.justec.facade.local.PaisFacadeLocal;
import ec.com.justec.modelo.Pais;
import ec.com.justec.servicios.local.PaisServiceLocal;


@Stateless
public class PaisService implements PaisServiceLocal {

    @EJB
    private PaisFacadeLocal paisFacadeLocal;

    public Pais obtenerPorCodigoUnico(String codigoUnicoPais) {
    	return paisFacadeLocal.obtenerPorCodigoUnico(codigoUnicoPais);
    }
    
    public List<Pais> listarPaisesPorCodigoIndicador(Integer codigoIndicador, String estado){
    	return paisFacadeLocal.listarPaisesPorCodigoIndicador(codigoIndicador, estado);
    }
   
}
