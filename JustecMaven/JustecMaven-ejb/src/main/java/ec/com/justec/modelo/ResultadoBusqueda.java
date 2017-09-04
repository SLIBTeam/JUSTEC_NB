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
@Table(name = "RESULTADO_BUSQUEDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoBusqueda.findAll", query = "SELECT r FROM ResultadoBusqueda r")
    , @NamedQuery(name = "ResultadoBusqueda.findByCodigoRb", query = "SELECT r FROM ResultadoBusqueda r WHERE r.codigoRb = :codigoRb")
    , @NamedQuery(name = "ResultadoBusqueda.findByTextoRb", query = "SELECT r FROM ResultadoBusqueda r WHERE r.textoRb = :textoRb")
    , @NamedQuery(name = "ResultadoBusqueda.findByFechaRb", query = "SELECT r FROM ResultadoBusqueda r WHERE r.fechaRb = :fechaRb")})
public class ResultadoBusqueda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_RB")
    private Integer codigoRb;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "TEXTO_RB")
    private String textoRb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_RB")
    @Temporal(TemporalType.DATE)
    private Date fechaRb;
    @JoinColumn(name = "CODIGO_DOC", referencedColumnName = "CODIGO_DOC")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Documento codigoDoc;
    @JoinColumn(name = "USUARIO_RB", referencedColumnName = "CODIGO_US")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioRb;

    public ResultadoBusqueda() {
    }

    public ResultadoBusqueda(Integer codigoRb) {
        this.codigoRb = codigoRb;
    }

    public ResultadoBusqueda(Integer codigoRb, String textoRb, Date fechaRb) {
        this.codigoRb = codigoRb;
        this.textoRb = textoRb;
        this.fechaRb = fechaRb;
    }

    public Integer getCodigoRb() {
        return codigoRb;
    }

    public void setCodigoRb(Integer codigoRb) {
        this.codigoRb = codigoRb;
    }

    public String getTextoRb() {
        return textoRb;
    }

    public void setTextoRb(String textoRb) {
        this.textoRb = textoRb;
    }

    public Date getFechaRb() {
        return fechaRb;
    }

    public void setFechaRb(Date fechaRb) {
        this.fechaRb = fechaRb;
    }

    public Documento getCodigoDoc() {
        return codigoDoc;
    }

    public void setCodigoDoc(Documento codigoDoc) {
        this.codigoDoc = codigoDoc;
    }

    public Usuario getUsuarioRb() {
        return usuarioRb;
    }

    public void setUsuarioRb(Usuario usuarioRb) {
        this.usuarioRb = usuarioRb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRb != null ? codigoRb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoBusqueda)) {
            return false;
        }
        ResultadoBusqueda other = (ResultadoBusqueda) object;
        if ((this.codigoRb == null && other.codigoRb != null) || (this.codigoRb != null && !this.codigoRb.equals(other.codigoRb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.ResultadoBusqueda[ codigoRb=" + codigoRb + " ]";
    }
    
}
