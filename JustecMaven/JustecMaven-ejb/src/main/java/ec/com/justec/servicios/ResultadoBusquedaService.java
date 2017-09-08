/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import ec.com.justec.facade.local.ResultadoBusquedaFacadeLocal;
import ec.com.justec.modelo.ResultadoBusqueda;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ec.com.justec.servicios.local.ResultadoBusquedaServiceLocal;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class ResultadoBusquedaService implements ResultadoBusquedaServiceLocal {

    @EJB
    private ResultadoBusquedaFacadeLocal resultadoBusquedaFacade;
    
    @Override
    public void crear(ResultadoBusqueda resultadoBusqueda)
    {
        resultadoBusquedaFacade.create(resultadoBusqueda);
    }
}
