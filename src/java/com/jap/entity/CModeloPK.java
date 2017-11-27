/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author irvin_monterroza
 */
@Embeddable
public class CModeloPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_marca")
    private int idMarca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public CModeloPK() {
    }

    public CModeloPK(int idMarca, int id) {
        this.idMarca = idMarca;
        this.id = id;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMarca;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CModeloPK)) {
            return false;
        }
        CModeloPK other = (CModeloPK) object;
        if (this.idMarca != other.idMarca) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CModeloPK[ idMarca=" + idMarca + ", id=" + id + " ]";
    }
    
}
