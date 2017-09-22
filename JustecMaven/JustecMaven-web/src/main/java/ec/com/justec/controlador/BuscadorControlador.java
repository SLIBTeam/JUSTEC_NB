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

	public BuscadorControlador() {
	}

	@PostConstruct
	private void init() {
		if (sesionControlador.isLogueoCorrecto()) {
			listaSecciones = seccionService.obtenerSeccionesActivas();
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			mostrarPais = Boolean.FALSE;
		} else {
			redireccionarPagina("/faces/paginas/principal.xhtml");
		}
	}

	public void buscar() {
		try {
			mostrarDocumentos = Boolean.FALSE;
			mostrarSecciones = Boolean.FALSE;
			documentosEncontradosTotal = new ArrayList<Documento>();
			seccionesEncontradas = new HashSet<Seccion>();
			List<Documento> documentos = documentoService.obtenerTodoDocumento();
			for (Documento documento : documentos) {
				if (buscarPalabraEnDocumento(documento, palabraBuscada.toUpperCase())) {
					documentosEncontradosTotal.add(documento);
					paisesEncontrados.add(documento.getCodigoPais());
					seccionesEncontradas.add(documento.getCodigoSec());
					resultadoBusquedaService.crear(generarResultadoBusqueda(documento));
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

	public void regresar() {
		if (mostrarDocumentos) {
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

}
