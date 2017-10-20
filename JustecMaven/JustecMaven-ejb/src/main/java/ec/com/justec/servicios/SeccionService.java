/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios;

import ec.com.justec.facade.local.SeccionFacadeLocal;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.SeccionServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class SeccionService implements SeccionServiceLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private SeccionFacadeLocal seccionFacade;

    @Override
    public List<Seccion> obtenerSeccionesActivas() {
        return seccionFacade.obtenerSeccionesActivas();
    }
    
    @Override
    public Seccion obtenerXId(Integer id) {
    	return seccionFacade.find(id);
    }
}
