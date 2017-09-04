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
@Table(name = "MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m")
    , @NamedQuery(name = "Materia.findByCodigoMat", query = "SELECT m FROM Materia m WHERE m.codigoMat = :codigoMat")
    , @NamedQuery(name = "Materia.findByNombreMat", query = "SELECT m FROM Materia m WHERE m.nombreMat = :nombreMat")
    , @NamedQuery(name = "Materia.findByEstadoMat", query = "SELECT m FROM Materia m WHERE m.estadoMat = :estadoMat")
    , @NamedQuery(name = "Materia.findByFechaActMat", query = "SELECT m FROM Materia m WHERE m.fechaActMat = :fechaActMat")})
public class Materia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_MAT")
    private Integer codigoMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NOMBRE_MAT")
    private String nombreMat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_MAT")
    private String estadoMat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_MAT")
    @Temporal(TemporalType.DATE)
    private Date fechaActMat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMat", fetch = FetchType.LAZY)
    private List<Documento> documentoList;

    public Materia() {
    }

    public Materia(Integer codigoMat) {
        this.codigoMat = codigoMat;
    }

    public Materia(Integer codigoMat, String nombreMat, String estadoMat, Date fechaActMat) {
        this.codigoMat = codigoMat;
        this.nombreMat = nombreMat;
        this.estadoMat = estadoMat;
        this.fechaActMat = fechaActMat;
    }

    public Integer getCodigoMat() {
        return codigoMat;
    }

    public void setCodigoMat(Integer codigoMat) {
        this.codigoMat = codigoMat;
    }

    public String getNombreMat() {
        return nombreMat;
    }

    public void setNombreMat(String nombreMat) {
        this.nombreMat = nombreMat;
    }

    public String getEstadoMat() {
        return estadoMat;
    }

    public void setEstadoMat(String estadoMat) {
        this.estadoMat = estadoMat;
    }

    public Date getFechaActMat() {
        return fechaActMat;
    }

    public void setFechaActMat(Date fechaActMat) {
        this.fechaActMat = fechaActMat;
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
        hash += (codigoMat != null ? codigoMat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.codigoMat == null && other.codigoMat != null) || (this.codigoMat != null && !this.codigoMat.equals(other.codigoMat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.Materia[ codigoMat=" + codigoMat + " ]";
    }
    
}
