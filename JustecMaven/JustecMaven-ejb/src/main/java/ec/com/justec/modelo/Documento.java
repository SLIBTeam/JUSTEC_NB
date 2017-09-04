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
@Table(name = "DOCUMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findByCodigoDoc", query = "SELECT d FROM Documento d WHERE d.codigoDoc = :codigoDoc")
    , @NamedQuery(name = "Documento.findByTituloDoc", query = "SELECT d FROM Documento d WHERE d.tituloDoc = :tituloDoc")
    , @NamedQuery(name = "Documento.findByNumeroDoc", query = "SELECT d FROM Documento d WHERE d.numeroDoc = :numeroDoc")
    , @NamedQuery(name = "Documento.findByFechaFirmaDoc", query = "SELECT d FROM Documento d WHERE d.fechaFirmaDoc = :fechaFirmaDoc")
    , @NamedQuery(name = "Documento.findByFechaPublicacionDoc", query = "SELECT d FROM Documento d WHERE d.fechaPublicacionDoc = :fechaPublicacionDoc")
    , @NamedQuery(name = "Documento.findByEstadoDoc", query = "SELECT d FROM Documento d WHERE d.estadoDoc = :estadoDoc")
    , @NamedQuery(name = "Documento.findByFechaActDoc", query = "SELECT d FROM Documento d WHERE d.fechaActDoc = :fechaActDoc")
    , @NamedQuery(name = "Documento.findByRutaDoc", query = "SELECT d FROM Documento d WHERE d.rutaDoc = :rutaDoc")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_DOC")
    private Integer codigoDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "TITULO_DOC")
    private String tituloDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_DOC")
    private int numeroDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_FIRMA_DOC")
    @Temporal(TemporalType.DATE)
    private Date fechaFirmaDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_PUBLICACION_DOC")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacionDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ESTADO_DOC")
    private String estadoDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACT_DOC")
    @Temporal(TemporalType.DATE)
    private Date fechaActDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "RUTA_DOC")
    private String rutaDoc;
    @JoinColumn(name = "CODIGO_MAT", referencedColumnName = "CODIGO_MAT")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Materia codigoMat;
    @JoinColumn(name = "CODIGO_SEC", referencedColumnName = "CODIGO_SEC")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Seccion codigoSec;
    @JoinColumn(name = "CODIGO_TN", referencedColumnName = "CODIGO_TN")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoNorma codigoTn;
    @JoinColumn(name = "CODIGO_TP", referencedColumnName = "CODIGO_TP")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPublicacion codigoTp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoDoc", fetch = FetchType.LAZY)
    private List<ResultadoBusqueda> resultadoBusquedaList;

    public Documento() {
    }

    public Documento(Integer codigoDoc) {
        this.codigoDoc = codigoDoc;
    }

    public Documento(Integer codigoDoc, String tituloDoc, int numeroDoc, Date fechaFirmaDoc, Date fechaPublicacionDoc, String estadoDoc, Date fechaActDoc, String rutaDoc) {
        this.codigoDoc = codigoDoc;
        this.tituloDoc = tituloDoc;
        this.numeroDoc = numeroDoc;
        this.fechaFirmaDoc = fechaFirmaDoc;
        this.fechaPublicacionDoc = fechaPublicacionDoc;
        this.estadoDoc = estadoDoc;
        this.fechaActDoc = fechaActDoc;
        this.rutaDoc = rutaDoc;
    }

    public Integer getCodigoDoc() {
        return codigoDoc;
    }

    public void setCodigoDoc(Integer codigoDoc) {
        this.codigoDoc = codigoDoc;
    }

    public String getTituloDoc() {
        return tituloDoc;
    }

    public void setTituloDoc(String tituloDoc) {
        this.tituloDoc = tituloDoc;
    }

    public int getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(int numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public Date getFechaFirmaDoc() {
        return fechaFirmaDoc;
    }

    public void setFechaFirmaDoc(Date fechaFirmaDoc) {
        this.fechaFirmaDoc = fechaFirmaDoc;
    }

    public Date getFechaPublicacionDoc() {
        return fechaPublicacionDoc;
    }

    public void setFechaPublicacionDoc(Date fechaPublicacionDoc) {
        this.fechaPublicacionDoc = fechaPublicacionDoc;
    }

    public String getEstadoDoc() {
        return estadoDoc;
    }

    public void setEstadoDoc(String estadoDoc) {
        this.estadoDoc = estadoDoc;
    }

    public Date getFechaActDoc() {
        return fechaActDoc;
    }

    public void setFechaActDoc(Date fechaActDoc) {
        this.fechaActDoc = fechaActDoc;
    }

    public String getRutaDoc() {
        return rutaDoc;
    }

    public void setRutaDoc(String rutaDoc) {
        this.rutaDoc = rutaDoc;
    }

    public Materia getCodigoMat() {
        return codigoMat;
    }

    public void setCodigoMat(Materia codigoMat) {
        this.codigoMat = codigoMat;
    }

    public Seccion getCodigoSec() {
        return codigoSec;
    }

    public void setCodigoSec(Seccion codigoSec) {
        this.codigoSec = codigoSec;
    }

    public TipoNorma getCodigoTn() {
        return codigoTn;
    }

    public void setCodigoTn(TipoNorma codigoTn) {
        this.codigoTn = codigoTn;
    }

    public TipoPublicacion getCodigoTp() {
        return codigoTp;
    }

    public void setCodigoTp(TipoPublicacion codigoTp) {
        this.codigoTp = codigoTp;
    }

    @XmlTransient
    public List<ResultadoBusqueda> getResultadoBusquedaList() {
        return resultadoBusquedaList;
    }

    public void setResultadoBusquedaList(List<ResultadoBusqueda> resultadoBusquedaList) {
        this.resultadoBusquedaList = resultadoBusquedaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDoc != null ? codigoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.codigoDoc == null && other.codigoDoc != null) || (this.codigoDoc != null && !this.codigoDoc.equals(other.codigoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.justec.modelo.Documento[ codigoDoc=" + codigoDoc + " ]";
    }
    
}
