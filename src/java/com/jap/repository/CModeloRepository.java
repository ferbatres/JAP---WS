/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CModelo;
import com.jap.entity.CModeloPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CModeloRepository extends JpaRepository<CModelo, CModeloPK>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_modelo")
    public int nextId();  
    
    @Query(nativeQuery = true, value = "SELECT cmo.*,cm.descripcion as descripcionmarca FROM jap.c_modelo cmo , jap.c_marca cm where cmo.id_marca=cm.id order by cmo.id asc")
    public Object[] findAll2();
    
     @Query(nativeQuery = true, value = "select   * from jap.c_modelo order by id asc")
    public List<CModelo> findByAllOrder();
    
    @Query(nativeQuery = true, value = "SELECT cmo.* FROM jap.c_modelo cmo , jap.c_marca cm where cmo.id_marca=cm.id and cmo.id_marca=? order by cmo.id asc")
    public List<CModelo> findAllbyIdMarca(int id_marca);
    
    
    
}
