/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.facade.local;

import ec.com.justec.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene usuario activo por nombre
     * @param nombre
     * @return 
     */
    public Usuario obtenerPorNombre(String nombre);
    
    
    /**
     * Método que obtiene un usuario por identificacion
     * @param identificacion
     * @param estado
     * @return objeto Usuario
     */
    public Usuario obtenerPorIdentificacion(String identificacion, String estado);
    
}
