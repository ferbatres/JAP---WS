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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "s_opcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SOpcion.findAll", query = "SELECT s FROM SOpcion s"),
    @NamedQuery(name = "SOpcion.findByIdEmpresa", query = "SELECT s FROM SOpcion s WHERE s.sOpcionPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "SOpcion.findByIdOpcPrincipal", query = "SELECT s FROM SOpcion s WHERE s.sOpcionPK.idOpcPrincipal = :idOpcPrincipal"),
    @NamedQuery(name = "SOpcion.findById", query = "SELECT s FROM SOpcion s WHERE s.sOpcionPK.id = :id"),
    @NamedQuery(name = "SOpcion.findByDescripcion", query = "SELECT s FROM SOpcion s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SOpcion.findByStatus", query = "SELECT s FROM SOpcion s WHERE s.status = :status"),
    @NamedQuery(name = "SOpcion.findByPropsUpdate", query = "SELECT s FROM SOpcion s WHERE s.propsUpdate = :propsUpdate"),
    @NamedQuery(name = "SOpcion.findByPropsOnclick", query = "SELECT s FROM SOpcion s WHERE s.propsOnclick = :propsOnclick"),
    @NamedQuery(name = "SOpcion.findByPropsActionlistener", query = "SELECT s FROM SOpcion s WHERE s.propsActionlistener = :propsActionlistener")})
public class SOpcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SOpcionPK sOpcionPK;
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;
    @Size(max = 500)
    @Column(name = "props_update")
    private String propsUpdate;
    @Size(max = 500)
    @Column(name = "props_onclick")
    private String propsOnclick;
    @Size(max = 500)
    @Column(name = "props_actionlistener")
    private String propsActionlistener;

    public SOpcion() {
    }

    public SOpcion(SOpcionPK sOpcionPK) {
        this.sOpcionPK = sOpcionPK;
    }

    public SOpcion(int idEmpresa, int idOpcPrincipal, int id) {
        this.sOpcionPK = new SOpcionPK(idEmpresa, idOpcPrincipal, id);
    }

    public SOpcionPK getSOpcionPK() {
        return sOpcionPK;
    }

    public void setSOpcionPK(SOpcionPK sOpcionPK) {
        this.sOpcionPK = sOpcionPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPropsUpdate() {
        return propsUpdate;
    }

    public void setPropsUpdate(String propsUpdate) {
        this.propsUpdate = propsUpdate;
    }

    public String getPropsOnclick() {
        return propsOnclick;
    }

    public void setPropsOnclick(String propsOnclick) {
        this.propsOnclick = propsOnclick;
    }

    public String getPropsActionlistener() {
        return propsActionlistener;
    }

    public void setPropsActionlistener(String propsActionlistener) {
        this.propsActionlistener = propsActionlistener;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sOpcionPK != null ? sOpcionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SOpcion)) {
            return false;
        }
        SOpcion other = (SOpcion) object;
        if ((this.sOpcionPK == null && other.sOpcionPK != null) || (this.sOpcionPK != null && !this.sOpcionPK.equals(other.sOpcionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SOpcion[ sOpcionPK=" + sOpcionPK + " ]";
    }
    
}
