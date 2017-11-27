/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CTipoProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CTipoProductoRepository extends JpaRepository<CTipoProducto, Integer>{
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1)  as id  FROM jap.c_tipo_producto")
    public int nextId();
    
     @Query(nativeQuery = true, value = "select   * from jap.c_tipo_producto order by id asc")
    public List<CTipoProducto> findByAllOrder();
}
