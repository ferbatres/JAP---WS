/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SOpcionPrincipal;
import com.jap.entity.SOpcionPrincipalPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface SOpcionPrincipalRepository extends JpaRepository<SOpcionPrincipal, SOpcionPrincipalPK>{
    
    
    @Query(nativeQuery = true, value = "select  * from jap.s_opcion_principal where id_empresa = ?")
    public List<SOpcionPrincipal> findByIdEmpresa(int id_empresa);
    
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.s_opcion_principal where id_empresa= ?")
    public int nextId(Integer id_empresa);
}
