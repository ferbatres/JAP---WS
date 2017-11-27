/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SRol;
import com.jap.repository.SRolRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author irvin_monterroza
 */
@Path("srol")
public class SRolService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    SRolRepository srolrepository;

    @GET
    @Path("/ssrol/{id}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssrol(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return ssrol(param.toString(), request);
    }

    @POST
    @Path("/ssrol")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssrol(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            SRol c = new SRol();
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            c.setStatus(status);
            srolrepository.save(c);
            msg.put("dato", "Datos Guardados con Exito");
             httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
             httpstatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/dsrol/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcformadepago(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dsrol(param.toString());
    }

    @POST
    @Path("/dsrol")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsrol(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            srolrepository.delete(id);
            httpstatus = Response.Status.OK;
            msg.put("dato", "Datos Eliminados con Exito");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fsrol")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsrol() {
        return fsrolS();
    }

    @POST
    @Path("/fsrol")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsrolS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<SRol> list = srolrepository.findAll();
            jarr = new JSONArray(list);
            obj.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    @GET
    @Path("/fsrolbyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsrolbyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fsrolbyid(param.toString());
    }

    @POST
    @Path("/fsrolbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsrolbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            SRol c = new SRol();
            c = srolrepository.findOne(id);
            httpstatus = Response.Status.OK;
            msg.put("dato", new JSONObject(c));

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }
    
    @GET
    @Path("/nextid")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response nextid() {
        return nextidS();
    }

    @POST
    @Path("/nextid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response nextidS() {
       
        JSONObject obj = new JSONObject();
        try {
            int id  = srolrepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }
    
    
    
    @GET
    @Path("/rolesusuariosy/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolesusuariosy(@PathParam("usr") String usr,@PathParam("id_empresa") String id_empresa) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("usr", usr);                 
        jsonObj.put("id_empresa", id_empresa); 
        return rolesusuariosyY(jsonObj.toString());
    }

    @POST
    @Path("/rolesusuariosy")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolesusuariosyY(String jsonStr) {
       
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray jarr  =  new JSONArray();
        JSONObject objsend = new JSONObject();
        String usr = obj.getString("usr");
        String id_empresa = obj.getString("id_empresa");
        try {
            List<SRol> list = srolrepository.roleDeUsuario(usr,Integer.parseInt(id_empresa));
            jarr = new JSONArray(list);          
            objsend.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            objsend.put("dato", e.getMessage());

        }
        
        return Response.status(httpstatus).entity(objsend.toString()).build();
    }
    
    
    @GET
    @Path("/rolesusuariosn/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolesusuariosn(@PathParam("usr") String  usr , @PathParam("id_empresa") String  id_empresa) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("usr", usr);                 
        jsonObj.put("id_empresa", id_empresa);
        return rolesusuariosnN(jsonObj.toString());
    }

    @POST
    @Path("/rolesusuariosn")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolesusuariosnN(String jsonStr) {
       
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray jarr  =  new JSONArray();
        JSONObject objsend = new JSONObject();
        String usr = obj.getString("usr");
        String id_empresa = obj.getString("id_empresa");
        try {
            List<SRol> list = srolrepository.rolesNoUsuario(usr,Integer.parseInt(id_empresa));
            jarr = new JSONArray(list);          
            objsend.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            objsend.put("dato", e.getMessage());

        }
          return Response.status(httpstatus).entity(objsend.toString()).build();
    }

    
    
    
    
    

    public SRolService() {
        srolrepository = ApplicationContextProvider.getApplicationContext().getBean(SRolRepository.class);
    }
}
