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
public class CMunicipioPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_depto")
    private int idDepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public CMunicipioPK() {
    }

    public CMunicipioPK(int idDepto, int id) {
        this.idDepto = idDepto;
        this.id = id;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
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
        hash += (int) idDepto;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CMunicipioPK)) {
            return false;
        }
        CMunicipioPK other = (CMunicipioPK) object;
        if (this.idDepto != other.idDepto) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CMunicipioPK[ idDepto=" + idDepto + ", id=" + id + " ]";
    }
    
}
