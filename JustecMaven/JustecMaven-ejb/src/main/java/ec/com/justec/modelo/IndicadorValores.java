/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="INDICADOR_VALORES")
public class IndicadorValores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_IV")
    private Integer codigoIndicadorValor;
    
    @Column(name = "VALOR_IV")
    private BigDecimal valor;
    
    @Column(name = "ANIO_IV")
    private Integer anio;
 
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "CODIGO_PAIS", referencedColumnName = "CODIGO_PAIS")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pais pais;
    
    @JoinColumn(name = "CODIGO_IN", referencedColumnName = "CODIGO_IN")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Indicador indicador;
    

    public IndicadorValores() {
    }
    

	public Integer getCodigoIndicadorValor() {
		return codigoIndicadorValor;
	}

	public void setCodigoIndicadorValor(Integer codigoIndicadorValor) {
		this.codigoIndicadorValor = codigoIndicadorValor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}
    
}
