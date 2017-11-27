/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CEmpresa.findAll", query = "SELECT c FROM CEmpresa c"),
    @NamedQuery(name = "CEmpresa.findById", query = "SELECT c FROM CEmpresa c WHERE c.id = :id"),
    @NamedQuery(name = "CEmpresa.findByDescripcion", query = "SELECT c FROM CEmpresa c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CEmpresa.findByStatus", query = "SELECT c FROM CEmpresa c WHERE c.status = :status")})
public class CEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "logo")
    private String logo;
    @Size(max = 1)
    @Column(name = "estado")
    private String status;

    public CEmpresa() {
    }

    public CEmpresa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CEmpresa)) {
            return false;
        }
        CEmpresa other = (CEmpresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CEmpresa[ id=" + id + " ]";
    }
    
}
