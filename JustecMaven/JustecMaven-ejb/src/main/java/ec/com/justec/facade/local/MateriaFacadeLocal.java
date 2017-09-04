/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade.local;

import ec.com.justec.modelo.Materia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface MateriaFacadeLocal {

    void create(Materia materia);

    void edit(Materia materia);

    void remove(Materia materia);

    Materia find(Object id);

    List<Materia> findAll();

    List<Materia> findRange(int[] range);

    int count();
    
}
