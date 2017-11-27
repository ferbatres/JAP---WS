/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SRolUsuario;
import com.jap.entity.SRolUsuarioPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface SRolUsuarioRepository extends JpaRepository<SRolUsuario, SRolUsuarioPK>{
    
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from jap.s_rol_usuario where usr = ? and id_empresa = ?")
    public void deleteAllRolesUsuario(String usr , Integer id_empresa);
    
    
    
    
}
