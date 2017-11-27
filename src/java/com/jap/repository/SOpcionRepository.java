/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SOpcion;
import com.jap.entity.SOpcionPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface SOpcionRepository extends JpaRepository<SOpcion, SOpcionPK>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.s_opcion where id_empresa = ?")
    public int nextId(Integer id_empresa);
    
      @Query(nativeQuery = true, value = "select   * from jap.s_opcion where id_empresa = ? and status = 'A'")
    public List<SOpcion> findAllBiIdEmpresa(Integer id_empresa);
    
    
    @Query(nativeQuery = true, value = "SELECT  op.* FROM jap.s_opcion_rol oprol , jap.s_rol rol , jap.s_opcion op  where oprol.id_rol=rol.id and oprol.id_opcion=op.id and oprol.id_empresa = op.id_empresa and rol.id =? and oprol.id_empresa= ?")
    public List<SOpcion> opcionesDelRol(Integer id_rol , Integer id_empresa);
    
    
    @Query(nativeQuery = true, value = "select  *  from  jap.s_opcion where id not in (SELECT  op.id  FROM jap.s_opcion_rol oprol , jap.s_rol rol , jap.s_opcion op where oprol.id_rol=rol.id and  op.id_empresa = oprol.id_empresa  and rol.id = ? and op.id_empresa = ?)")
    public  List<SOpcion> opcionesNoRol(Integer id_rol , Integer id_empresa);
    
    @Query(nativeQuery = true, value = "select   b.*  from  jap.s_opcion_rol a , jap.s_opcion b where a.id_rol= ? and  id_opc_principal = ? and   b.id=a.id_opcion and a.id_empresa=b.id_empresa and a.id_empresa = ?")
    public  List<SOpcion> opcionesMenuAll(Integer id_rol, Integer id_opc_principal, Integer id_empresa);
    
    
   
    
    
    
}
