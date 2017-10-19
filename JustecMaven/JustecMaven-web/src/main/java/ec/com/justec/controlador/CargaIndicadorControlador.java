package ec.com.justec.controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ec.com.justec.dto.EstadoRespuestaMensajeDTO;
import ec.com.justec.enumeradores.EstadoEnum;
import ec.com.justec.modelo.IndicadorValores;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.recursos.Constantes;
import ec.com.justec.servicios.local.IndicadorServiceLocal;
import ec.com.justec.servicios.local.IndicadorValoresServiceLocal;
import ec.com.justec.servicios.local.PaisServiceLocal;
import ec.com.justec.servicios.local.SeccionServiceLocal;

@Named
@ViewScoped
public class CargaIndicadorControlador extends BaseControlador implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1819130226156858163L;
	@EJB
	private SeccionServiceLocal seccionService;
	@EJB
	private IndicadorValoresServiceLocal indicadorValoresServicio;
	@EJB
	private IndicadorServiceLocal indicadorServiceLocal;
	@EJB
	private PaisServiceLocal paisServiceLocal;
	@Inject
	private SesionControlador sesionControlador;
	
	private List<Seccion> normativas;
	private Integer codNormativaSeleccionada;
	private int tamanioMaxArchivo;
	private EstadoRespuestaMensajeDTO respuestaMensaje;
	private EstadoRespuestaMensajeDTO respuestaMensajeSatisfactorio;
	private int cantidadErroneos;
	
	@PostConstruct
	public void init() {
		if (sesionControlador.isLogueoCorrecto()) {
			normativas = seccionService.obtenerSeccionesActivas();
			tamanioMaxArchivo = Constantes.SIZE_MAX_FILE_UPLOAD;
		} else {
			redireccionarPagina("/faces/paginas/principal.xhtml");
		}
	}
	
	public void inicializarElementosCarga() {
		respuestaMensaje = new EstadoRespuestaMensajeDTO();
		respuestaMensajeSatisfactorio = new EstadoRespuestaMensajeDTO();
		cantidadErroneos = 0;
	}

	public void cargarExcel(FileUploadEvent event){
		inicializarElementosCarga();
		if(codNormativaSeleccionada!=null) {
			UploadedFile fileExcel = event.getFile();
			try {
				generarArchivoExcel(fileExcel.getInputstream(), fileExcel.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				respuestaMensaje.setRespuesta(true);
				respuestaMensaje.setMensaje("EL archivo a procesar presenta errores. Por favor verifique.");
			}
		}else {
			respuestaMensaje.setRespuesta(true);
			respuestaMensaje.setMensaje("Debe seleccionar una normativa. Por favor verifique.");
		}
	}
	
	@SuppressWarnings("resource")
	private void generarArchivoExcel(InputStream inputStream, String tipoArchivo){
		try {
			if (tipoArchivo.equals(Constantes.TIPO_ARCHIVO_EXCEL_NUEVO)) {
	            XSSFWorkbook libroTrabajo = new XSSFWorkbook(inputStream);
	            XSSFSheet hojaHssfActual = libroTrabajo.getSheetAt(0);
	           abrirHojaCalculo(hojaHssfActual);

	        } else if (tipoArchivo.equals(Constantes.TIPO_ARCHIVO_EXCEL_ANTIGUO)) {
	            HSSFWorkbook libroTrabajo = new HSSFWorkbook(inputStream);
	            HSSFSheet hojaHssfAntigua = libroTrabajo.getSheetAt(0);
	            abrirHojaCalculo(hojaHssfAntigua);
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respuestaMensaje.setRespuesta(true);
			respuestaMensaje.setMensaje("EL archivo a procesar presenta errores. Por favor verifique.");
		}
	}
	
	private void abrirHojaCalculo(HSSFSheet sheet){
		try {
			Row row;
			cantidadErroneos = 0;
			String celdasErroneas = "";
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				try {
					iterarElementos(row);
				} catch (Exception e) {
					// TODO: handle exception
					cantidadErroneos = cantidadErroneos + 1;
					celdasErroneas = celdasErroneas + " -" + (i+1);
				}
			}
			validarMensaje(celdasErroneas, cantidadErroneos);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respuestaMensaje.setRespuesta(true);
			respuestaMensaje.setMensaje("EL archivo a procesar presenta errores. Por favor verifique.");
		}
	}
	
	private void abrirHojaCalculo(XSSFSheet sheet){
		try {
			Row row;
			String celdasErroneas = "";
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				try {
					iterarElementos(row);
				} catch (Exception e) {
					// TODO: handle exception
					cantidadErroneos = cantidadErroneos + 1;
					celdasErroneas = celdasErroneas + " -" + (i+1);
				}
			}
			validarMensaje(celdasErroneas, cantidadErroneos);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respuestaMensaje.setRespuesta(true);
			respuestaMensaje.setMensaje("EL archivo a procesar presenta errores. Por favor verifique.");
		}
	}
	
	private void iterarElementos(Row row){
		String codigoIndicador = row.getCell(0).getStringCellValue();
		BigDecimal valor = new BigDecimal(row.getCell(1).getNumericCellValue());
		int anio = (int)row.getCell(2).getNumericCellValue();
		String codigoPais = row.getCell(3).getStringCellValue();
		
		
		IndicadorValores valorConsultado = indicadorValoresServicio.obtenerPorCodGeneradoIndicadorAnioCodPais(codigoIndicador, anio, codigoPais, EstadoEnum.ACTIVO.getValor());
		if(valorConsultado != null){
			valorConsultado.setValor(valor);
			indicadorValoresServicio.actualizar(valorConsultado);
		}else if(valorConsultado == null){
			IndicadorValores nuevoValor = new IndicadorValores();
			nuevoValor.setAnio(anio);
			nuevoValor.setIndicador(indicadorServiceLocal.obtenerPorCodGenerado(codigoIndicador, EstadoEnum.ACTIVO.getValor()));
			nuevoValor.setPais(paisServiceLocal.obtenerPorCodigoUnico(codigoPais));
			nuevoValor.setValor(valor);
			nuevoValor.setEstado(EstadoEnum.ACTIVO.getValor());
			indicadorValoresServicio.guardar(nuevoValor);
		}
	}
	
	private void validarMensaje(String celdasErroneas, int cantidadErroneosParametro) {
		String mensaje = "";
		if(cantidadErroneos>0) {
			mensaje = "Indicadores cargados/actualizados con errores.<br/>"
					+ "Estimado usuario verifique las celdas que presentan error y cargue nuevamente el archivo.<br/><br/>"
					+ "<strong>Celdas erróneas:</strong> " + celdasErroneas;
			respuestaMensajeSatisfactorio.setRespuestaAlterna(true);
		}else {
			mensaje = "Indicadores cargados/actualizados correctamente.";
		}
		respuestaMensajeSatisfactorio.setRespuesta(true);
		respuestaMensajeSatisfactorio.setMensaje(mensaje);
	}

	public List<Seccion> getNormativas() {
		return normativas;
	}


	public void setNormativas(List<Seccion> normativas) {
		this.normativas = normativas;
	}


	public Integer getCodNormativaSeleccionada() {
		return codNormativaSeleccionada;
	}


	public void setCodNormativaSeleccionada(Integer codNormativaSeleccionada) {
		this.codNormativaSeleccionada = codNormativaSeleccionada;
	}


	public int getTamanioMaxArchivo() {
		return tamanioMaxArchivo;
	}


	public void setTamanioMaxArchivo(int tamanioMaxArchivo) {
		this.tamanioMaxArchivo = tamanioMaxArchivo;
	}

	public EstadoRespuestaMensajeDTO getRespuestaMensaje() {
		return respuestaMensaje;
	}

	public void setRespuestaMensaje(EstadoRespuestaMensajeDTO respuestaMensaje) {
		this.respuestaMensaje = respuestaMensaje;
	}

	public EstadoRespuestaMensajeDTO getRespuestaMensajeSatisfactorio() {
		return respuestaMensajeSatisfactorio;
	}

	public void setRespuestaMensajeSatisfactorio(EstadoRespuestaMensajeDTO respuestaMensajeSatisfactorio) {
		this.respuestaMensajeSatisfactorio = respuestaMensajeSatisfactorio;
	}

	public int getCantidadErroneos() {
		return cantidadErroneos;
	}

	public void setCantidadErroneos(int cantidadErroneos) {
		this.cantidadErroneos = cantidadErroneos;
	}

}
