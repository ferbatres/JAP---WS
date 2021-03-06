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
public class SOpcionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private int idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opc_principal")
    private int idOpcPrincipal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;

    public SOpcionPK() {
    }

    public SOpcionPK(int idEmpresa, int idOpcPrincipal, int id) {
        this.idEmpresa = idEmpresa;
        this.idOpcPrincipal = idOpcPrincipal;
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdOpcPrincipal() {
        return idOpcPrincipal;
    }

    public void setIdOpcPrincipal(int idOpcPrincipal) {
        this.idOpcPrincipal = idOpcPrincipal;
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
        hash += (int) idEmpresa;
        hash += (int) idOpcPrincipal;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SOpcionPK)) {
            return false;
        }
        SOpcionPK other = (SOpcionPK) object;
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idOpcPrincipal != other.idOpcPrincipal) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SOpcionPK[ idEmpresa=" + idEmpresa + ", idOpcPrincipal=" + idOpcPrincipal + ", id=" + id + " ]";
    }
    
}
