package ec.com.justec.recursos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ec.com.justec.dto.ItemMenuDTO;
import ec.com.justec.dto.SubMenuDTO;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.SeccionServiceLocal;

@Named
@RequestScoped
public class MenuBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3170588724362450437L;
	private MenuModel menuModelVertical;
	private MenuModel menuModelHorizontal;
	private List<Seccion> secciones;
	@EJB
	private SeccionServiceLocal seccionService;
    
    @PostConstruct
    public void init(){
    	secciones = new ArrayList<Seccion>();
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
    	List<SubMenuDTO> menuGuardado = (esVertical)?menuQuemado():menuQuemadoHorizontal();
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
    	if(subMenuDTO.getItems()!=null) {
    		for (ItemMenuDTO itemMenuDTO : subMenuDTO.getItems()) {
        		submenu.addElement(generarMenuItem(itemMenuDTO, esVertical));
    		}
    		if(esVertical) {
        		submenu.setStyleClass(Constantes.ESTILO_DEFECTO_SUB_MENU_VERTICAL);
        	}
        	menuModel.addElement(submenu);
    	}else {
    		menuModel.addElement(generarMenuItem(subMenuDTO, esVertical));
		}
    	
    	
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
    			((item.getUrl()!=null && !item.getUrl().isEmpty())?item.getUrl():Constantes.PAGINA_DEFECTO_MENU));
    	if(esVertical) {
    		itemMenu.setStyleClass(Constantes.ESTILO_DEFECTO_ITEM_MENU_VERTICAL);
    	}
    	return itemMenu;
    }
    
    public DefaultMenuItem generarMenuItem(SubMenuDTO item, boolean esVertical) {
    	DefaultMenuItem itemMenu = new DefaultMenuItem(((item.getValor()!=null && !item.getValor().isEmpty())?item.getValor():Constantes.NOMBRE_DEFECTO_ITEM_MENU), 
    			((item.getIcono()!=null && !item.getIcono().isEmpty())?item.getIcono():Constantes.ICONO_DEFECTO_MENU), 
    			((item.getUrl()!=null && !item.getUrl().isEmpty())?item.getUrl():Constantes.PAGINA_DEFECTO_MENU));
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
    	ItemMenuDTO item = new ItemMenuDTO("Búsqueda general", "/faces/paginas/buscadorPrincipal.xhtml", "fa fa-search");
    	items.add(item);
    	SubMenuDTO submenu = new SubMenuDTO("Inicio" , items);
    	menu.add(submenu);
    	//tercer menu
    	/*items  = new ArrayList<ItemMenuDTO>();
    	item = new ItemMenuDTO("Carga de indicadores", "/faces/paginas/indicadores.xhtml", "fa fa-upload");
    	items.add(item);
    	submenu = new SubMenuDTO("Administración" , items);
    	menu.add(submenu);*/
    	//segundo menu
    	items = new ArrayList<ItemMenuDTO>();
    	secciones = seccionService.obtenerSeccionesActivas();
    	for(Seccion s : secciones)
    	{
    		try {
    			item = new ItemMenuDTO(StringUtils.capitalize(StringUtils.lowerCase(s.getNombreSec())), "/faces/paginas/tabPrincipal.xhtml?seccionId="+s.getCodigoSec()+"&initialCharge=true&generatedCodeIndi=0", "fa fa-book");
    			items.add(item);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		
    	}
    	submenu = new SubMenuDTO("Normativas" , items);
    	menu.add(submenu);
    	
    	return menu;
    }
    
    public List<SubMenuDTO> menuQuemadoHorizontal() {
    	List<SubMenuDTO> menu = new ArrayList<SubMenuDTO>();
    	//List<ItemMenuDTO> items = new ArrayList<ItemMenuDTO>();
    	//primer menu
    	SubMenuDTO submenu = new SubMenuDTO("Inicio" , "/faces/paginas/buscadorPrincipal.xhtml", "fa fa-home");
    	menu.add(submenu);
    	//tercer menu
    	/*items  = new ArrayList<ItemMenuDTO>();
    	ItemMenuDTO item = new ItemMenuDTO("Carga de indicadores", "/faces/paginas/indicadores.xhtml", "fa fa-upload");
    	items.add(item);
    	submenu = new SubMenuDTO("Administración" , items);
    	menu.add(submenu);*/
    	//segundo menu
    	secciones = seccionService.obtenerSeccionesActivas();
    	for(Seccion s : secciones)
    	{
    		try {
    			submenu = new SubMenuDTO(s.getNombreCorto() , "/faces/paginas/tabPrincipal.xhtml?seccionId="+s.getCodigoSec()+"&initialCharge=true&generatedCodeIndi=0", "fa fa-book");
    			menu.add(submenu);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
    		
    	}
    	return menu;
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


	public List<Seccion> getSecciones() {
		return secciones;
	}


	public void setSecciones(List<Seccion> secciones) {
		this.secciones = secciones;
	}
}
