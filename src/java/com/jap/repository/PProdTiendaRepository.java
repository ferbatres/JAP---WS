/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.PProdTienda;
import com.jap.entity.PProdTiendaPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando_batres
 */

@Repository
public interface PProdTiendaRepository extends JpaRepository<PProdTienda, PProdTiendaPK> {
    
    @Query(nativeQuery = true, value = "select * from jap.c_producto cp, jap.p_prod_tienda pp\n" +
"where cp.id = pp.id_prod and pp.id_empresa = ? and id_almacen = ? order by id asc")
    public List<PProdTienda> findAllProductByAlmacenEmpresa(int idEmpresa, int idAlmacen);
    
    @Query(nativeQuery = true, value = "select * from jap.c_producto cp, jap.p_prod_tienda pp\n" +
"where cp.id = pp.id_prod and cp.id = ? and pp.id_empresa = ? and id_almacen = ?")
    public PProdTienda findProductoByIdAndAlmacenEmpresa(int id, int idEmpresa, int idAlmacen);
 }
