/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando_batres
 */
@Entity
@Table(name = "p_fact_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PFactDetail.findAll", query = "SELECT p FROM PFactDetail p"),
    @NamedQuery(name = "PFactDetail.findByIdEmpresa", query = "SELECT p FROM PFactDetail p WHERE p.pFactDetailPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "PFactDetail.findByIdAlmacen", query = "SELECT p FROM PFactDetail p WHERE p.pFactDetailPK.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "PFactDetail.findByIdFactura", query = "SELECT p FROM PFactDetail p WHERE p.pFactDetailPK.idFactura = :idFactura"),
    @NamedQuery(name = "PFactDetail.findByIdProd", query = "SELECT p FROM PFactDetail p WHERE p.pFactDetailPK.idProd = :idProd"),
    @NamedQuery(name = "PFactDetail.findByQty", query = "SELECT p FROM PFactDetail p WHERE p.qty = :qty"),
    @NamedQuery(name = "PFactDetail.findByPrecioUnitario", query = "SELECT p FROM PFactDetail p WHERE p.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "PFactDetail.findByDescuento", query = "SELECT p FROM PFactDetail p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "PFactDetail.findByStatus", query = "SELECT p FROM PFactDetail p WHERE p.status = :status")})
public class PFactDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PFactDetailPK pFactDetailPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qty")
    private BigDecimal qty;
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_almacen", referencedColumnName = "id_sucursal", insertable = false, updatable = false),
        @JoinColumn(name = "id_factura", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PFacturacion pFacturacion;

    public PFactDetail() {
    }

    public PFactDetail(PFactDetailPK pFactDetailPK) {
        this.pFactDetailPK = pFactDetailPK;
    }

    public PFactDetail(int idEmpresa, int idAlmacen, String idFactura, int idProd) {
        this.pFactDetailPK = new PFactDetailPK(idEmpresa, idAlmacen, idFactura, idProd);
    }

    public PFactDetailPK getPFactDetailPK() {
        return pFactDetailPK;
    }

    public void setPFactDetailPK(PFactDetailPK pFactDetailPK) {
        this.pFactDetailPK = pFactDetailPK;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PFacturacion getPFacturacion() {
        return pFacturacion;
    }

    public void setPFacturacion(PFacturacion pFacturacion) {
        this.pFacturacion = pFacturacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pFactDetailPK != null ? pFactDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PFactDetail)) {
            return false;
        }
        PFactDetail other = (PFactDetail) object;
        if ((this.pFactDetailPK == null && other.pFactDetailPK != null) || (this.pFactDetailPK != null && !this.pFactDetailPK.equals(other.pFactDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.catalogo.service.PFactDetail[ pFactDetailPK=" + pFactDetailPK + " ]";
    }
    
}
