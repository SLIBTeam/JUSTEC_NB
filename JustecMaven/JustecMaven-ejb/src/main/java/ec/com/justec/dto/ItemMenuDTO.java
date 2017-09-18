package ec.com.justec.dto;

public class ItemMenuDTO {

	private String valor;
	private String url;
	private String icono;
	
	public ItemMenuDTO() {};
	
	public ItemMenuDTO(String valor, String url, String icono) {
		this.valor = valor;
		this.url = url;
		this.icono = icono;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
	}
}
