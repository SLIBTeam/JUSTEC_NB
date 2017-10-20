/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.justec.modelo.IndicadorValores;


@Local
public interface IndicadorValoresServiceLocal {

	/**
	 * Método que busca el valor de un indicador
	 * @param codigoGeneradoIndicador
	 * @param anio
	 * @param codPais
	 * @param estado
	 * @return objeto de tipo IndicadorValores
	 */
    public IndicadorValores obtenerPorCodGeneradoIndicadorAnioCodPais(String codigoGeneradoIndicador, int anio, String codPais, String estado);
    
    /**
     * Método que guarda el valor de un indicador
     * @param valor
     */
    public void guardar(IndicadorValores valor);
    
    /**
     * Método que actualiza el valor de un indicador
     * @param valor
     */
    public void actualizar(IndicadorValores valor);
    
    public List<Integer> listarAniosPorCodigoIndicador(Integer codigoIndicador, String estado);
    
    public List<IndicadorValores> listarvaloresPorCodigoIndicador(Integer codigoIndicador, String estado);
    
}
