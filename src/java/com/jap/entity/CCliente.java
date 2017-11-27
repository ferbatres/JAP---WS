/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CCliente.findAll", query = "SELECT c FROM CCliente c"),
    @NamedQuery(name = "CCliente.findById", query = "SELECT c FROM CCliente c WHERE c.id = :id"),
    @NamedQuery(name = "CCliente.findByNombre", query = "SELECT c FROM CCliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CCliente.findByDireccion", query = "SELECT c FROM CCliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "CCliente.findByRegistroFiscal", query = "SELECT c FROM CCliente c WHERE c.registroFiscal = :registroFiscal"),
    @NamedQuery(name = "CCliente.findByNit", query = "SELECT c FROM CCliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "CCliente.findByTelefono1", query = "SELECT c FROM CCliente c WHERE c.telefono1 = :telefono1"),
    @NamedQuery(name = "CCliente.findByTelefono2", query = "SELECT c FROM CCliente c WHERE c.telefono2 = :telefono2"),
    @NamedQuery(name = "CCliente.findByFax", query = "SELECT c FROM CCliente c WHERE c.fax = :fax"),
    @NamedQuery(name = "CCliente.findByLimiteCredito", query = "SELECT c FROM CCliente c WHERE c.limiteCredito = :limiteCredito"),
    @NamedQuery(name = "CCliente.findByEmail", query = "SELECT c FROM CCliente c WHERE c.email = :email"),
    @NamedQuery(name = "CCliente.findByComentarios", query = "SELECT c FROM CCliente c WHERE c.comentarios = :comentarios"),
    @NamedQuery(name = "CCliente.findByPercepcion", query = "SELECT c FROM CCliente c WHERE c.percepcion = :percepcion")})
public class CCliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_depto", nullable=false)
    private Integer idDepto;
    @Column(name = "id_municipio", nullable=false)
    @Basic(optional = false)
    private Integer idMunicipio;
    @Basic(optional = false)
    @Column(name = "id_ciudad", nullable=false)
    private Integer idCiudad;
    @Column(name = "giro")
    private String giro;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 500)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 17)
    @Column(name = "registro_fiscal")
    private String registroFiscal;
    @Size(max = 17)
    @Column(name = "nit")
    private String nit;
    @Size(max = 12)
    @Column(name = "telefono1")
    private String telefono1;
    @Size(max = 12)
    @Column(name = "telefono2")
    private String telefono2;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 12)
    @Column(name = "fax")
    private String fax;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 500)
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "percepcion")
    private Boolean percepcion;
    
    
    

    public CCliente() {
    }

    public CCliente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean getPercepcion() {
        return percepcion;
    }

    public void setPercepcion(Boolean percepcion) {
        this.percepcion = percepcion;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

   


    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CCliente)) {
            return false;
        }
        CCliente other = (CCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CCliente[ id=" + id + " ]";
    }

    
   
   
    
}
