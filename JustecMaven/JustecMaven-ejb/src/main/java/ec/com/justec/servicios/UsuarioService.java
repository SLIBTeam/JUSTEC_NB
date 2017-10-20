/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import ec.com.justec.facade.local.UsuarioFacadeLocal;
import ec.com.justec.facade.local.UsuarioServiceLocal;
import ec.com.justec.modelo.Usuario;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @Override
    public Usuario obtenerPorNombre(String nombre) {
        return usuarioFacade.obtenerPorNombre(nombre);
    }
    
    public Usuario obtenerPorIdentificacion(String identificacion, String estado) {
    	return usuarioFacade.obtenerPorIdentificacion(identificacion, estado);
    }
}
