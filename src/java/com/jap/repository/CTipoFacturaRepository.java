/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CTipoFactura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando_batres
 */
@Repository
public interface CTipoFacturaRepository extends JpaRepository<CTipoFactura, Integer>{
    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_tipo_factura")
    public int nextId();
    
     @Query(nativeQuery = true, value = "select   * from jap.c_tipo_factura order by id asc")
    public List<CTipoFactura> findByAllOrder();
}
