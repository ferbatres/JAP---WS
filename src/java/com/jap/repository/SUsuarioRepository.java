/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.repository;

import com.jap.entity.SUsuario;
import com.jap.entity.SUsuarioPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author irvin_monterroza
 */
@Repository
public interface SUsuarioRepository extends JpaRepository<SUsuario, SUsuarioPK>{
    
    @Query(nativeQuery = true, value = "SELECT distinct d.descripcion , d.id , b.id_rol as rol_usuario ,  d.menu_icon FROM jap.s_opcion_rol a , jap.s_rol_usuario b , jap.s_opcion c , jap.s_opcion_principal d , jap.s_usuario e where a.id_rol=b.id_rol and c.id=a.id_opcion and d.id = c.id_opc_principal and e.usr=b.usr and a.id_empresa = b.id_empresa and e.id_empresa = a.id_empresa and c.id_empresa=a.id_empresa and e.usr= ? and a.id_empresa = ? ")
    public List<Object[]> RolesOpcionesUsuarioAll(String usr , Integer id_empresa);
    
    
    @Query(nativeQuery = true, value = "select   *  from jap.s_usuario where usr = ? and clave = ? and status = 'A' and id_empresa = ?")
    public SUsuario loging(String usr, String pass , Integer id_empresa);
    
    
    @Query(nativeQuery = true, value = "select   *  from jap.s_usuario where id_empresa = ?")
    public List<SUsuario> findbyAllIdEmpresa( Integer id_empresa);
   
    
    
    
}
