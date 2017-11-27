/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CCiudad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CCiudadRepository extends JpaRepository<CCiudad, Integer>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_ciudad")
    public int nextId();
    
    @Query(nativeQuery = true, value = "select   * from jap.c_ciudad where id_depto = ?  and id_municipio= ?")
    public List<CCiudad> findbyDeptoMuni(int iddept ,  int idmuni);
    
    
     @Query(nativeQuery = true, value = "select  * from jap.c_ciudad order by id asc")
    public List<CCiudad> findbyAllorder();
}
