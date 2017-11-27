/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
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
@Table(name = "c_empleado", catalog = "JAP", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CEmpleado.findAll", query = "SELECT c FROM CEmpleado c"),
    @NamedQuery(name = "CEmpleado.findById", query = "SELECT c FROM CEmpleado c WHERE c.cEmpleadoPK.id = :id"),
    @NamedQuery(name = "CEmpleado.findByIdEmpresa", query = "SELECT c FROM CEmpleado c WHERE c.cEmpleadoPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "CEmpleado.findByIdSucursal", query = "SELECT c FROM CEmpleado c WHERE c.cEmpleadoPK.idSucursal = :idSucursal"),
    @NamedQuery(name = "CEmpleado.findByNombre", query = "SELECT c FROM CEmpleado c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CEmpleado.findByStatus", query = "SELECT c FROM CEmpleado c WHERE c.status = :status")})
public class CEmpleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CEmpleadoPK cEmpleadoPK;
    @Size(max = 200)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @JoinColumns({
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false),
        @JoinColumn(name = "id_sucursal", referencedColumnName = "id", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CAlmacen cAlmacen;

    public CEmpleado() {
    }

    public CEmpleado(CEmpleadoPK cEmpleadoPK) {
        this.cEmpleadoPK = cEmpleadoPK;
    }

    public CEmpleado(int id, int idEmpresa, int idSucursal) {
        this.cEmpleadoPK = new CEmpleadoPK(id, idEmpresa, idSucursal);
    }

    public CEmpleadoPK getCEmpleadoPK() {
        return cEmpleadoPK;
    }

    public void setCEmpleadoPK(CEmpleadoPK cEmpleadoPK) {
        this.cEmpleadoPK = cEmpleadoPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        hash += (cEmpleadoPK != null ? cEmpleadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CEmpleado)) {
            return false;
        }
        CEmpleado other = (CEmpleado) object;
        if ((this.cEmpleadoPK == null && other.cEmpleadoPK != null) || (this.cEmpleadoPK != null && !this.cEmpleadoPK.equals(other.cEmpleadoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.CEmpleado[ cEmpleadoPK=" + cEmpleadoPK + " ]";
    }
    
}
