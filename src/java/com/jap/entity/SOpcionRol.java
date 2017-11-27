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
@Table(name = "s_opcion_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SOpcionRol.findAll", query = "SELECT s FROM SOpcionRol s"),
    @NamedQuery(name = "SOpcionRol.findByIdRol", query = "SELECT s FROM SOpcionRol s WHERE s.sOpcionRolPK.idRol = :idRol"),
    @NamedQuery(name = "SOpcionRol.findByIdOpcion", query = "SELECT s FROM SOpcionRol s WHERE s.sOpcionRolPK.idOpcion = :idOpcion")})
public class SOpcionRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SOpcionRolPK sOpcionRolPK;

    public SOpcionRol() {
    }

    public SOpcionRol(SOpcionRolPK sOpcionRolPK) {
        this.sOpcionRolPK = sOpcionRolPK;
    }

    public SOpcionRol(int idRol, int idOpcion) {
        this.sOpcionRolPK = new SOpcionRolPK(idRol, idOpcion);
    }

    public SOpcionRolPK getSOpcionRolPK() {
        return sOpcionRolPK;
    }

    public void setSOpcionRolPK(SOpcionRolPK sOpcionRolPK) {
        this.sOpcionRolPK = sOpcionRolPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sOpcionRolPK != null ? sOpcionRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SOpcionRol)) {
            return false;
        }
        SOpcionRol other = (SOpcionRol) object;
        if ((this.sOpcionRolPK == null && other.sOpcionRolPK != null) || (this.sOpcionRolPK != null && !this.sOpcionRolPK.equals(other.sOpcionRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SOpcionRol[ sOpcionRolPK=" + sOpcionRolPK + " ]";
    }
    
}
