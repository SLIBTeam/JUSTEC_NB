/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import ec.com.justec.modelo.Documento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface DocumentoServiceLocal {

    /**
     * Metodo que obtiene todos los documentos activos
     * @return 
     */
    public List<Documento> obtenerTodoDocumento();
    
}
