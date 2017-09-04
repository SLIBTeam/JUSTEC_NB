/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.controlador;

import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.SeccionServiceLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author luisp.araujo
 */
@Named(value = "seccionControlador")
@ViewScoped
public class SeccionControlador implements Serializable{

    @EJB
    private SeccionServiceLocal seccionService;
    /**
     * Creates a new instance of SeccionControlador
     */
    public SeccionControlador() {
    }
    
    public void obtenerSecciones()
    {
        List<Seccion> secciones = seccionService.obtenerSeccionesActivas();
        for(Seccion s : secciones)
        {
            System.out.println("seccion: "+s.getNombreSec());
        }
    }
}
