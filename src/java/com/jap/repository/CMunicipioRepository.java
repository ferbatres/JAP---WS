/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CMunicipio;
import com.jap.entity.CMunicipioPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CMunicipioRepository extends JpaRepository<CMunicipio, CMunicipioPK>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_municipio")
    public int nextId();   
    
    @Query(nativeQuery = true, value = "select   *  from jap.c_municipio where id_depto=?")
    public List<CMunicipio> findById(Integer id_depto);   
    
    @Query(nativeQuery = true, value = "select   * from jap.c_municipio order by id asc")
    public List<CMunicipio> findByAllOrder();
}
