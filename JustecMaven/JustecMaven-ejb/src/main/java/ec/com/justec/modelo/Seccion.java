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
@Table(name="SECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s")
    , @NamedQuery(name = "Seccion.findByCodigoSec", query = "SELECT s FROM Seccion s WHERE s.codigoSec = :codigoSec")
    , @NamedQuery(name = "Seccion.findByNombreSec", query = "SELECT s FROM Seccion s WHERE s.nombreSec = :nombreSec")
    , @NamedQuery(name = "Seccion.findByEstadoSec", query = "SELECT s FROM Seccion s WHERE s.estadoSec = :estadoSec")
    , @NamedQuery(name = "Seccion.findByFechaActSec", query = "SELECT s FROM Seccion s WHERE s.fechaActSec = :fechaActSec")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_SEC")
    private Integer codigoSec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_SEC")
    private String nombreSec;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_SEC")
    private String estadoSec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_SEC")
    @Temporal(TemporalType.DATE)
    private Date fechaActSec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSec", fetch = FetchType.LAZY)
    private List<Documento> documentoList;

    public Seccion() {
    }

    public Seccion(Integer codigoSec) {
        this.codigoSec = codigoSec;
    }

    public Seccion(Integer codigoSec, String nombreSec, String estadoSec, Date fechaActSec) {
        this.codigoSec = codigoSec;
        this.nombreSec = nombreSec;
        this.estadoSec = estadoSec;
        this.fechaActSec = fechaActSec;
    }

    public Integer getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(Integer codigoSec) {
        this.codigoSec = codigoSec;
    }

    public String getNombreSec() {
        return nombreSec;
    }

    public void setNombreSec(String nombreSec) {
        this.nombreSec = nombreSec;
    }

    public String getEstadoSec() {
        return estadoSec;
    }

    public void setEstadoSec(String estadoSec) {
        this.estadoSec = estadoSec;
    }

    public Date getFechaActSec() {
        return fechaActSec;
    }

    public void setFechaActSec(Date fechaActSec) {
        this.fechaActSec = fechaActSec;
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
        hash += (codigoSec != null ? codigoSec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.codigoSec == null && other.codigoSec != null) || (this.codigoSec != null && !this.codigoSec.equals(other.codigoSec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.Seccion[ codigoSec=" + codigoSec + " ]";
    }
    
}
