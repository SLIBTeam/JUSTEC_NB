package ec.com.justec.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import ec.com.justec.modelo.Seccion;
import ec.com.justec.servicios.local.SeccionServiceLocal;

@Named(value = "indicadorControlador")
@ViewScoped
public class IndicadorControlador extends BaseControlador implements Serializable {

	private static final long serialVersionUID = 1L;

	private BarChartModel barModel;
	private Seccion seccion;

	@EJB
	private SeccionServiceLocal seccionService;
	
	@PostConstruct
	public void init() {
		createBarModels();
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 135);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	private void createBarModels() {
		createBarModel();
	}

	private void createBarModel() {
		
		Integer seccionId = getHttpRequest().getParameter("seccionId") != null ? Integer.parseInt(getHttpRequest().getParameter("seccionId")) : null; 
		if(seccionId != null)
			seccion = seccionService.obtenerXId(seccionId);
		
		barModel = initBarModel();

		barModel.setTitle(seccion != null ? seccion.getNombreSec() : "Prueba");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Tiempo");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Indicador");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

}
