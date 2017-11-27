/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CFormaPago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CFormaPagoRepository extends JpaRepository<CFormaPago,Integer>{
    @Query(nativeQuery = true, value = "SELECT ifnull(max(id)+1,1)  as id  FROM jap.c_forma_pago")
    public int nextId();
    
    @Query(nativeQuery = true, value = "select   * from jap.c_forma_pago order by id asc")
    public List<CFormaPago> findByAllOrder();
}
