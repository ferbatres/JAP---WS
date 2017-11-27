/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_ciudad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CCiudad.findAll", query = "SELECT c FROM CCiudad c"),
    @NamedQuery(name = "CCiudad.findByDescripcion", query = "SELECT c FROM CCiudad c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CCiudad.findByStatus", query = "SELECT c FROM CCiudad c WHERE c.status = :status")})
public class CCiudad implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_depto")
    private int idDepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_municipio")
    private int idMunicipio;
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

    @JoinColumns({
        @JoinColumn(name = "id_depto", referencedColumnName = "id_depto", insertable = false, updatable = false),
        @JoinColumn(name = "id_municipio", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CMunicipio cMunicipio;
    public CCiudad() {
    }

    public CMunicipio getcMunicipio() {
        return cMunicipio;
    }

    public void setcMunicipio(CMunicipio cMunicipio) {
        this.cMunicipio = cMunicipio;
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
        hash += 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CCiudad)) {
            return false;
        }
       
        return true;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CCiudad[ cCiudadPK=" + getId() + " ]";
    }

   
    
}
