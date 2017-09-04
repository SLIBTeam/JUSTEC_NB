/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.justec.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisp.araujo
 */
@Entity
@Table(name = "USUARIO_ROL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRol.findAll", query = "SELECT u FROM UsuarioRol u")
    , @NamedQuery(name = "UsuarioRol.findByCodigoUsr", query = "SELECT u FROM UsuarioRol u WHERE u.codigoUsr = :codigoUsr")
    , @NamedQuery(name = "UsuarioRol.findByEstadoUsr", query = "SELECT u FROM UsuarioRol u WHERE u.estadoUsr = :estadoUsr")
    , @NamedQuery(name = "UsuarioRol.findByFechaActUsr", query = "SELECT u FROM UsuarioRol u WHERE u.fechaActUsr = :fechaActUsr")})
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_USR")
    private Integer codigoUsr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_USR")
    private String estadoUsr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_USR")
    @Temporal(TemporalType.DATE)
    private Date fechaActUsr;
    @JoinColumn(name = "CODIGO_ROL", referencedColumnName = "CODIGO_ROL")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rol codigoRol;
    @JoinColumn(name = "CODIGO_US", referencedColumnName = "CODIGO_US")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codigoUs;

    public UsuarioRol() {
    }

    public UsuarioRol(Integer codigoUsr) {
        this.codigoUsr = codigoUsr;
    }

    public UsuarioRol(Integer codigoUsr, String estadoUsr, Date fechaActUsr) {
        this.codigoUsr = codigoUsr;
        this.estadoUsr = estadoUsr;
        this.fechaActUsr = fechaActUsr;
    }

    public Integer getCodigoUsr() {
        return codigoUsr;
    }

    public void setCodigoUsr(Integer codigoUsr) {
        this.codigoUsr = codigoUsr;
    }

    public String getEstadoUsr() {
        return estadoUsr;
    }

    public void setEstadoUsr(String estadoUsr) {
        this.estadoUsr = estadoUsr;
    }

    public Date getFechaActUsr() {
        return fechaActUsr;
    }

    public void setFechaActUsr(Date fechaActUsr) {
        this.fechaActUsr = fechaActUsr;
    }

    public Rol getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Rol codigoRol) {
        this.codigoRol = codigoRol;
    }

    public Usuario getCodigoUs() {
        return codigoUs;
    }

    public void setCodigoUs(Usuario codigoUs) {
        this.codigoUs = codigoUs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsr != null ? codigoUsr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRol)) {
            return false;
        }
        UsuarioRol other = (UsuarioRol) object;
        if ((this.codigoUsr == null && other.codigoUsr != null) || (this.codigoUsr != null && !this.codigoUsr.equals(other.codigoUsr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.UsuarioRol[ codigoUsr=" + codigoUsr + " ]";
    }
    
}
