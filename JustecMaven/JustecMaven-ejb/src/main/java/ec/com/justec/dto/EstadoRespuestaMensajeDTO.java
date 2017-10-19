package ec.com.justec.dto;


public class EstadoRespuestaMensajeDTO {


	private String mensaje;
	private boolean respuesta;
	private boolean respuestaAlterna;
	
	
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isRespuesta() {
		return respuesta;
	}
	public void setRespuesta(boolean respuesta) {
		this.respuesta = respuesta;
	}
	public boolean isRespuestaAlterna() {
		return respuestaAlterna;
	}
	public void setRespuestaAlterna(boolean respuestaAlterna) {
		this.respuestaAlterna = respuestaAlterna;
	}
	
	
}
