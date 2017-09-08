/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import ec.com.justec.modelo.ResultadoBusqueda;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ResultadoBusquedaServiceLocal {

    /**
     * Metodo que persiste un registro
     * @param resultadoBusqueda 
     */
    public void crear(ResultadoBusqueda resultadoBusqueda);
    
}
