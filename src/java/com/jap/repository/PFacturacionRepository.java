/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.PFacturacion;
import com.jap.entity.PFacturacionPK;
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
public interface PFacturacionRepository extends JpaRepository<PFacturacion, PFacturacionPK>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value = "select * from jap.p_facturacion where id = ? and id_empresa = ? and id_sucursal = ?")
    public List<PFacturacion> findFacturacionById(Integer id, Integer id_empresa, Integer id_sucursal);

    @Query(nativeQuery = true, value = "select * from jap.p_facturacion where id_empresa = ?")
    public List<PFacturacion> findAllFactura(Integer id_empresa);

    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.p_facturacion where id_empresa = ?")
    public int nextId(Integer id_empresa);

}
