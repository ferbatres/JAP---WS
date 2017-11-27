/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "s_rol_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SRolUsuario.findAll", query = "SELECT s FROM SRolUsuario s"),
    @NamedQuery(name = "SRolUsuario.findByUsr", query = "SELECT s FROM SRolUsuario s WHERE s.sRolUsuarioPK.usr = :usr"),
    @NamedQuery(name = "SRolUsuario.findByIdRol", query = "SELECT s FROM SRolUsuario s WHERE s.sRolUsuarioPK.idRol = :idRol")})
public class SRolUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SRolUsuarioPK sRolUsuarioPK;

    public SRolUsuario() {
    }

    public SRolUsuario(SRolUsuarioPK sRolUsuarioPK) {
        this.sRolUsuarioPK = sRolUsuarioPK;
    }

    public SRolUsuario(String usr, int idRol) {
        this.sRolUsuarioPK = new SRolUsuarioPK(usr, idRol);
    }

    public SRolUsuarioPK getSRolUsuarioPK() {
        return sRolUsuarioPK;
    }

    public void setSRolUsuarioPK(SRolUsuarioPK sRolUsuarioPK) {
        this.sRolUsuarioPK = sRolUsuarioPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sRolUsuarioPK != null ? sRolUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SRolUsuario)) {
            return false;
        }
        SRolUsuario other = (SRolUsuario) object;
        if ((this.sRolUsuarioPK == null && other.sRolUsuarioPK != null) || (this.sRolUsuarioPK != null && !this.sRolUsuarioPK.equals(other.sRolUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SRolUsuario[ sRolUsuarioPK=" + sRolUsuarioPK + " ]";
    }
    
}
