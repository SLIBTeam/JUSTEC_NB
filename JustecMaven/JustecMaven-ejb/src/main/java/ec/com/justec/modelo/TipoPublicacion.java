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
@Table(name = "TIPO_PUBLICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPublicacion.findAll", query = "SELECT t FROM TipoPublicacion t")
    , @NamedQuery(name = "TipoPublicacion.findByCodigoTp", query = "SELECT t FROM TipoPublicacion t WHERE t.codigoTp = :codigoTp")
    , @NamedQuery(name = "TipoPublicacion.findByNombreTp", query = "SELECT t FROM TipoPublicacion t WHERE t.nombreTp = :nombreTp")
    , @NamedQuery(name = "TipoPublicacion.findByEstadoTp", query = "SELECT t FROM TipoPublicacion t WHERE t.estadoTp = :estadoTp")
    , @NamedQuery(name = "TipoPublicacion.findByFechaActTp", query = "SELECT t FROM TipoPublicacion t WHERE t.fechaActTp = :fechaActTp")})
public class TipoPublicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_TP")
    private Integer codigoTp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_TP")
    private String nombreTp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_TP")
    private String estadoTp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_TP")
    @Temporal(TemporalType.DATE)
    private Date fechaActTp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTp", fetch = FetchType.LAZY)
    private List<Documento> documentoList;

    public TipoPublicacion() {
    }

    public TipoPublicacion(Integer codigoTp) {
        this.codigoTp = codigoTp;
    }

    public TipoPublicacion(Integer codigoTp, String nombreTp, String estadoTp, Date fechaActTp) {
        this.codigoTp = codigoTp;
        this.nombreTp = nombreTp;
        this.estadoTp = estadoTp;
        this.fechaActTp = fechaActTp;
    }

    public Integer getCodigoTp() {
        return codigoTp;
    }

    public void setCodigoTp(Integer codigoTp) {
        this.codigoTp = codigoTp;
    }

    public String getNombreTp() {
        return nombreTp;
    }

    public void setNombreTp(String nombreTp) {
        this.nombreTp = nombreTp;
    }

    public String getEstadoTp() {
        return estadoTp;
    }

    public void setEstadoTp(String estadoTp) {
        this.estadoTp = estadoTp;
    }

    public Date getFechaActTp() {
        return fechaActTp;
    }

    public void setFechaActTp(Date fechaActTp) {
        this.fechaActTp = fechaActTp;
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
        hash += (codigoTp != null ? codigoTp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPublicacion)) {
            return false;
        }
        TipoPublicacion other = (TipoPublicacion) object;
        if ((this.codigoTp == null && other.codigoTp != null) || (this.codigoTp != null && !this.codigoTp.equals(other.codigoTp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.TipoPublicacion[ codigoTp=" + codigoTp + " ]";
    }
    
}
