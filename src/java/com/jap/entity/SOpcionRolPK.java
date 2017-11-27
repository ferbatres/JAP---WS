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
public class SOpcionRolPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_opcion")
    private int idOpcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private int idEmpresa;

    public SOpcionRolPK() {
    }

    public SOpcionRolPK(int idRol, int idOpcion) {
        this.idRol = idRol;
        this.idOpcion = idOpcion;
    }

    public int getIdRol() {
        return idRol;
    }

   
    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(int idOpcion) {
        this.idOpcion = idOpcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (int) idOpcion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SOpcionRolPK)) {
            return false;
        }
        SOpcionRolPK other = (SOpcionRolPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idOpcion != other.idOpcion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SOpcionRolPK[ idRol=" + idRol + ", idOpcion=" + idOpcion + " ]";
    }
    
}
