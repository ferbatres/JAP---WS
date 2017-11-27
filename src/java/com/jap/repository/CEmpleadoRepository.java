/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.CEmpleado;
import com.jap.entity.CEmpleadoPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface CEmpleadoRepository extends JpaRepository<CEmpleado, CEmpleadoPK> {

    @Query(nativeQuery = true, value = "select   ifnull(max(id)+1,1)  as id from jap.c_empleado")
    public int nextId();

    @Query(nativeQuery = true, value = "select   * from jap.c_empleado order by id asc")
    public List<CEmpleado> findByAllOrder();

    @Query(nativeQuery = true, value = "select emp.nombre, suc.id, suc.id_empresa, suc.descripcion, suc.tel1, suc.direccion1 \n"
            + "from s_usuario usu, c_empleado emp, c_almacen suc \n"
            + "where usu.id_empresa = emp.id_empresa and usu.id_empleado = emp.id \n"
            + "and emp.id_sucursal = suc.id and usu.id_empresa = suc.id_empresa \n"
            + "and usu.id_empresa = ? and emp.id = ? and usu.usr = ?")
    public List<Object[]> getEmpleadoSuc(String empresa, String empleado, String usuario);
}
