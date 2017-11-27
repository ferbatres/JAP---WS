/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author irvin_monterroza
 */
@Entity
@Table(name = "c_modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CModelo.findAll", query = "SELECT c FROM CModelo c"),
    @NamedQuery(name = "CModelo.findByDescripcion", query = "SELECT c FROM CModelo c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CModelo.findByStatus", query = "SELECT c FROM CModelo c WHERE c.status = :status")})
public class CModelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CModeloPK cModeloPK;

    @PrimaryKeyJoinColumn(name = "id_marca", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CMarca cMarca;
    
    @Size(max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1)
    @Column(name = "status")
    private String status;

    public CModelo() {
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

    public CModeloPK getCModeloPK() {
        return cModeloPK;
    }

    public void setCModeloPK(CModeloPK cModeloPK) {
        this.cModeloPK = cModeloPK;
    }
    
    public CMarca getCMarca() {
        return cMarca;
    }

    public void setCMarca(CMarca cMarca) {
        this.cMarca = cMarca;
    }

}
