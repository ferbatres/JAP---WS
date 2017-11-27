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
 * @author fernando_batres
 */
@Embeddable
public class PProdTiendaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private int idEmpresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_almacen")
    private int idAlmacen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prod")
    private int idProd;

    public PProdTiendaPK() {
    }

    public PProdTiendaPK(int idEmpresa, int idAlmacen, int idProd) {
        this.idEmpresa = idEmpresa;
        this.idAlmacen = idAlmacen;
        this.idProd = idProd;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpresa;
        hash += (int) idAlmacen;
        hash += (int) idProd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PProdTiendaPK)) {
            return false;
        }
        PProdTiendaPK other = (PProdTiendaPK) object;
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idAlmacen != other.idAlmacen) {
            return false;
        }
        if (this.idProd != other.idProd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.PProdTiendaPK[ idEmpresa=" + idEmpresa + ", idAlmacen=" + idAlmacen + ", idProd=" + idProd + " ]";
    }
    
}
