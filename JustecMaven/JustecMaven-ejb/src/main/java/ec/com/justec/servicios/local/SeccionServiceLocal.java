/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import ec.com.justec.modelo.Seccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface SeccionServiceLocal {

    /**
     * Metodo que obtiene todas las secciones activas
     * @return 
     */
    public List<Seccion> obtenerSeccionesActivas();

    /**
     * Metodo que obtiene la seccion por su id
     * @param id
     * @return
     */
	public Seccion obtenerXId(Integer id);
    
}
