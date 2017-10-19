/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.justec.modelo.Pais;


@Local
public interface PaisServiceLocal {

	public Pais obtenerPorCodigoUnico(String codigoUnicoPais);
	
	public List<Pais> listarPaisesPorCodigoIndicador(Integer codigoIndicador, String estado);
    
}
