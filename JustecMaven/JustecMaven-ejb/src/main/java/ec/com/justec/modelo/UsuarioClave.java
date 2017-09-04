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
@Table(name = "USUARIO_CLAVE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioClave.findAll", query = "SELECT u FROM UsuarioClave u")
    , @NamedQuery(name = "UsuarioClave.findByCodigoUsc", query = "SELECT u FROM UsuarioClave u WHERE u.codigoUsc = :codigoUsc")
    , @NamedQuery(name = "UsuarioClave.findByClaveUsc", query = "SELECT u FROM UsuarioClave u WHERE u.claveUsc = :claveUsc")
    , @NamedQuery(name = "UsuarioClave.findByEstadoUsc", query = "SELECT u FROM UsuarioClave u WHERE u.estadoUsc = :estadoUsc")
    , @NamedQuery(name = "UsuarioClave.findByFechaActUsc", query = "SELECT u FROM UsuarioClave u WHERE u.fechaActUsc = :fechaActUsc")})
public class UsuarioClave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_USC")
    private Integer codigoUsc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "CLAVE_USC")
    private String claveUsc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO_USC")
    private String estadoUsc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_USC")
    @Temporal(TemporalType.DATE)
    private Date fechaActUsc;
    @JoinColumn(name = "CODIGO_US", referencedColumnName = "CODIGO_US")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario codigoUs;

    public UsuarioClave() {
    }

    public UsuarioClave(Integer codigoUsc) {
        this.codigoUsc = codigoUsc;
    }

    public UsuarioClave(Integer codigoUsc, String claveUsc, String estadoUsc, Date fechaActUsc) {
        this.codigoUsc = codigoUsc;
        this.claveUsc = claveUsc;
        this.estadoUsc = estadoUsc;
        this.fechaActUsc = fechaActUsc;
    }

    public Integer getCodigoUsc() {
        return codigoUsc;
    }

    public void setCodigoUsc(Integer codigoUsc) {
        this.codigoUsc = codigoUsc;
    }

    public String getClaveUsc() {
        return claveUsc;
    }

    public void setClaveUsc(String claveUsc) {
        this.claveUsc = claveUsc;
    }

    public String getEstadoUsc() {
        return estadoUsc;
    }

    public void setEstadoUsc(String estadoUsc) {
        this.estadoUsc = estadoUsc;
    }

    public Date getFechaActUsc() {
        return fechaActUsc;
    }

    public void setFechaActUsc(Date fechaActUsc) {
        this.fechaActUsc = fechaActUsc;
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
        hash += (codigoUsc != null ? codigoUsc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioClave)) {
            return false;
        }
        UsuarioClave other = (UsuarioClave) object;
        if ((this.codigoUsc == null && other.codigoUsc != null) || (this.codigoUsc != null && !this.codigoUsc.equals(other.codigoUsc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.UsuarioClave[ codigoUsc=" + codigoUsc + " ]";
    }
    
}
