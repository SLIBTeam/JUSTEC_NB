/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import ec.com.justec.facade.local.DocumentoFacadeLocal;
import ec.com.justec.modelo.Documento;
import ec.com.justec.servicios.local.DocumentoServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class DocumentoService implements DocumentoServiceLocal {

    @EJB
    private DocumentoFacadeLocal documentoFacade;
    
    @Override
    public List<Documento> obtenerTodoDocumento()
    {
        return documentoFacade.obtenerTodoDocumento();
    }
            
    
}
