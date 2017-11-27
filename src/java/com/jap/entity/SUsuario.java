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
@Table(name = "s_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SUsuario.findAll", query = "SELECT s FROM SUsuario s"),
    @NamedQuery(name = "SUsuario.findByIdEmpresa", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "SUsuario.findByUsr", query = "SELECT s FROM SUsuario s WHERE s.sUsuarioPK.usr = :usr"),
    @NamedQuery(name = "SUsuario.findByClave", query = "SELECT s FROM SUsuario s WHERE s.clave = :clave"),
    @NamedQuery(name = "SUsuario.findByNombre", query = "SELECT s FROM SUsuario s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "SUsuario.findByEmail", query = "SELECT s FROM SUsuario s WHERE s.email = :email"),
    @NamedQuery(name = "SUsuario.findByStatus", query = "SELECT s FROM SUsuario s WHERE s.status = :status")})
public class SUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SUsuarioPK sUsuarioPK;
    @Size(max = 15)
    @Column(name = "clave")
    private String clave;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "email")
    private String email;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public SUsuario() {
    }

    public SUsuario(SUsuarioPK sUsuarioPK) {
        this.sUsuarioPK = sUsuarioPK;
    }

    public SUsuario(int idEmpresa, String usr) {
        this.sUsuarioPK = new SUsuarioPK(idEmpresa, usr);
    }

    public SUsuarioPK getSUsuarioPK() {
        return sUsuarioPK;
    }

    public void setSUsuarioPK(SUsuarioPK sUsuarioPK) {
        this.sUsuarioPK = sUsuarioPK;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (sUsuarioPK != null ? sUsuarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SUsuario)) {
            return false;
        }
        SUsuario other = (SUsuario) object;
        if ((this.sUsuarioPK == null && other.sUsuarioPK != null) || (this.sUsuarioPK != null && !this.sUsuarioPK.equals(other.sUsuarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jap.entity.SUsuario[ sUsuarioPK=" + sUsuarioPK + " ]";
    }
    
}
