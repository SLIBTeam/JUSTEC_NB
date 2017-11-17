/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.controlador;

import ec.com.justec.enumeradores.EstadoEnum;
import ec.com.justec.facade.local.UsuarioServiceLocal;
import ec.com.justec.modelo.Documento;
import ec.com.justec.modelo.Pais;
import ec.com.justec.modelo.ResultadoBusqueda;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.recursos.Constantes;
import ec.com.justec.servicios.local.DocumentoServiceLocal;
import ec.com.justec.servicios.local.ResultadoBusquedaServiceLocal;
import ec.com.justec.servicios.local.SeccionServiceLocal;
import ec.com.justec.util.Util;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.Splitter;
import org.pdfclown.documents.Page;
import org.pdfclown.documents.contents.ITextString;
import org.pdfclown.documents.contents.TextChar;
import org.pdfclown.documents.interaction.annotations.TextMarkup;
import org.pdfclown.documents.interaction.annotations.TextMarkup.MarkupTypeEnum;
import org.pdfclown.files.SerializationModeEnum;
import org.pdfclown.tools.TextExtractor;
import org.pdfclown.util.math.Interval;
import org.pdfclown.util.math.geom.Quad;

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
	private static final long serialVersionUID = 2416482371077787199L;
	/**
	 * Creates a new instance of BuscadorControlador
	 */
	private List<Seccion> listaSecciones = new ArrayList<Seccion>();
	private String palabraBuscada;
	private List<Documento> documentosEncontrados = new ArrayList<Documento>();
	private List<Documento> documentosEncontradosTotal = new ArrayList<Documento>();
	private List<Documento> documentosEncontradosPais = new ArrayList<Documento>();
	private Set<Seccion> seccionesEncontradas = new HashSet<Seccion>();
	private Map<Seccion, Integer> resultadoSeccionesMap = new HashMap<Seccion, Integer>();
	private Set<Pais> paisesEncontrados = new HashSet<Pais>();
	private Map<Pais, Integer> resultadosPaisMap = new HashMap<Pais, Integer>();
	private Boolean mostrarSecciones = Boolean.FALSE;
	private Boolean mostrarDocumentos = Boolean.FALSE;
	private Boolean mostrarPais = Boolean.FALSE;
	private String urlPdf;
	private Integer seccionId;
	private boolean iniciadaBusqueda;
	private boolean documentoPdf = Boolean.FALSE;
	private Documento documentoTemp;

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

	public Integer getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(Integer seccionId) {
		this.seccionId = seccionId;
	}

	public BuscadorControlador() {
	}

	@PostConstruct
	private void init() {
		if (sesionControlador.isLogueoCorrecto()) {
			listaSecciones = seccionService.obtenerSeccionesActivas();
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			mostrarPais = Boolean.FALSE;
			seccionId = getHttpRequest().getParameter("seccionId") != null ? Integer.parseInt(getHttpRequest().getParameter("seccionId")) : null; 
		} else {
			redireccionarPagina("/faces/paginas/principal.xhtml");
		}
	}

	public void buscar() {
		try {
			iniciadaBusqueda = true;
			System.out.println("seccionId: "+seccionId);
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			documentoPdf = Boolean.FALSE;
			documentosEncontradosTotal = new ArrayList<Documento>();
			seccionesEncontradas = new HashSet<Seccion>();
			List<Documento> documentos = new ArrayList<>();
			if(seccionId != null)
			{
				documentos = documentoService.obtenerTodoDocumentoXSeccion(seccionId);
			}
			else
			{
				documentos = documentoService.obtenerTodoDocumento();
			}
			for (Documento documento : documentos) {
				try {
					buscarCoincidenciaDocumento(documento, palabraBuscada);
					if (documento.getExistePalabra()) {
//						documento.setPagina(obtenerPaginaPDF(documento, palabraBuscada.toUpperCase()));
						documentosEncontradosTotal.add(documento);
						paisesEncontrados.add(documento.getCodigoPais());
						seccionesEncontradas.add(documento.getCodigoSec());
						resultadoBusquedaService.crear(generarResultadoBusqueda(documento));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			resultadosPaisMap = generarResultadoXPais(paisesEncontrados, documentosEncontradosTotal);
			mostrarPais = Boolean.TRUE;
		} catch (Exception e) {
			agregarMensajeError(e);
			e.printStackTrace();
		}

	}

	private Boolean buscarPalabraEnDocumento(Documento documento, String palabra) throws IOException {
		Boolean existePalabra = Boolean.FALSE;
		try {
			PDDocument pdf = PDDocument.load(new File(Util.obtenerRutaDocumentos() + documento.getRutaDoc()));
			String content = new PDFTextStripper().getText(pdf).toUpperCase();
			pdf.close();
			if (content.contains(palabra)) {
				existePalabra = Boolean.TRUE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return existePalabra;
	}
	
	private void buscarCoincidenciaDocumento(final Documento documento, String palabra) throws IOException{
//		Boolean existePalabra = Boolean.FALSE;
		int pagina = 0;
		
		org.pdfclown.files.File file = new org.pdfclown.files.File(Util.obtenerRutaDocumentos() + documento.getRutaDoc());
        Pattern pattern = Pattern.compile(palabra, Pattern.CASE_INSENSITIVE);
                
        TextExtractor textExtractor = new TextExtractor(true, true);

        for (final Page page : file.getDocument().getPages()) {
        	final int pag = pagina + 1;
        	Map<Rectangle2D, List<ITextString>> textStrings = textExtractor.extract(page);

            final Matcher matcher = pattern.matcher(TextExtractor.toString(textStrings));
            textExtractor.filter(
                    textStrings,
                    new TextExtractor.IIntervalFilter() {
                @Override
                public boolean hasNext() {
                    return matcher.find();
                }

                @Override
                public Interval next() {
                    return new Interval(matcher.start(), matcher.end());
                }

                @Override
                public void process(
                        Interval interval,
                        ITextString match
                ) {
                    List highlightQuads = new ArrayList();
                    {
                        Rectangle2D textBox = null;
                        for (TextChar textChar : match.getTextChars()) {
                            Rectangle2D textCharBox = textChar.getBox();
                            if (textBox == null) {
                                textBox = (Rectangle2D) textCharBox.clone();
                            } else {
                                if (textCharBox.getY() > textBox.getMaxY()) {
                                    highlightQuads.add(Quad.get(textBox));
                                    textBox = (Rectangle2D) textCharBox.clone();
                                } else {
                                    textBox.add(textCharBox);
                                }
                            }
                        }
                        highlightQuads.add(Quad.get(textBox));
                    }
                    new TextMarkup(page, null, MarkupTypeEnum.Highlight, highlightQuads);
                    generarRutaAlternaYPaginaDocumento(documento, pag);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            }
            );
            pagina++;
        }

        if(documento.getExistePalabra())
        	file.save(Util.obtenerRutaDocumentos() +"temp_"+ documento.getRutaDoc(),SerializationModeEnum.Incremental);
        System.err.println("fin");
		
//		return existePalabra;
	}
	
	private void generarRutaAlternaYPaginaDocumento(Documento documento, Integer pagina)
	{
		if(!documento.getExistePalabra())
		{
			documento.setExistePalabra(Boolean.TRUE);
			documento.setPagina(pagina);
			documento.setRutaTemp("temp_"+documento.getRutaDoc());
		}
	}
	
	private Integer obtenerPaginaPDF(Documento documento, String palabra)throws IOException {
		Integer pagina = 0;
		try {
			PDDocument pdf = PDDocument.load(new File(Util.obtenerRutaDocumentos() + documento.getRutaDoc()));
			
			Splitter splitter = new Splitter();
			splitter.setStartPage(1);
			
			List<PDDocument> paginas = splitter.split(pdf);
			for(int i= 0; i < paginas.size(); i++)
			{
				PDDocument doc = (PDDocument) paginas.get(i);
				String content =  new PDFTextStripper().getText(doc).toUpperCase();
				doc.close();
				if (content.contains(palabra)) {
					pagina = i+1;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;
	}

	private ResultadoBusqueda generarResultadoBusqueda(Documento documento) {
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
		resultadoBusqueda.setCodigoDoc(documento);
		resultadoBusqueda.setFechaRb(new Date());
		resultadoBusqueda.setTextoRb(palabraBuscada.toUpperCase());
		resultadoBusqueda.setUsuarioRb(usuarioService.obtenerPorIdentificacion(sesionControlador.getIdentificacionUsuario(), EstadoEnum.ACTIVO.getValor()));
		return resultadoBusqueda;
	}

	private Map<Seccion, Integer> generarResultadoXSeccion(Set<Seccion> secciones, List<Documento> documentos) {
		Map<Seccion, Integer> resultado = new HashMap<>();

		for (Seccion s : secciones) {
			Integer contador = 0;
			for (Documento d : documentos) {
				if (d.getCodigoSec().getCodigoSec().equals(s.getCodigoSec())) {
					contador += 1;
				}
				resultado.put(s, contador);
			}
		}

		return resultado;
	}

	private Map<Pais, Integer> generarResultadoXPais(Set<Pais> paises, List<Documento> documentos) {
		Map<Pais, Integer> resultado = new HashMap<>();

		for (Pais p : paises) {
			Integer contador = 0;
			for (Documento d : documentos) {
				if (d.getCodigoPais().getCodigoPais().equals(p.getCodigoPais())) {
					contador += 1;
				}
				resultado.put(p, contador);
			}
		}

		return resultado;
	}

	private List<Documento> obtenerDocumentosXSeccion(Seccion seccion) {
		List<Documento> resultado = new ArrayList<>();
		for (Documento d : documentosEncontradosPais) {
			if (d.getCodigoSec().equals(seccion)) {
				resultado.add(d);
			}
		}
		return resultado;
	}

	private List<Documento> obtenerDocumentosXPais(Pais pais) {
		List<Documento> resultado = new ArrayList<>();
		for (Documento d : documentosEncontradosTotal) {
			if (d.getCodigoPais().equals(pais)) {
				resultado.add(d);
			}
		}
		return resultado;
	}

	private Set<Seccion> obtenerSeccionesXPais(Pais pais) {
		Set<Seccion> resultado = new HashSet<>();
		for (Documento d : documentosEncontradosPais) {
			resultado.add(d.getCodigoSec());
		}
		return resultado;
	}

	public void obtenerDocumentos(Seccion seccion) {
		documentosEncontrados = obtenerDocumentosXSeccion(seccion);
		mostrarDocumentos = Boolean.TRUE;
		mostrarSecciones = Boolean.FALSE;
	}

	public void obtenerSecciones(Pais pais) {
		documentosEncontradosPais = obtenerDocumentosXPais(pais);
		seccionesEncontradas = obtenerSeccionesXPais(pais);
		resultadoSeccionesMap = generarResultadoXSeccion(seccionesEncontradas, documentosEncontradosPais);
		mostrarSecciones = Boolean.TRUE;
		mostrarDocumentos = Boolean.FALSE;
		mostrarPais = Boolean.FALSE;
	}

	public void descargarArchivo(Documento documento) {

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + documento.getTituloDoc() + ".pdf" + "\"");
		try {
			OutputStream os = null;
			os = response.getOutputStream();
			os.write(Files.readAllBytes(Paths.get(documento.getRutaDoc())));
			os.flush();
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void mostrarDocumento(Documento documento)
	{
		documentoTemp = documento;
		documentoPdf = Boolean.TRUE;
	}
	
	public void regresar() {
		if(documentoPdf) {
			mostrarDocumentos = Boolean.TRUE;
			documentoPdf = Boolean.FALSE;
		}
		else if (mostrarDocumentos) {
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.TRUE;
			mostrarPais = Boolean.FALSE;
		} else if (mostrarSecciones) {
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			mostrarPais = Boolean.TRUE;
		} else {
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			mostrarPais = Boolean.FALSE;
		}
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

	public Set<Seccion> getSeccionesEncontradas() {
		return seccionesEncontradas;
	}

	public void setSeccionesEncontradas(Set<Seccion> seccionesEncontradas) {
		this.seccionesEncontradas = seccionesEncontradas;
	}

	public Map<Seccion, Integer> getResultadoSeccionesMap() {
		return resultadoSeccionesMap;
	}

	public void setResultadoSeccionesMap(Map<Seccion, Integer> resultadoSeccionesMap) {
		this.resultadoSeccionesMap = resultadoSeccionesMap;
	}

	public Boolean getMostrarSecciones() {
		return mostrarSecciones;
	}

	public void setMostrarSecciones(Boolean mostrarSecciones) {
		this.mostrarSecciones = mostrarSecciones;
	}

	public Boolean getMostrarDocumentos() {
		return mostrarDocumentos;
	}

	public void setMostrarDocumentos(Boolean mostrarDocumentos) {
		this.mostrarDocumentos = mostrarDocumentos;
	}

	public List<Documento> getDocumentosEncontradosTotal() {
		return documentosEncontradosTotal;
	}

	public void setDocumentosEncontradosTotal(List<Documento> documentosEncontradosTotal) {
		this.documentosEncontradosTotal = documentosEncontradosTotal;
	}

	public Set<Pais> getPaisesEncontrados() {
		return paisesEncontrados;
	}

	public void setPaisesEncontrados(Set<Pais> paisesEncontrados) {
		this.paisesEncontrados = paisesEncontrados;
	}

	public Map<Pais, Integer> getResultadosPaisMap() {
		return resultadosPaisMap;
	}

	public void setResultadosPaisMap(Map<Pais, Integer> resultadosPaisMap) {
		this.resultadosPaisMap = resultadosPaisMap;
	}

	public Boolean getMostrarPais() {
		return mostrarPais;
	}

	public void setMostrarPais(Boolean mostrarPais) {
		this.mostrarPais = mostrarPais;
	}

	public String getUrlPdf() {
		return urlPdf;
	}

	public void setUrlPdf(String urlPdf) {
		this.urlPdf = urlPdf;
	}

	public boolean isIniciadaBusqueda() {
		return iniciadaBusqueda;
	}

	public void setIniciadaBusqueda(boolean iniciadaBusqueda) {
		this.iniciadaBusqueda = iniciadaBusqueda;
	}

	public boolean isDocumentoPdf() {
		return documentoPdf;
	}

	public void setDocumentoPdf(boolean documentoPdf) {
		this.documentoPdf = documentoPdf;
	}

	public Documento getDocumentoTemp() {
		return documentoTemp;
	}

	public void setDocumentoTemp(Documento documentoTemp) {
		this.documentoTemp = documentoTemp;
	}

}
