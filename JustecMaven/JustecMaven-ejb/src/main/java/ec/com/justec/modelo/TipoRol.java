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
@Table(name = "TIPO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRol.findAll", query = "SELECT t FROM TipoRol t")
    , @NamedQuery(name = "TipoRol.findByCodigoTr", query = "SELECT t FROM TipoRol t WHERE t.codigoTr = :codigoTr")
    , @NamedQuery(name = "TipoRol.findByNombreTr", query = "SELECT t FROM TipoRol t WHERE t.nombreTr = :nombreTr")
    , @NamedQuery(name = "TipoRol.findByEstadoTr", query = "SELECT t FROM TipoRol t WHERE t.estadoTr = :estadoTr")
    , @NamedQuery(name = "TipoRol.findByFechaActTr", query = "SELECT t FROM TipoRol t WHERE t.fechaActTr = :fechaActTr")})
public class TipoRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_TR")
    private Integer codigoTr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_TR")
    private String nombreTr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_TR")
    private String estadoTr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_TR")
    @Temporal(TemporalType.DATE)
    private Date fechaActTr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoTr", fetch = FetchType.LAZY)
    private List<Rol> rolList;

    public TipoRol() {
    }

    public TipoRol(Integer codigoTr) {
        this.codigoTr = codigoTr;
    }

    public TipoRol(Integer codigoTr, String nombreTr, String estadoTr, Date fechaActTr) {
        this.codigoTr = codigoTr;
        this.nombreTr = nombreTr;
        this.estadoTr = estadoTr;
        this.fechaActTr = fechaActTr;
    }

    public Integer getCodigoTr() {
        return codigoTr;
    }

    public void setCodigoTr(Integer codigoTr) {
        this.codigoTr = codigoTr;
    }

    public String getNombreTr() {
        return nombreTr;
    }

    public void setNombreTr(String nombreTr) {
        this.nombreTr = nombreTr;
    }

    public String getEstadoTr() {
        return estadoTr;
    }

    public void setEstadoTr(String estadoTr) {
        this.estadoTr = estadoTr;
    }

    public Date getFechaActTr() {
        return fechaActTr;
    }

    public void setFechaActTr(Date fechaActTr) {
        this.fechaActTr = fechaActTr;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTr != null ? codigoTr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRol)) {
            return false;
        }
        TipoRol other = (TipoRol) object;
        if ((this.codigoTr == null && other.codigoTr != null) || (this.codigoTr != null && !this.codigoTr.equals(other.codigoTr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.TipoRol[ codigoTr=" + codigoTr + " ]";
    }
    
}
