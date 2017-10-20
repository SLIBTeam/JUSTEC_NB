package ec.com.justec.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SesionControlador extends BaseControlador implements Serializable {
	
	private static final long serialVersionUID = 3463016173694116536L;

	
	private String nombreUsuario;
	private boolean logueoCorrecto;
	private String identificacionUsuario;

	
	@PostConstruct
    private void init() {
		
    }
	
	public void cerrarSesion() {
		logueoCorrecto = false;
		getHttpRequest().getSession().invalidate();
		redireccionarPagina("/faces/paginas/login.xhtml");
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public boolean isLogueoCorrecto() {
		return logueoCorrecto;
	}


	public void setLogueoCorrecto(boolean logueoCorrecto) {
		this.logueoCorrecto = logueoCorrecto;
	}

	public String getIdentificacionUsuario() {
		return identificacionUsuario;
	}

	public void setIdentificacionUsuario(String identificacionUsuario) {
		this.identificacionUsuario = identificacionUsuario;
	}

	
}
