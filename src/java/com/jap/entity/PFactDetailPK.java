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
import javax.validation.constraints.Size;

/**
 *
 * @author fernando_batres
 */
@Embeddable
public class PFactDetailPK implements Serializable {
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
    @Size(min = 1, max = 20)
    @Column(name = "id_factura")
    private String idFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prod")
    private int idProd;

    public PFactDetailPK() {
    }

    public PFactDetailPK(int idEmpresa, int idAlmacen, String idFactura, int idProd) {
        this.idEmpresa = idEmpresa;
        this.idAlmacen = idAlmacen;
        this.idFactura = idFactura;
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

    public String getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(String idFactura) {
        this.idFactura = idFactura;
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
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        hash += (int) idProd;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PFactDetailPK)) {
            return false;
        }
        PFactDetailPK other = (PFactDetailPK) object;
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idAlmacen != other.idAlmacen) {
            return false;
        }
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        if (this.idProd != other.idProd) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.catalogo.service.PFactDetailPK[ idEmpresa=" + idEmpresa + ", idAlmacen=" + idAlmacen + ", idFactura=" + idFactura + ", idProd=" + idProd + " ]";
    }
    
}
