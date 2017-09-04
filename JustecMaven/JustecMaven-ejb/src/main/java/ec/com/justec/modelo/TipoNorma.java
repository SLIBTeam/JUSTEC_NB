/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "TIPO_NORMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNorma.findAll", query = "SELECT t FROM TipoNorma t")
    , @NamedQuery(name = "TipoNorma.findByCodigoTn", query = "SELECT t FROM TipoNorma t WHERE t.codigoTn = :codigoTn")
    , @NamedQuery(name = "TipoNorma.findByNombreTn", query = "SELECT t FROM TipoNorma t WHERE t.nombreTn = :nombreTn")
    , @NamedQuery(name = "TipoNorma.findByEstadoTn", query = "SELECT t FROM TipoNorma t WHERE t.estadoTn = :estadoTn")
    , @NamedQuery(name = "TipoNorma.findByFechaActTn", query = "SELECT t FROM TipoNorma t WHERE t.fechaActTn = :fechaActTn")})
public class TipoNorma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_TN")
    private Integer codigoTn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_TN")
    private String nombreTn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_TN")
    private String estadoTn;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_TN")
    @Temporal(TemporalType.DATE)
    private Date fechaActTn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTn", fetch = FetchType.LAZY)
    private List<Documento> documentoList;

    public TipoNorma() {
    }

    public TipoNorma(Integer codigoTn) {
        this.codigoTn = codigoTn;
    }

    public TipoNorma(Integer codigoTn, String nombreTn, String estadoTn, Date fechaActTn) {
        this.codigoTn = codigoTn;
        this.nombreTn = nombreTn;
        this.estadoTn = estadoTn;
        this.fechaActTn = fechaActTn;
    }

    public Integer getCodigoTn() {
        return codigoTn;
    }

    public void setCodigoTn(Integer codigoTn) {
        this.codigoTn = codigoTn;
    }

    public String getNombreTn() {
        return nombreTn;
    }

    public void setNombreTn(String nombreTn) {
        this.nombreTn = nombreTn;
    }

    public String getEstadoTn() {
        return estadoTn;
    }

    public void setEstadoTn(String estadoTn) {
        this.estadoTn = estadoTn;
    }

    public Date getFechaActTn() {
        return fechaActTn;
    }

    public void setFechaActTn(Date fechaActTn) {
        this.fechaActTn = fechaActTn;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTn != null ? codigoTn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoNorma)) {
            return false;
        }
        TipoNorma other = (TipoNorma) object;
        if ((this.codigoTn == null && other.codigoTn != null) || (this.codigoTn != null && !this.codigoTn.equals(other.codigoTn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.TipoNorma[ codigoTn=" + codigoTn + " ]";
    }
    
}
