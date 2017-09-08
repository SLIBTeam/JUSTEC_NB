/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade.local;

import ec.com.justec.modelo.Usuario;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface UsuarioServiceLocal {

    /**
     * Metodo que obtiene un usuario activo por nombre
     * @param nombre
     * @return 
     */
    public Usuario obtenerPorNombre(String nombre);
    
}
