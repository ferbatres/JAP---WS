/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CProducto.findAll", query = "SELECT c FROM CProducto c"),
    @NamedQuery(name = "CProducto.findById", query = "SELECT c FROM CProducto c WHERE c.id = :id"),
    @NamedQuery(name = "CProducto.findByCodigo", query = "SELECT c FROM CProducto c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CProducto.findByDescripcion", query = "SELECT c FROM CProducto c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CProducto.findByStock", query = "SELECT c FROM CProducto c WHERE c.stock = :stock"),
    @NamedQuery(name = "CProducto.findByServicio", query = "SELECT c FROM CProducto c WHERE c.servicio = :servicio"),
    @NamedQuery(name = "CProducto.findByStockMinimo", query = "SELECT c FROM CProducto c WHERE c.stockMinimo = :stockMinimo"),
    @NamedQuery(name = "CProducto.findByStockMaximo", query = "SELECT c FROM CProducto c WHERE c.stockMaximo = :stockMaximo"),
    @NamedQuery(name = "CProducto.findBySuspendido", query = "SELECT c FROM CProducto c WHERE c.suspendido = :suspendido"),
    @NamedQuery(name = "CProducto.findByCostoCompra", query = "SELECT c FROM CProducto c WHERE c.costoCompra = :costoCompra"),
    @NamedQuery(name = "CProducto.findByCostoFob", query = "SELECT c FROM CProducto c WHERE c.costoFob = :costoFob"),
    @NamedQuery(name = "CProducto.findByCostoContable", query = "SELECT c FROM CProducto c WHERE c.costoContable = :costoContable"),
    @NamedQuery(name = "CProducto.findByUltimoCostoSImpuesto", query = "SELECT c FROM CProducto c WHERE c.ultimoCostoSImpuesto = :ultimoCostoSImpuesto"),
    @NamedQuery(name = "CProducto.findByUltimoCostoCImpuesto", query = "SELECT c FROM CProducto c WHERE c.ultimoCostoCImpuesto = :ultimoCostoCImpuesto"),
    @NamedQuery(name = "CProducto.findByCostoPromSImpuesto", query = "SELECT c FROM CProducto c WHERE c.costoPromSImpuesto = :costoPromSImpuesto"),
    @NamedQuery(name = "CProducto.findByCostoPromCImpuesto", query = "SELECT c FROM CProducto c WHERE c.costoPromCImpuesto = :costoPromCImpuesto"),
    @NamedQuery(name = "CProducto.findByCostoAnteriorCImpuesto", query = "SELECT c FROM CProducto c WHERE c.costoAnteriorCImpuesto = :costoAnteriorCImpuesto"),
    @NamedQuery(name = "CProducto.findByUtilidad1", query = "SELECT c FROM CProducto c WHERE c.utilidad1 = :utilidad1"),
    @NamedQuery(name = "CProducto.findByPrecio1", query = "SELECT c FROM CProducto c WHERE c.precio1 = :precio1"),
    @NamedQuery(name = "CProducto.findByUtilidad2", query = "SELECT c FROM CProducto c WHERE c.utilidad2 = :utilidad2"),
    @NamedQuery(name = "CProducto.findByPrecio2", query = "SELECT c FROM CProducto c WHERE c.precio2 = :precio2"),
    @NamedQuery(name = "CProducto.findByUtildad3", query = "SELECT c FROM CProducto c WHERE c.utildad3 = :utildad3"),
    @NamedQuery(name = "CProducto.findByPrecio3", query = "SELECT c FROM CProducto c WHERE c.precio3 = :precio3"),
    @NamedQuery(name = "CProducto.findByOem", query = "SELECT c FROM CProducto c WHERE c.oem = :oem")})
