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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodigoUs", query = "SELECT u FROM Usuario u WHERE u.codigoUs = :codigoUs")
    , @NamedQuery(name = "Usuario.findByNombreUs", query = "SELECT u FROM Usuario u WHERE u.nombreUs = :nombreUs")
    , @NamedQuery(name = "Usuario.findByEstadoUs", query = "SELECT u FROM Usuario u WHERE u.estadoUs = :estadoUs")
    , @NamedQuery(name = "Usuario.findByFechaActUs", query = "SELECT u FROM Usuario u WHERE u.fechaActUs = :fechaActUs")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_US")
    private Integer codigoUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "NOMBRE_US")
    private String nombreUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_US")
    private String estadoUs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_US")
    @Temporal(TemporalType.DATE)
    private Date fechaActUs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUs", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuarioRolList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioRb", fetch = FetchType.LAZY)
    private List<ResultadoBusqueda> resultadoBusquedaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoUs", fetch = FetchType.LAZY)
    private List<UsuarioClave> usuarioClaveList;

    public Usuario() {
    }

    public Usuario(Integer codigoUs) {
        this.codigoUs = codigoUs;
    }

    public Usuario(Integer codigoUs, String nombreUs, String estadoUs, Date fechaActUs) {
        this.codigoUs = codigoUs;
        this.nombreUs = nombreUs;
        this.estadoUs = estadoUs;
        this.fechaActUs = fechaActUs;
    }

    public Integer getCodigoUs() {
        return codigoUs;
    }

    public void setCodigoUs(Integer codigoUs) {
        this.codigoUs = codigoUs;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getEstadoUs() {
        return estadoUs;
    }

    public void setEstadoUs(String estadoUs) {
        this.estadoUs = estadoUs;
    }

    public Date getFechaActUs() {
        return fechaActUs;
    }

    public void setFechaActUs(Date fechaActUs) {
        this.fechaActUs = fechaActUs;
    }

    @XmlTransient
    public List<UsuarioRol> getUsuarioRolList() {
        return usuarioRolList;
    }

    public void setUsuarioRolList(List<UsuarioRol> usuarioRolList) {
        this.usuarioRolList = usuarioRolList;
    }

    @XmlTransient
    public List<ResultadoBusqueda> getResultadoBusquedaList() {
        return resultadoBusquedaList;
    }

    public void setResultadoBusquedaList(List<ResultadoBusqueda> resultadoBusquedaList) {
        this.resultadoBusquedaList = resultadoBusquedaList;
    }

    @XmlTransient
    public List<UsuarioClave> getUsuarioClaveList() {
        return usuarioClaveList;
    }

    public void setUsuarioClaveList(List<UsuarioClave> usuarioClaveList) {
        this.usuarioClaveList = usuarioClaveList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUs != null ? codigoUs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigoUs == null && other.codigoUs != null) || (this.codigoUs != null && !this.codigoUs.equals(other.codigoUs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.Usuario[ codigoUs=" + codigoUs + " ]";
    }
    
}
