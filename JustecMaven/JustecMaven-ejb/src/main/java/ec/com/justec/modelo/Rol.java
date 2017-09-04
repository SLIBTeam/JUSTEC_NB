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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByCodigoRol", query = "SELECT r FROM Rol r WHERE r.codigoRol = :codigoRol")
    , @NamedQuery(name = "Rol.findByNombreRol", query = "SELECT r FROM Rol r WHERE r.nombreRol = :nombreRol")
    , @NamedQuery(name = "Rol.findByEstadoRol", query = "SELECT r FROM Rol r WHERE r.estadoRol = :estadoRol")
    , @NamedQuery(name = "Rol.findByFechaActRol", query = "SELECT r FROM Rol r WHERE r.fechaActRol = :fechaActRol")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_ROL")
    private Integer codigoRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_ROL")
    private String estadoRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_ROL")
    @Temporal(TemporalType.DATE)
    private Date fechaActRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoRol", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;
    @JoinColumn(name = "CODIGO_TR", referencedColumnName = "CODIGO_TR")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRol codigoTr;

    public Rol() {
    }

    public Rol(Integer codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Rol(Integer codigoRol, String nombreRol, String estadoRol, Date fechaActRol) {
        this.codigoRol = codigoRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
        this.fechaActRol = fechaActRol;
    }

    public Integer getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Integer codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(String estadoRol) {
        this.estadoRol = estadoRol;
    }

    public Date getFechaActRol() {
        return fechaActRol;
    }

    public void setFechaActRol(Date fechaActRol) {
        this.fechaActRol = fechaActRol;
    }

    @XmlTransient
    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    public TipoRol getCodigoTr() {
        return codigoTr;
    }

    public void setCodigoTr(TipoRol codigoTr) {
        this.codigoTr = codigoTr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRol != null ? codigoRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.codigoRol == null && other.codigoRol != null) || (this.codigoRol != null && !this.codigoRol.equals(other.codigoRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.Rol[ codigoRol=" + codigoRol + " ]";
    }
    
}
