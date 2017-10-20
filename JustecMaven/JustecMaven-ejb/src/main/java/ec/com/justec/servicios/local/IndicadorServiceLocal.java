/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.justec.modelo.Indicador;


@Local
public interface IndicadorServiceLocal {

	/**
	 * Método que obtiene un indicador por su código generado
	 * @param codigoGeneradoIndicador
	 * @param estado
	 * @return Objeto de tipo Indicador
	 */
    public Indicador obtenerPorCodGenerado(String codigoGeneradoIndicador, String estado);
    
    /**
     * Método que retorna los indicadores por sección
     * @param codigoSec
     * @param estado
     * @return listado de objetos de tipo Indicador
     */
    public List<Indicador> listarPorCodSeccion(Integer codigoSec, String estado);
    
}
