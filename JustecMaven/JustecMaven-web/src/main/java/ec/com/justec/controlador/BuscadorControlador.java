/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.controlador;

import ec.com.justec.facade.local.UsuarioServiceLocal;
import ec.com.justec.modelo.Documento;
import ec.com.justec.modelo.ResultadoBusqueda;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.DocumentoServiceLocal;
import ec.com.justec.servicios.local.ResultadoBusquedaServiceLocal;
import ec.com.justec.servicios.local.SeccionServiceLocal;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author luisp.araujo
 */
@Named(value = "buscadorControlador")
@ViewScoped
public class BuscadorControlador extends BaseControlador implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5135252173022127639L;
	/**
     * Creates a new instance of BuscadorControlador
     */
    private List<Seccion> listaSecciones = new ArrayList<Seccion>();
    private String palabraBuscada;
    private List<Documento> documentosEncontrados = new ArrayList<Documento>();

    @EJB
    private SeccionServiceLocal seccionService;
    @EJB
    private DocumentoServiceLocal documentoService;
    @EJB
    private ResultadoBusquedaServiceLocal resultadoBusquedaService;
    @EJB
    private UsuarioServiceLocal usuarioService;
    
    @Inject
    private SesionControlador sesionControlador;
    


    @PostConstruct
    private void init() {
    	if(sesionControlador.isLogueoCorrecto()) {
    		listaSecciones = seccionService.obtenerSeccionesActivas();
    	}else {
    		redireccionarPagina("/faces/paginas/principal.xhtml");
		}
    }

    public void buscar() {
        try {
            List<Documento> documentos = documentoService.obtenerTodoDocumento();

            System.out.println("Documentos: "+documentos.size());
            for (Documento documento : documentos) {
                if (buscarPalabraEnDocumento(documento, palabraBuscada)) {
                    documentosEncontrados.add(documento);
                    resultadoBusquedaService.crear(generarResultadoBusqueda(documento));
                }
            }
            System.out.println("Documentos encontrados: "+documentosEncontrados.size());
        } catch (Exception e) {
            agregarMensajeError(e);
            e.printStackTrace();
        }

    }

    private Boolean buscarPalabraEnDocumento(Documento documento, String palabra) throws IOException {
        Boolean existePalabra = Boolean.FALSE;
        if (documento.getRutaDoc().toLowerCase().endsWith(".pdf")) {
            PDDocument pdf = PDDocument.load(new File(documento.getRutaDoc()));
            String content = new PDFTextStripper()
                    .getText(pdf);
            pdf.close();
            if (content.contains(palabra)) {
                existePalabra = Boolean.TRUE;
                System.out.println("existe en: " + documento.getTituloDoc());
            }
        }

        return existePalabra;
    }
    
    private ResultadoBusqueda generarResultadoBusqueda(Documento documento)
    {
        ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
        resultadoBusqueda.setCodigoDoc(documento);
        resultadoBusqueda.setFechaRb(new Date());
        resultadoBusqueda.setTextoRb(palabraBuscada);
        resultadoBusqueda.setUsuarioRb(usuarioService.obtenerPorNombre("1723723092"));
        return resultadoBusqueda;
    }

    public List<Seccion> getListaSecciones() {
        return listaSecciones;
    }

    public void setListaSecciones(List<Seccion> listaSecciones) {
        this.listaSecciones = listaSecciones;
    }

    public String getPalabraBuscada() {
        return palabraBuscada;
    }

    public void setPalabraBuscada(String palabraBuscada) {
        this.palabraBuscada = palabraBuscada;
    }

    public List<Documento> getDocumentosEncontrados() {
        return documentosEncontrados;
    }

    public void setDocumentosEncontrados(List<Documento> documentosEncontrados) {
        this.documentosEncontrados = documentosEncontrados;
    }
    
    
}
