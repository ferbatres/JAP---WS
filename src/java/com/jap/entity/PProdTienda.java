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
@Table(name = "p_prod_tienda", catalog = "JAP", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PProdTienda.findAll", query = "SELECT p FROM PProdTienda p"),
    @NamedQuery(name = "PProdTienda.findByIdEmpresa", query = "SELECT p FROM PProdTienda p WHERE p.pProdTiendaPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "PProdTienda.findByIdAlmacen", query = "SELECT p FROM PProdTienda p WHERE p.pProdTiendaPK.idAlmacen = :idAlmacen"),
    @NamedQuery(name = "PProdTienda.findByIdProd", query = "SELECT p FROM PProdTienda p WHERE p.pProdTiendaPK.idProd = :idProd"),
    @NamedQuery(name = "PProdTienda.findByStock", query = "SELECT p FROM PProdTienda p WHERE p.stock = :stock"),
    @NamedQuery(name = "PProdTienda.findByStockInTransit", query = "SELECT p FROM PProdTienda p WHERE p.stockInTransit = :stockInTransit"),
    @NamedQuery(name = "PProdTienda.findByStockPedido", query = "SELECT p FROM PProdTienda p WHERE p.stockPedido = :stockPedido"),
    @NamedQuery(name = "PProdTienda.findByStatus", query = "SELECT p FROM PProdTienda p WHERE p.status = :status")})
public class PProdTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PProdTiendaPK pProdTiendaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "stock")
    private BigDecimal stock;
    @Column(name = "stock_in_transit")
    private BigDecimal stockInTransit;
    @Column(name = "stock_pedido")
    private BigDecimal stockPedido;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_prod", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CProducto cProducto;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_almacen", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CAlmacen cAlmacen;

    public PProdTienda() {
    }

    public PProdTienda(PProdTiendaPK pProdTiendaPK) {
        this.pProdTiendaPK = pProdTiendaPK;
    }

    public PProdTienda(int idEmpresa, int idAlmacen, int idProd) {
        this.pProdTiendaPK = new PProdTiendaPK(idEmpresa, idAlmacen, idProd);
    }

    public PProdTiendaPK getPProdTiendaPK() {
        return pProdTiendaPK;
    }

    public void setPProdTiendaPK(PProdTiendaPK pProdTiendaPK) {
        this.pProdTiendaPK = pProdTiendaPK;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public BigDecimal getStockInTransit() {
        return stockInTransit;
    }

    public void setStockInTransit(BigDecimal stockInTransit) {
        this.stockInTransit = stockInTransit;
    }

    public BigDecimal getStockPedido() {
        return stockPedido;
    }

    public void setStockPedido(BigDecimal stockPedido) {
        this.stockPedido = stockPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CProducto getCProducto() {
        return cProducto;
    }

    public void setCProducto(CProducto cProducto) {
        this.cProducto = cProducto;
    }

    public CAlmacen getCAlmacen() {
        return cAlmacen;
    }

    public void setCAlmacen(CAlmacen cAlmacen) {
        this.cAlmacen = cAlmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pProdTiendaPK != null ? pProdTiendaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PProdTienda)) {
            return false;
        }
        PProdTienda other = (PProdTienda) object;
        if ((this.pProdTiendaPK == null && other.pProdTiendaPK != null) || (this.pProdTiendaPK != null && !this.pProdTiendaPK.equals(other.pProdTiendaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.PProdTienda[ pProdTiendaPK=" + pProdTiendaPK + " ]";
    }
    
}
