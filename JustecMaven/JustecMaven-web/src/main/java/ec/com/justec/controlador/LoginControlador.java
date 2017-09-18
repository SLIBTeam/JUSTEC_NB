/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ViewScoped
public class LoginControlador extends BaseControlador implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8967637270135230046L;
	
	@Inject
	private SesionControlador sesionControlador;
	
	private String usuario;
	private String contrasenia;
	private Boolean logueoIncorrecto;


    @PostConstruct
    private void init() {
    	
    }
    
    public void iniciarSesion() {
    	logueoIncorrecto = null;
    	if(usuario.equals("admin") && contrasenia.equals("admin123")) {
    		sesionControlador.setNombreUsuario("Luis Falcones");
    		sesionControlador.setLogueoCorrecto(true);
    		redireccionarPagina("/faces/paginas/buscador.xhtml");
    	}else {
    		logueoIncorrecto = true;
		}
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Boolean getLogueoIncorrecto() {
		return logueoIncorrecto;
	}

	public void setLogueoIncorrecto(Boolean logueoIncorrecto) {
		this.logueoIncorrecto = logueoIncorrecto;
	}

    
}
