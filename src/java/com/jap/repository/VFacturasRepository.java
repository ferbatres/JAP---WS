/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.PFacturacion;
import com.jap.entity.VFacturas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando_batres
 */
@Repository
public interface VFacturasRepository extends JpaRepository<VFacturas, String>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "select * from jap.v_facturas where empresa = ?")
    public List<VFacturas> findAllFacturas(Integer empresa);

}
