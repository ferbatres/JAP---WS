/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CProducto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CProductoRepository extends JpaRepository<CProducto, Integer> {

    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_producto")
    public int nextId();

    @Query(nativeQuery = true, value = "select * from jap.c_producto cp")
    public List<CProducto> findByAllOrder();

    @Query(nativeQuery = true, value = "select * from jap.c_producto cp, jap.p_prod_tienda pp\n"
            + "where cp.id = pp.id_prod and pp.id_empresa = ? and id_almacen = ? order by id asc")
    public List<CProducto> findAllProductByAlmacenEmpresa(int idEmpresa, int idAlmacen);

    @Query(nativeQuery = true, value = "select * from jap.c_producto cp, jap.p_prod_tienda pp\n"
            + "where cp.id = pp.id_prod and cp.id = ? and pp.id_empresa = ? and id_almacen = ?")
    public CProducto findProductoByIdAndAlmacenEmpresa(int id, int idEmpresa, int idAlmacen);

    @Query(nativeQuery = true, value = "select cp.id, cp.codigo, cp.descripcion, mc.descripcion, md.descripcion, cp.costo_compra, pp.stock, al.descripcion\n"
            + "from jap.c_producto cp,\n"
            + "	   jap.p_prod_tienda pp,\n"
            + "    jap.c_almacen al,\n"
            + "    jap.c_marca mc,\n"
            + "    jap.c_modelo md\n"
            + "where cp.id = pp.id_prod \n"
            + "	and pp.id_almacen = al.id \n"
            + "    and pp.id_empresa = al.id_empresa \n"
            + "    and pp.status = 'Y'\n"
            + "    and cp.id_marca = mc.id\n"
            + "    and cp.id_modelo = md.id\n"
            + "    order by cp.id asc, al.descripcion asc")
    public List<Object[]> getAllArticulos();

}
