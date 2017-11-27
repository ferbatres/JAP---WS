 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SOpcionRol;
import com.jap.entity.SOpcionRolPK;
import java.util.List;
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
public interface SOpcionRolRepository extends JpaRepository<SOpcionRol, SOpcionRolPK>{
     @Query(nativeQuery = true, value = "SELECT rol.id , rol.descripcion  , op.id , op.descripcion  FROM jap.s_opcion_rol oprol , jap.s_rol rol , jap.s_opcion op\n" +
"where oprol.id_rol=rol.id and oprol.id_opcion=op.id and oprol.id_empresa = op.id_empresa and op.id_empresa = ?")
    public List<Object[]> ustomFindAll(Integer id_empresa);
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from jap.s_opcion_rol where id_rol = ? and id_empresa = ?")
    public void deleteAllOpciones(Integer id_rol , Integer id_empresa);
}
