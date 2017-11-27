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
@Table(name = "c_proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CProveedor.findAll", query = "SELECT c FROM CProveedor c"),
    @NamedQuery(name = "CProveedor.findById", query = "SELECT c FROM CProveedor c WHERE c.id = :id"),
    @NamedQuery(name = "CProveedor.findByDescripcion", query = "SELECT c FROM CProveedor c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CProveedor.findByTel1", query = "SELECT c FROM CProveedor c WHERE c.tel1 = :tel1"),
    @NamedQuery(name = "CProveedor.findByTel2", query = "SELECT c FROM CProveedor c WHERE c.tel2 = :tel2"),
    @NamedQuery(name = "CProveedor.findByDireccion", query = "SELECT c FROM CProveedor c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CProveedor.findByProvMoto", query = "SELECT c FROM CProveedor c WHERE c.provMoto = :provMoto"),
    @NamedQuery(name = "CProveedor.findByStatus", query = "SELECT c FROM CProveedor c WHERE c.status = :status")})
public class CProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 12)
    @Column(name = "tel1")
    private String tel1;
    @Size(max = 12)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 1)
    @Column(name = "prov_moto")
    private String provMoto;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public CProveedor() {
    }

    public CProveedor(Integer id) {
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

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvMoto() {
        return provMoto;
    }

    public void setProvMoto(String provMoto) {
        this.provMoto = provMoto;
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
        if (!(object instanceof CProveedor)) {
            return false;
        }
        CProveedor other = (CProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CProveedor[ id=" + id + " ]";
    }
    
}
