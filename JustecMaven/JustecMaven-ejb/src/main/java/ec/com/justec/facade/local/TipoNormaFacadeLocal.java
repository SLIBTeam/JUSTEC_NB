/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade.local;

import ec.com.justec.modelo.TipoNorma;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface TipoNormaFacadeLocal {

    void create(TipoNorma tipoNorma);

    void edit(TipoNorma tipoNorma);

    void remove(TipoNorma tipoNorma);

    TipoNorma find(Object id);

    List<TipoNorma> findAll();

    List<TipoNorma> findRange(int[] range);

    int count();
    
}
