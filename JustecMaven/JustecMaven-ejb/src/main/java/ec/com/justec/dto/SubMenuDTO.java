package ec.com.justec.dto;

import java.util.List;

public class SubMenuDTO {

	private String valor;
	private List<ItemMenuDTO> items;
	
	public SubMenuDTO() {};
	
	public SubMenuDTO(String valor, List<ItemMenuDTO> items) {
		this.valor = valor;
		this.items = items;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<ItemMenuDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemMenuDTO> items) {
		this.items = items;
	}
}
