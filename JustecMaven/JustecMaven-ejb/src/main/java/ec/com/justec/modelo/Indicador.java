/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="INDICADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicador.findAll", query = "SELECT s FROM Indicador s")
    , @NamedQuery(name = "Indicador.findByCodigoIndicador", query = "SELECT s FROM Indicador s WHERE s.codigoIndicador = :codigoIndicador")})
public class Indicador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_IN")
    private Integer codigoIndicador;
    
    @Column(name = "COD_GENERADO_IN")
    private String codigoGeneradoIndicador;
    
    @Column(name = "NOMBRE_IN")
    private String nombreIndicador;
    
    @Column(name = "DEFINICION_IN")
    private String definicionIndicador;
    
    @Column(name = "FUENTE_IN")
    private String fuenteIndicador;
    
    @Column(name = "UNIDAD_MEDIDA_IN")
    private String unidadMedidaIndicador;
 
    @Column(name = "ESTADO_IN")
    private String estado;
    
    @JoinColumn(name = "CODIGO_SEC", referencedColumnName = "CODIGO_SEC")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Seccion codigoSec;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "indicador", fetch = FetchType.LAZY)
    private List<IndicadorValores> indicadorValoresList;

    public Indicador() {
    }


	public Integer getCodigoIndicador() {
		return codigoIndicador;
	}

	public void setCodigoIndicador(Integer codigoIndicador) {
		this.codigoIndicador = codigoIndicador;
	}


	public String getCodigoGeneradoIndicador() {
		return codigoGeneradoIndicador;
	}


	public void setCodigoGeneradoIndicador(String codigoGeneradoIndicador) {
		this.codigoGeneradoIndicador = codigoGeneradoIndicador;
	}


	public String getNombreIndicador() {
		return nombreIndicador;
	}


	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}


	public String getDefinicionIndicador() {
		return definicionIndicador;
	}


	public void setDefinicionIndicador(String definicionIndicador) {
		this.definicionIndicador = definicionIndicador;
	}


	public String getFuenteIndicador() {
		return fuenteIndicador;
	}


	public void setFuenteIndicador(String fuenteIndicador) {
		this.fuenteIndicador = fuenteIndicador;
	}


	public String getUnidadMedidaIndicador() {
		return unidadMedidaIndicador;
	}


	public void setUnidadMedidaIndicador(String unidadMedidaIndicador) {
		this.unidadMedidaIndicador = unidadMedidaIndicador;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Seccion getCodigoSec() {
		return codigoSec;
	}



	public void setCodigoSec(Seccion codigoSec) {
		this.codigoSec = codigoSec;
	}


	@XmlTransient
	public List<IndicadorValores> getIndicadorValoresList() {
		return indicadorValoresList;
	}


	public void setIndicadorValoresList(List<IndicadorValores> indicadorValoresList) {
		this.indicadorValoresList = indicadorValoresList;
	}

	 @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (codigoIndicador != null ? codigoIndicador.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof Indicador)) {
	            return false;
	        }
	        Indicador other = (Indicador) object;
	        if ((this.codigoIndicador == null && other.codigoIndicador != null) || (this.codigoIndicador != null && !this.codigoIndicador.equals(other.codigoIndicador))) {
	            return false;
	        }
	        return true;
	    }
    
}
