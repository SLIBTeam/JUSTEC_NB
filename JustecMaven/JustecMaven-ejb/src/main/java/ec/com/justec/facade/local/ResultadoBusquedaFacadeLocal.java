/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade.local;

import ec.com.justec.modelo.ResultadoBusqueda;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ResultadoBusquedaFacadeLocal {

    void create(ResultadoBusqueda resultadoBusqueda);

    void edit(ResultadoBusqueda resultadoBusqueda);

    void remove(ResultadoBusqueda resultadoBusqueda);

    ResultadoBusqueda find(Object id);

    List<ResultadoBusqueda> findAll();

    List<ResultadoBusqueda> findRange(int[] range);

    int count();
    
}
