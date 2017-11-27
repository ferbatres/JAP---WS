/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SRolUsuario;
import com.jap.entity.SRolUsuarioPK;
import com.jap.repository.SRolUsuarioRepository;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author irvin_monterroza
 */
@Path("srolusuario")
public class SRolUsuarioService {
    HttpServletRequest request;

    Response.Status httpstatus;
    SRolUsuarioRepository srolusuariorepository;
    
    
    @GET
    @Path("/ssopcionrolusuario/{id_rol}/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionrolusuario(
            @PathParam("id_rol") String id_rol,
            @PathParam("usr") String usr,
            @PathParam("id_empresa") String id_empresa,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("id_rol", id_rol);
        param.put("id_empresa", id_empresa);
        return ssopcionrolusuario(param.toString(), request);
    }

    @POST
    @Path("/ssopcionrolusuario")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionrolusuario(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String usr = jsonObj.getString("usr");
            String id_rol = jsonObj.optString("id_rol");
            String id_empresa = jsonObj.optString("id_empresa");
            SRolUsuarioPK pk =  new SRolUsuarioPK();
            pk.setUsr((usr));
            pk.setIdRol(Integer.parseInt(id_rol));
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            SRolUsuario c = new SRolUsuario();
            c.setSRolUsuarioPK(pk);
            srolusuariorepository.save(c);
            msg.put("dato", "Roles Registrados con Exito");
             httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
             httpstatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }
    
    
    @GET
    @Path("/dsrolusuarioall/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsrolusuarioall(@PathParam("usr") String usr , @PathParam("id_empresa") String id_empresa) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("id_empresa", id_empresa);
      
        return dsrolusuarioallD(param.toString());
    }

    @POST
    @Path("/dsrolusuarioall")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsrolusuarioallD(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String usr = jsonObj.getString("usr");
            String id_empresa = jsonObj.getString("id_empresa");
            srolusuariorepository.deleteAllRolesUsuario(usr,Integer.parseInt(id_empresa));
            httpstatus = Response.Status.OK;
            msg.put("dato", "OK");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }
        
    
     public SRolUsuarioService() {
        srolusuariorepository = ApplicationContextProvider.getApplicationContext().getBean(SRolUsuarioRepository.class);
    }
    
}
