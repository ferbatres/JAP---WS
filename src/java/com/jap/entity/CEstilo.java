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
@Table(name = "c_estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CEstilo.findAll", query = "SELECT c FROM CEstilo c"),
    @NamedQuery(name = "CEstilo.findById", query = "SELECT c FROM CEstilo c WHERE c.id = :id"),
    @NamedQuery(name = "CEstilo.findByDescripcion", query = "SELECT c FROM CEstilo c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CEstilo.findByStatus", query = "SELECT c FROM CEstilo c WHERE c.status = :status")})
public class CEstilo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 125)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public CEstilo() {
    }

    public CEstilo(Integer id) {
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
        if (!(object instanceof CEstilo)) {
            return false;
        }
        CEstilo other = (CEstilo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CEstilo[ id=" + id + " ]";
    }
    
}
