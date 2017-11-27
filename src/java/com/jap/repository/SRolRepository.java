/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SRol;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface SRolRepository extends JpaRepository<SRol, Integer>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.s_rol")
    public int nextId();
    
    @Query(nativeQuery = true, value = "select  role.* FROM jap.s_usuario user , jap.s_rol role  , jap.s_rol_usuario usrol where  user.usr=usrol.usr and role.id=usrol.id_rol and user.id_empresa=usrol.id_empresa and  user.usr = ? and user.id_empresa = ? ")
    public List<SRol> roleDeUsuario(String usr , Integer id_empresa);
    
    
    @Query(nativeQuery = true, value = "select  *  from jap.s_rol where id not in (select  role.id FROM jap.s_usuario user , jap.s_rol role  , jap.s_rol_usuario usrol where  user.usr=usrol.usr and role.id=usrol.id_rol and user.id_empresa=usrol.id_empresa and  user.usr = ? and user.id_empresa = ? )")
    public  List<SRol> rolesNoUsuario(String usr , Integer id_empresa);
    
    
}
