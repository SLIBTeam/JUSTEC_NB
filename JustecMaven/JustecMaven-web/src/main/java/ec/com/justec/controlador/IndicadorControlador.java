package ec.com.justec.controlador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;

import ec.com.justec.enumeradores.EstadoEnum;
import ec.com.justec.enumeradores.TipoGraficoEnum;
import ec.com.justec.modelo.Indicador;
import ec.com.justec.modelo.IndicadorValores;
import ec.com.justec.modelo.Pais;
import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.IndicadorServiceLocal;
import ec.com.justec.servicios.local.IndicadorValoresServiceLocal;
import ec.com.justec.servicios.local.PaisServiceLocal;
import ec.com.justec.servicios.local.SeccionServiceLocal;

@Named
@ViewScoped
public class IndicadorControlador extends BaseControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5389251918677216121L;
	
	@EJB
	private SeccionServiceLocal seccionService;
	@EJB
	private IndicadorServiceLocal indicadorServiceLocal;
	@EJB
	private IndicadorValoresServiceLocal indicadorValoresServiceLocal;
	@EJB
	private PaisServiceLocal paisServiceLocal;
	@Inject
	private SesionControlador sesionControlador;
	
	private Seccion seccion;
	private List<Indicador> indicadores;
	private List<Integer> aniosIndicador;
	private List<IndicadorValores> valoresIndicador;
	private List<Pais> paisesIndicador;
	private LineChartModel lineModel;
	private BarChartModel barModel;
	private Indicador indicador;
	private boolean activarGrafLineal;
	private boolean activarGrafBarras;
	private int opcionGrafico;
	private TipoGraficoEnum[] tiposGrafico;
	
	
	@PostConstruct
	public void init() {		
		if (sesionControlador.isLogueoCorrecto()) {
			Integer codigoSeccion = getHttpRequest().getParameter("seccionId") != null ? Integer.parseInt(getHttpRequest().getParameter("seccionId")) : null;
			Boolean esCargaInicial = getHttpRequest().getParameter("initialCharge") != null ? Boolean.parseBoolean(getHttpRequest().getParameter("initialCharge")) : true; 
			String codigoGeneradoIndicador = getHttpRequest().getParameter("generatedCodeIndi");
			valoresIndicador = new ArrayList<IndicadorValores>();
			aniosIndicador = new ArrayList<Integer>();
			paisesIndicador = new ArrayList<Pais>();
			if(codigoSeccion != null)
			{
				seccion = seccionService.obtenerXId(codigoSeccion);
				indicadores = indicadorServiceLocal.listarPorCodSeccion(codigoSeccion, EstadoEnum.ACTIVO.getValor());
				tiposGrafico = TipoGraficoEnum.values();
				opcionGrafico = TipoGraficoEnum.LINEAL.getOpcion();
				if(!indicadores.isEmpty() && esCargaInicial) {
					cargarValoresIndicador(indicadores.get(0));
				}else if (!indicadores.isEmpty() && !esCargaInicial && codigoGeneradoIndicador != null) {
					cargarValoresIndicador(indicadorServiceLocal.obtenerPorCodGenerado(codigoGeneradoIndicador, EstadoEnum.ACTIVO.getValor()));
				}
				
			}
		} else {
			redireccionarPagina("/faces/paginas/principal.xhtml");
		}
		
	}
	
	public void cargarValoresIndicador(Indicador indicadorParametro) {
		try {
			lineModel = null;
			barModel = null;
			indicador = indicadorParametro;
			valoresIndicador = indicadorValoresServiceLocal.listarvaloresPorCodigoIndicador(indicador.getCodigoIndicador(), EstadoEnum.ACTIVO.getValor());
			paisesIndicador = paisServiceLocal.listarPaisesPorCodigoIndicador(indicador.getCodigoIndicador(), EstadoEnum.ACTIVO.getValor());
			aniosIndicador = indicadorValoresServiceLocal.listarAniosPorCodigoIndicador(indicador.getCodigoIndicador(), EstadoEnum.ACTIVO.getValor());
			if(!valoresIndicador.isEmpty()) {
				crearModeloGrafico();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public BigDecimal valorIndicadorPorPaisAnio(Indicador indicador, Pais pais, Integer anio) {
		BigDecimal valor = null;
		for (IndicadorValores valorIndicador : valoresIndicador) {
			try {
				if(valorIndicador.getAnio().equals(anio) && valorIndicador.getPais().equals(pais)) {
					valor = valorIndicador.getValor();
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				valor = null;
			}
		}
		return valor;
	}
	
	public void crearModeloGrafico() {
		activarGrafLineal= false;
		activarGrafBarras = false;
		switch (opcionGrafico) {
		case 1:
			crearModeloLineal(indicador);
			activarGrafLineal= true;
			activarGrafBarras = false;
			break;
		case 2:
			createModeloBarras(indicador);
			activarGrafLineal= false;
			activarGrafBarras = true;
			break;

		}
		
	}

	private void crearModeloLineal(Indicador indicador) {
        lineModel = generarGraficoLineal(indicador);
        lineModel.setTitle("Gráfico lineal del indicador");
        lineModel.setLegendPosition("s");
        lineModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        lineModel.setAnimate(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Años"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(getMaximoMinimoValordeIndicador(valoresIndicador,false));
        yAxis.setMax(getMaximoMinimoValordeIndicador(valoresIndicador,true));
    }
	
	private void createModeloBarras(Indicador indicador) {
        barModel = generarGraficoBarras(indicador);
        barModel.setTitle("Gráfico de barras del indicador");
        barModel.setLegendPosition("s");
        barModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        barModel.setAnimate(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Años");
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setMin(getMaximoMinimoValordeIndicador(valoresIndicador,false));
        yAxis.setMax(getMaximoMinimoValordeIndicador(valoresIndicador,true));
    }
	

	private LineChartModel generarGraficoLineal(Indicador indicador) {
		LineChartModel model = new LineChartModel();
		for (Pais pais : paisesIndicador) {
			ChartSeries paisGraficoLinea = new ChartSeries();
			for (Integer anio : aniosIndicador) {
				paisGraficoLinea.setLabel(pais.getNombrePais());
				paisGraficoLinea.set(anio, valorIndicadorPorPaisAnio(indicador, pais, anio));
			}
			model.addSeries(paisGraficoLinea);
		}
		return model;
	}
	
	private BarChartModel generarGraficoBarras(Indicador indicador) {
		BarChartModel model = new BarChartModel();
		for (Pais pais : paisesIndicador) {
			ChartSeries paisGraficoLinea = new ChartSeries();
			for (Integer anio : aniosIndicador) {
				paisGraficoLinea.setLabel(pais.getNombrePais());
				paisGraficoLinea.set(anio, valorIndicadorPorPaisAnio(indicador, pais, anio));
			}
			model.addSeries(paisGraficoLinea);
		}
		return model;
	}
	
	private BigDecimal getMaximoMinimoValordeIndicador(List<IndicadorValores> valores, boolean maximo) {
		BigDecimal resultado = new BigDecimal(0);
		int tamanioLista = valores.size();
		try {
			if(maximo) {
				resultado = (valores!=null && tamanioLista>0)?valores.get(0).getValor().add(new BigDecimal(5)):new BigDecimal(0);
			}else {
				resultado = (valores!=null && tamanioLista>0)?valores.get(tamanioLista-1).getValor().subtract(new BigDecimal(5)):new BigDecimal(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			resultado = new BigDecimal(0);
		}
		return resultado;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public List<Integer> getAniosIndicador() {
		return aniosIndicador;
	}

	public void setAniosIndicador(List<Integer> aniosIndicador) {
		this.aniosIndicador = aniosIndicador;
	}

	public List<IndicadorValores> getValoresIndicador() {
		return valoresIndicador;
	}

	public void setValoresIndicador(List<IndicadorValores> valoresIndicador) {
		this.valoresIndicador = valoresIndicador;
	}

	public List<Pais> getPaisesIndicador() {
		return paisesIndicador;
	}

	public void setPaisesIndicador(List<Pais> paisesIndicador) {
		this.paisesIndicador = paisesIndicador;
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public boolean isActivarGrafLineal() {
		return activarGrafLineal;
	}

	public void setActivarGrafLineal(boolean activarGrafLineal) {
		this.activarGrafLineal = activarGrafLineal;
	}

	public boolean isActivarGrafBarras() {
		return activarGrafBarras;
	}

	public void setActivarGrafBarras(boolean activarGrafBarras) {
		this.activarGrafBarras = activarGrafBarras;
	}

	public int getOpcionGrafico() {
		return opcionGrafico;
	}

	public void setOpcionGrafico(int opcionGrafico) {
		this.opcionGrafico = opcionGrafico;
	}

	public TipoGraficoEnum[] getTiposGrafico() {
		return tiposGrafico;
	}

	public void setTiposGrafico(TipoGraficoEnum[] tiposGrafico) {
		this.tiposGrafico = tiposGrafico;
	}

}
