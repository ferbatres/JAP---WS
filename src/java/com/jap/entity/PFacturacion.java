/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fernando_batres
 */
@Entity
@Table(name = "p_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PFacturacion.findAll", query = "SELECT p FROM PFacturacion p"),
    @NamedQuery(name = "PFacturacion.findByIdEmpresa", query = "SELECT p FROM PFacturacion p WHERE p.pFacturacionPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "PFacturacion.findByIdSucursal", query = "SELECT p FROM PFacturacion p WHERE p.pFacturacionPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "PFacturacion.findById", query = "SELECT p FROM PFacturacion p WHERE p.pFacturacionPK.id = :id"),
    @NamedQuery(name = "PFacturacion.findByDireccionCliente", query = "SELECT p FROM PFacturacion p WHERE p.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "PFacturacion.findByRegistroFiscal", query = "SELECT p FROM PFacturacion p WHERE p.registroFiscal = :registroFiscal"),
    @NamedQuery(name = "PFacturacion.findByNit", query = "SELECT p FROM PFacturacion p WHERE p.nit = :nit"),
    @NamedQuery(name = "PFacturacion.findByIdCcfGenerar", query = "SELECT p FROM PFacturacion p WHERE p.idCcfGenerar = :idCcfGenerar"),
    @NamedQuery(name = "PFacturacion.findByExpediente", query = "SELECT p FROM PFacturacion p WHERE p.expediente = :expediente"),
    @NamedQuery(name = "PFacturacion.findByPlaca", query = "SELECT p FROM PFacturacion p WHERE p.placa = :placa"),
    @NamedQuery(name = "PFacturacion.findByEstado", query = "SELECT p FROM PFacturacion p WHERE p.estado = :estado")})
public class PFacturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PFacturacionPK pFacturacionPK;
    @NotNull
    @Column(name = "id_tipo_factura")
    private int idTipoFactura;
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Size(max = 250)
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Column(name = "id_ciudad", nullable = false)
    private int idCiudad;
    @Column(name = "id_municipio")
    private int idMunicipio;
    @Column(name = "id_depto")
    private int idDepto;
    @Size(max = 20)
    @Column(name = "registro_fiscal")
    private String registroFiscal;
    @Size(max = 16)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 20)
    @Column(name = "id_ccf_generar")
    private String idCcfGenerar;
    @Size(max = 50)
    @Column(name = "expediente")
    private String expediente;
    @NotNull
    @Column(name = "id_forma_pago")
    private int idFormaPago;
    @NotNull
    @Column(name = "id_vendedor")
    private int idVendedor;
    @NotNull
    @Column(name = "id_taller")
    private int idTaller;
    @NotNull
    @Column(name = "fecha_factura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    @Size(max = 15)
    @Column(name = "placa")
    private String placa;
    @Size(max = 25)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pFacturacion")
    private List<PFactDetail> pFactDetailList;

    public PFacturacion() {
    }

    public PFacturacion(PFacturacionPK pFacturacionPK) {
        this.pFacturacionPK = pFacturacionPK;
    }

    public PFacturacion(int idEmpresa, int idSucursal, String id) {
        this.pFacturacionPK = new PFacturacionPK(idEmpresa, idSucursal, id);
    }

    public PFacturacionPK getPFacturacionPK() {
        return pFacturacionPK;
    }

    public void setPFacturacionPK(PFacturacionPK pFacturacionPK) {
        this.pFacturacionPK = pFacturacionPK;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getRegistroFiscal() {
        return registroFiscal;
    }

    public void setRegistroFiscal(String registroFiscal) {
        this.registroFiscal = registroFiscal;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIdCcfGenerar() {
        return idCcfGenerar;
    }

    public void setIdCcfGenerar(String idCcfGenerar) {
        this.idCcfGenerar = idCcfGenerar;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdTipoFactura() {
        return idTipoFactura;
    }

    public void setIdTipoFactura(int idTipoFactura) {
        this.idTipoFactura = idTipoFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public int getIdFormaPago() {
        return idFormaPago;
    }

    public void setIdFormaPago(int idFormaPago) {
        this.idFormaPago = idFormaPago;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @XmlTransient
    public List<PFactDetail> getPFactDetailList() {
        return pFactDetailList;
    }

    public void setPFactDetailList(List<PFactDetail> pFactDetailList) {
        this.pFactDetailList = pFactDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pFacturacionPK != null ? pFacturacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PFacturacion)) {
            return false;
        }
        PFacturacion other = (PFacturacion) object;
        if ((this.pFacturacionPK == null && other.pFacturacionPK != null) || (this.pFacturacionPK != null && !this.pFacturacionPK.equals(other.pFacturacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.catalogo.service.PFacturacion[ pFacturacionPK=" + pFacturacionPK + " ]";
    }

}
