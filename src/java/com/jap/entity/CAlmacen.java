/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CAlmacen.findAll", query = "SELECT c FROM CAlmacen c"),
    @NamedQuery(name = "CAlmacen.findByIdEmpresa", query = "SELECT c FROM CAlmacen c WHERE c.cAlmacenPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "CAlmacen.findById", query = "SELECT c FROM CAlmacen c WHERE c.cAlmacenPK.id = :id"),
    @NamedQuery(name = "CAlmacen.findByDescripcion", query = "SELECT c FROM CAlmacen c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CAlmacen.findByTel1", query = "SELECT c FROM CAlmacen c WHERE c.tel1 = :tel1"),
    @NamedQuery(name = "CAlmacen.findByTel2", query = "SELECT c FROM CAlmacen c WHERE c.tel2 = :tel2"),
    @NamedQuery(name = "CAlmacen.findByDireccion1", query = "SELECT c FROM CAlmacen c WHERE c.direccion1 = :direccion1"),
    @NamedQuery(name = "CAlmacen.findByDireccion2", query = "SELECT c FROM CAlmacen c WHERE c.direccion2 = :direccion2"),
    @NamedQuery(name = "CAlmacen.findByTransferible", query = "SELECT c FROM CAlmacen c WHERE c.transferible = :transferible"),
    @NamedQuery(name = "CAlmacen.findByStatus", query = "SELECT c FROM CAlmacen c WHERE c.status = :status")})
public class CAlmacen implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cAlmacen")
    private List<PProdTienda> pProdTiendaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cAlmacen")
    private List<CEmpleado> cEmpleadoList;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CAlmacenPK cAlmacenPK;
    @Size(max = 125)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 10)
    @Column(name = "tel1")
    private String tel1;
    @Size(max = 10)
    @Column(name = "tel2")
    private String tel2;
    @Size(max = 500)
    @Column(name = "direccion1")
    private String direccion1;
    @Size(max = 500)
    @Column(name = "direccion2")
    private String direccion2;
    @Size(max = 1)
    @Column(name = "transferible")
    private String transferible;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public CAlmacen() {
    }

    public CAlmacen(CAlmacenPK cAlmacenPK) {
        this.cAlmacenPK = cAlmacenPK;
    }

    public CAlmacen(int idEmpresa, int id) {
        this.cAlmacenPK = new CAlmacenPK(idEmpresa, id);
    }

    public CAlmacenPK getCAlmacenPK() {
        return cAlmacenPK;
    }

    public void setCAlmacenPK(CAlmacenPK cAlmacenPK) {
        this.cAlmacenPK = cAlmacenPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getTransferible() {
        return transferible;
    }

    public void setTransferible(String transferible) {
        this.transferible = transferible;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cAlmacenPK != null ? cAlmacenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CAlmacen)) {
            return false;
        }
        CAlmacen other = (CAlmacen) object;
        if ((this.cAlmacenPK == null && other.cAlmacenPK != null) || (this.cAlmacenPK != null && !this.cAlmacenPK.equals(other.cAlmacenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CAlmacen[ cAlmacenPK=" + cAlmacenPK + " ]";
    }

    @XmlTransient
    public List<CEmpleado> getCEmpleadoList() {
        return cEmpleadoList;
    }

    public void setCEmpleadoList(List<CEmpleado> cEmpleadoList) {
        this.cEmpleadoList = cEmpleadoList;
    }

    @XmlTransient
    public List<PProdTienda> getPProdTiendaList() {
        return pProdTiendaList;
    }

    public void setPProdTiendaList(List<PProdTienda> pProdTiendaList) {
        this.pProdTiendaList = pProdTiendaList;
    }
    
}
