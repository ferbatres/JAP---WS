/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "s_opcion_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SOpcionPrincipal.findAll", query = "SELECT s FROM SOpcionPrincipal s"),
    @NamedQuery(name = "SOpcionPrincipal.findByIdEmpresa", query = "SELECT s FROM SOpcionPrincipal s WHERE s.sOpcionPrincipalPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "SOpcionPrincipal.findById", query = "SELECT s FROM SOpcionPrincipal s WHERE s.sOpcionPrincipalPK.id = :id"),
    @NamedQuery(name = "SOpcionPrincipal.findByDescripcion", query = "SELECT s FROM SOpcionPrincipal s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "SOpcionPrincipal.findByStatus", query = "SELECT s FROM SOpcionPrincipal s WHERE s.status = :status"),
    @NamedQuery(name = "SOpcionPrincipal.findByMenuIcon", query = "SELECT s FROM SOpcionPrincipal s WHERE s.menuIcon = :menuIcon")})
public class SOpcionPrincipal implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SOpcionPrincipalPK sOpcionPrincipalPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "status")
    private String status;
    @Size(max = 100)
    @Column(name = "menu_icon")
    private String menuIcon;

    public SOpcionPrincipal() {
    }

    public SOpcionPrincipal(SOpcionPrincipalPK sOpcionPrincipalPK) {
        this.sOpcionPrincipalPK = sOpcionPrincipalPK;
    }

    public SOpcionPrincipal(SOpcionPrincipalPK sOpcionPrincipalPK, String descripcion, String status) {
        this.sOpcionPrincipalPK = sOpcionPrincipalPK;
        this.descripcion = descripcion;
        this.status = status;
    }

    public SOpcionPrincipal(int idEmpresa, int id) {
        this.sOpcionPrincipalPK = new SOpcionPrincipalPK(idEmpresa, id);
    }

    public SOpcionPrincipalPK getSOpcionPrincipalPK() {
        return sOpcionPrincipalPK;
    }

    public void setSOpcionPrincipalPK(SOpcionPrincipalPK sOpcionPrincipalPK) {
        this.sOpcionPrincipalPK = sOpcionPrincipalPK;
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

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sOpcionPrincipalPK != null ? sOpcionPrincipalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SOpcionPrincipal)) {
            return false;
        }
        SOpcionPrincipal other = (SOpcionPrincipal) object;
        if ((this.sOpcionPrincipalPK == null && other.sOpcionPrincipalPK != null) || (this.sOpcionPrincipalPK != null && !this.sOpcionPrincipalPK.equals(other.sOpcionPrincipalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SOpcionPrincipal[ sOpcionPrincipalPK=" + sOpcionPrincipalPK + " ]";
    }
    
}
