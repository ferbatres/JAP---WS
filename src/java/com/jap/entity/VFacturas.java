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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernando_batres
 */
@Entity
@Table(name = "v_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VFacturas.findAll", query = "SELECT v FROM VFacturas v"),
    @NamedQuery(name = "VFacturas.findByEmpresa", query = "SELECT v FROM VFacturas v WHERE v.empresa = :empresa"),
    @NamedQuery(name = "VFacturas.findByIdTipoDocumento", query = "SELECT v FROM VFacturas v WHERE v.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "VFacturas.findByTipoDocumento", query = "SELECT v FROM VFacturas v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VFacturas.findByFecha", query = "SELECT v FROM VFacturas v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VFacturas.findByCorrelativo", query = "SELECT v FROM VFacturas v WHERE v.correlativo = :correlativo"),
    @NamedQuery(name = "VFacturas.findByIdCliente", query = "SELECT v FROM VFacturas v WHERE v.idCliente = :idCliente"),
    @NamedQuery(name = "VFacturas.findByCliente", query = "SELECT v FROM VFacturas v WHERE v.cliente = :cliente"),
    @NamedQuery(name = "VFacturas.findByTotal", query = "SELECT v FROM VFacturas v WHERE v.total = :total"),
    @NamedQuery(name = "VFacturas.findByIdFormaPago", query = "SELECT v FROM VFacturas v WHERE v.idFormaPago = :idFormaPago"),
    @NamedQuery(name = "VFacturas.findByFormaPago", query = "SELECT v FROM VFacturas v WHERE v.formaPago = :formaPago"),
    @NamedQuery(name = "VFacturas.findByIdSucursal", query = "SELECT v FROM VFacturas v WHERE v.idSucursal = :idSucursal"),
    @NamedQuery(name = "VFacturas.findBySucursal", query = "SELECT v FROM VFacturas v WHERE v.sucursal = :sucursal"),
    @NamedQuery(name = "VFacturas.findByIdVendedor", query = "SELECT v FROM VFacturas v WHERE v.idVendedor = :idVendedor"),
    @NamedQuery(name = "VFacturas.findByVendedor", query = "SELECT v FROM VFacturas v WHERE v.vendedor = :vendedor")})
public class VFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "empresa")
    private int empresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_documento")
    private int idTipoDocumento;
    @Size(max = 50)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "correlativo")
    private String correlativo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Size(max = 200)
    @Column(name = "cliente")
    private String cliente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_forma_pago")
    private int idFormaPago;
    @Size(max = 50)
    @Column(name = "forma_pago")
    private String formaPago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sucursal")
    private int idSucursal;
    @Size(max = 125)
    @Column(name = "sucursal")
    private String sucursal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vendedor")
    private int idVendedor;
    @Size(max = 200)
    @Column(name = "vendedor")
    private String vendedor;
    @Size(max = 200)
    @Column(name = "estado")
    private String estado;

    public VFacturas() {
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