public class CProducto implements Serializable {
    @Column(name = "fecha_recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_marca", nullable=false,columnDefinition = "default null")
    private Integer idmarca;
    @Column(name = "id_modelo", nullable=false,columnDefinition = "default null")
    private Integer idmodelo;
    @Column(name = "id_categoria", nullable=false,columnDefinition = "default null")
    private Integer idcategoria;  
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "servicio")
    private Boolean servicio;
    @Column(name = "stock_minimo")
    private Integer stockMinimo;
    @Column(name = "stock_maximo")
    private Integer stockMaximo;
    @Column(name = "suspendido")
    private Boolean suspendido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_compra")
    private BigDecimal costoCompra;
    @Column(name = "costo_fob")
    private BigDecimal costoFob;
    @Column(name = "costo_contable")
    private BigDecimal costoContable;
    @Column(name = "ultimo_costo_s_impuesto")
    private BigDecimal ultimoCostoSImpuesto;
    @Column(name = "ultimo_costo_c_impuesto")
    private BigDecimal ultimoCostoCImpuesto;
    @Column(name = "costo_prom_s_impuesto")
    private BigDecimal costoPromSImpuesto;
    @Column(name = "costo_prom_c_impuesto")
    private BigDecimal costoPromCImpuesto;
    @Column(name = "costo_anterior_c_impuesto")
    private BigDecimal costoAnteriorCImpuesto;
    @Column(name = "utilidad1")
    private BigDecimal utilidad1;
    @Column(name = "precio1")
    private BigDecimal precio1;
    @Column(name = "utilidad2")
    private BigDecimal utilidad2;
    @Column(name = "precio2")
    private BigDecimal precio2;
    @Column(name = "utildad3")
    private BigDecimal utildad3;
    @Column(name = "precio3")
    private BigDecimal precio3;
    @Size(max = 50)
    @Column(name = "OEM")
    private String oem;
    
    @JoinColumns({
        @JoinColumn(name = "id_marca", referencedColumnName = "id_marca", insertable = false, updatable = false),
        @JoinColumn(name = "id_modelo", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CModelo cmodelo;

    @PrimaryKeyJoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CMarca cmarca;
     
    @PrimaryKeyJoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CCategoria ccategoria;

    public CProducto() {
    }

    public CProducto(Integer id) {
        this.id = id;
    }
    
    public CProducto(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CModelo getCmodelo() {
        return cmodelo;
    }

    public void setCmodelo(CModelo cmodelo) {
        this.cmodelo = cmodelo;
    }

    public CMarca getCmarca() {
        return cmarca;
    }

    public void setCmarca(CMarca cmarca) {
        this.cmarca = cmarca;
    }

    public CCategoria getCcategoria() {
        return ccategoria;
    }

    public void setCcategoria(CCategoria ccategoria) {
        this.ccategoria = ccategoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getServicio() {
        return servicio;
    }

    public void setServicio(Boolean servicio) {
        this.servicio = servicio;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Integer getStockMaximo() {
        return stockMaximo;
    }

    public void setStockMaximo(Integer stockMaximo) {
        this.stockMaximo = stockMaximo;
    }

    public Boolean getSuspendido() {
        return suspendido;
    }

    public void setSuspendido(Boolean suspendido) {
        this.suspendido = suspendido;
    }

    public BigDecimal getCostoCompra() {
        return costoCompra;
    }

    public void setCostoCompra(BigDecimal costoCompra) {
        this.costoCompra = costoCompra;
    }

    public BigDecimal getCostoFob() {
        return costoFob;
    }

    public void setCostoFob(BigDecimal costoFob) {
        this.costoFob = costoFob;
    }

    public BigDecimal getCostoContable() {
        return costoContable;
    }

    public void setCostoContable(BigDecimal costoContable) {
        this.costoContable = costoContable;
    }

    public BigDecimal getUltimoCostoSImpuesto() {
        return ultimoCostoSImpuesto;
    }

    public void setUltimoCostoSImpuesto(BigDecimal ultimoCostoSImpuesto) {
        this.ultimoCostoSImpuesto = ultimoCostoSImpuesto;
    }

    public BigDecimal getUltimoCostoCImpuesto() {
        return ultimoCostoCImpuesto;
    }

    public void setUltimoCostoCImpuesto(BigDecimal ultimoCostoCImpuesto) {
        this.ultimoCostoCImpuesto = ultimoCostoCImpuesto;
    }

    public BigDecimal getCostoPromSImpuesto() {
        return costoPromSImpuesto;
    }

    public void setCostoPromSImpuesto(BigDecimal costoPromSImpuesto) {
        this.costoPromSImpuesto = costoPromSImpuesto;
    }

    public BigDecimal getCostoPromCImpuesto() {
        return costoPromCImpuesto;
    }

    public void setCostoPromCImpuesto(BigDecimal costoPromCImpuesto) {
        this.costoPromCImpuesto = costoPromCImpuesto;
    }

    public BigDecimal getCostoAnteriorCImpuesto() {
        return costoAnteriorCImpuesto;
    }

    public void setCostoAnteriorCImpuesto(BigDecimal costoAnteriorCImpuesto) {
        this.costoAnteriorCImpuesto = costoAnteriorCImpuesto;
    }

    public BigDecimal getUtilidad1() {
        return utilidad1;
    }

    public void setUtilidad1(BigDecimal utilidad1) {
        this.utilidad1 = utilidad1;
    }

    public BigDecimal getPrecio1() {
        return precio1;
    }

    public void setPrecio1(BigDecimal precio1) {
        this.precio1 = precio1;
    }

    public BigDecimal getUtilidad2() {
        return utilidad2;
    }

    public void setUtilidad2(BigDecimal utilidad2) {
        this.utilidad2 = utilidad2;
    }

    public BigDecimal getPrecio2() {
        return precio2;
    }

    public void setPrecio2(BigDecimal precio2) {
        this.precio2 = precio2;
    }

    public BigDecimal getUtildad3() {
        return utildad3;
    }

    public void setUtildad3(BigDecimal utildad3) {
        this.utildad3 = utildad3;
    }

    public BigDecimal getPrecio3() {
        return precio3;
    }

    public void setPrecio3(BigDecimal precio3) {
        this.precio3 = precio3;
    }

    public String getOem() {
        return oem;
    }

    public void setOem(String oem) {
        this.oem = oem;
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
        if (!(object instanceof CProducto)) {
            return false;
        }
        CProducto other = (CProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Integer getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(Integer idmarca) {
        this.idmarca = idmarca;
    }

    public Integer getIdmodelo() {
        return idmodelo;
    }

    public void setIdmodelo(Integer idmodelo) {
        this.idmodelo = idmodelo;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CProducto[ id=" + id + " ]";
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    
}
