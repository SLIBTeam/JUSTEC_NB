package ec.com.justec.recursos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.com.justec.dto.ItemMenuDTO;
import ec.com.justec.dto.SubMenuDTO;

@Named
@RequestScoped
public class MenuBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3170588724362450437L;
	private MenuModel menuModelVertical;
	private MenuModel menuModelHorizontal;
    
    @PostConstruct
    public void init(){
        //creating menu
    	menuModelVertical = new DefaultMenuModel();
    	menuModelHorizontal = new DefaultMenuModel();
        generateMenu(menuModelVertical, true);
        generateMenu(menuModelHorizontal, false);
    }
    
    
    /**
     * Método que genera un menú PrimeFaces a partir de un menú guardado por roles
     * @param menuModel
     * @param esVertical
     */
    public void generateMenu(MenuModel menuModel, boolean esVertical) {
    	List<SubMenuDTO> menuGuardado = menuQuemado();
    	for (SubMenuDTO subMenuDTO : menuGuardado) {
    		generarSubMenu(menuModel, subMenuDTO, esVertical);
		}
    }
    
    /**
     * Método que genera y agrega un submenú a un menú de PrimeFaces
     * @param menuModel
     * @param subMenuDTO
     * @param esVertical
     */
    public void generarSubMenu(MenuModel menuModel, SubMenuDTO subMenuDTO, boolean esVertical) {
    	DefaultSubMenu submenu = new DefaultSubMenu(((subMenuDTO.getValor()!=null && !subMenuDTO.getValor().isEmpty())?subMenuDTO.getValor():Constantes.NOMBRE_DEFECTO_SUB_MENU));
    	for (ItemMenuDTO itemMenuDTO : subMenuDTO.getItems()) {
    		submenu.addElement(generarMenuItem(itemMenuDTO, esVertical));
		}
    	if(esVertical) {
    		submenu.setStyleClass(Constantes.ESTILO_DEFECTO_SUB_MENU_VERTICAL);
    	}
    	menuModel.addElement(submenu);
    }
    
    /**
     * Método que genera el item de un menú PrimeFaces
     * @param item
     * @param esVertical
     * @return objeto de tipo DefaultMenuItem
     */
    public DefaultMenuItem generarMenuItem(ItemMenuDTO item, boolean esVertical) {
    	DefaultMenuItem itemMenu = new DefaultMenuItem(((item.getValor()!=null && !item.getValor().isEmpty())?item.getValor():Constantes.NOMBRE_DEFECTO_ITEM_MENU), 
    			((item.getIcono()!=null && !item.getIcono().isEmpty())?item.getIcono():Constantes.ICONO_DEFECTO_MENU), 
    			((item.getUrl()!=null && !item.getUrl().isEmpty())?item.getUrl():pathPagina(Constantes.PAGINA_DEFECTO_MENU)));
    	if(esVertical) {
    		itemMenu.setStyleClass(Constantes.ESTILO_DEFECTO_ITEM_MENU_VERTICAL);
    	}
    	return itemMenu;
    }
    
    /**
     * Método para generar un menú quemado
     * @return lista de objetos SubMenuDTO
     */
    public List<SubMenuDTO> menuQuemado() {
    	List<SubMenuDTO> menu = new ArrayList<SubMenuDTO>();
    	List<ItemMenuDTO> items = new ArrayList<ItemMenuDTO>();
    	//primer menu
    	ItemMenuDTO item = new ItemMenuDTO("Favoritos", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-star");
    	items.add(item);
    	item = new ItemMenuDTO("Buscar", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-search");
    	items.add(item);
    	SubMenuDTO submenu = new SubMenuDTO("Búsqueda" , items);
    	menu.add(submenu);
    	//segundo menu
    	items = new ArrayList<ItemMenuDTO>();
    	item = new ItemMenuDTO("Sección 1", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-tag");
    	items.add(item);
    	item = new ItemMenuDTO("Sección 2", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-tag");
    	items.add(item);
    	item = new ItemMenuDTO("Sección 3", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-tag");
    	items.add(item);
    	submenu = new SubMenuDTO("Secciones" , items);
    	menu.add(submenu);
    	//tercer menu
    	items  = new ArrayList<ItemMenuDTO>();
    	item = new ItemMenuDTO("Ecuador", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-radio-on");
    	items.add(item);
    	item = new ItemMenuDTO("Chile", pathPagina("/faces/paginas/buscador.xhtml"), "ui-icon-radio-on");
    	items.add(item);
    	submenu = new SubMenuDTO("Países" , items);
    	menu.add(submenu);
    	return menu;
    }
    
    public String pathPagina(String pagina) {
    	return  FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + pagina;
    }
    
    // getters and setters


	public MenuModel getMenuModelVertical() {
		return menuModelVertical;
	}


	public void setMenuModelVertical(MenuModel menuModelVertical) {
		this.menuModelVertical = menuModelVertical;
	}


	public MenuModel getMenuModelHorizontal() {
		return menuModelHorizontal;
	}


	public void setMenuModelHorizontal(MenuModel menuModelHorizontal) {
		this.menuModelHorizontal = menuModelHorizontal;
	}
}
