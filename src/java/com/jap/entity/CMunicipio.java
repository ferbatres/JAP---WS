/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CMunicipio.findAll", query = "SELECT c FROM CMunicipio c"),
    @NamedQuery(name = "CMunicipio.findByIdDepto", query = "SELECT c FROM CMunicipio c WHERE c.cMunicipioPK.idDepto = :idDepto"),
    @NamedQuery(name = "CMunicipio.findById", query = "SELECT c FROM CMunicipio c WHERE c.cMunicipioPK.id = :id"),
    @NamedQuery(name = "CMunicipio.findByDescdescripcion", query = "SELECT c FROM CMunicipio c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CMunicipio.findByStatus", query = "SELECT c FROM CMunicipio c WHERE c.status = :status")})
public class CMunicipio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CMunicipioPK cMunicipioPK;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @PrimaryKeyJoinColumn(name = "id_depto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CDepto cDepto;

    public CMunicipio() {
    }

    public CMunicipio(CMunicipioPK cMunicipioPK) {
        this.cMunicipioPK = cMunicipioPK;
    }

    public CMunicipio(int idDepto, int id) {
        this.cMunicipioPK = new CMunicipioPK(idDepto, id);
    }

    public CMunicipioPK getCMunicipioPK() {
        return cMunicipioPK;
    }

    public void setCMunicipioPK(CMunicipioPK cMunicipioPK) {
        this.cMunicipioPK = cMunicipioPK;
    }

    public CMunicipioPK getcMunicipioPK() {
        return cMunicipioPK;
    }

    public void setcMunicipioPK(CMunicipioPK cMunicipioPK) {
        this.cMunicipioPK = cMunicipioPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CDepto getcDepto() {
        return cDepto;
    }

    public void setcDepto(CDepto cDepto) {
        this.cDepto = cDepto;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CDepto getCDepto() {
        return cDepto;
    }

    public void setCDepto(CDepto cDepto) {
        this.cDepto = cDepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cMunicipioPK != null ? cMunicipioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CMunicipio)) {
            return false;
        }
        CMunicipio other = (CMunicipio) object;
        if ((this.cMunicipioPK == null && other.cMunicipioPK != null) || (this.cMunicipioPK != null && !this.cMunicipioPK.equals(other.cMunicipioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CMunicipio[ cMunicipioPK=" + cMunicipioPK + " ]";
    }

   
}
