/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SOpcionRol;
import com.jap.entity.SOpcionRolPK;
import com.jap.repository.SOpcionRolRepository;
import java.util.ArrayList;
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
@Path("sopcionrol")
public class SOpcionRolService {
    
     HttpServletRequest request;

    Response.Status httpstatus;
    SOpcionRolRepository sopcionrolrepository;
    
    
    @GET
    @Path("/fsopcion/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcion( @PathParam("id_empresa") String id_empresa,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        return fsopcionS(param.toString(),request);
    }

    @POST
    @Path("/fsopcion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionS(String jsonStr, @Context HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        String id_empresa = jsonObj.getString("id_empresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject>  ArrJson = new ArrayList<>();
        try { 
             List<Object[]> resultList = sopcionrolrepository.ustomFindAll(Integer.parseInt(id_empresa));
            for (Object[] objy : resultList){
                 
                 JSONObject objfor = new JSONObject();
                 objfor.put("roldescripcion", objy[1].toString());
                 objfor.put("rolid", objy[0].toString());
                 objfor.put("opciondescripcion", objy[3].toString());
                 objfor.put("opcionid", objy[2].toString());
                 ArrJson.add(objfor);
                 
            }            
            jarr = new JSONArray(ArrJson);
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
    @Path("/ssopcionrol/{id_rol}/{id_opcion}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionrol(
            @PathParam("id_rol") String id_rol,
            @PathParam("id_opcion") String id_opcion,
            @PathParam("id_empresa") String id_empresa,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id_opcion", id_opcion);
        param.put("id_rol", id_rol);
        param.put("id_empresa", id_empresa);
        return ssopcionrol(param.toString(), request);
    }

    @POST
    @Path("/ssopcionrol")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionrol(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id_opcion = jsonObj.getString("id_opcion");
            String id_rol = jsonObj.optString("id_rol");
            String id_empresa = jsonObj.optString("id_empresa");
            SOpcionRolPK pk =  new SOpcionRolPK();
            pk.setIdOpcion(Integer.parseInt(id_opcion));
            pk.setIdRol(Integer.parseInt(id_rol));
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            SOpcionRol c = new SOpcionRol();
            c.setSOpcionRolPK(pk);            
            sopcionrolrepository.save(c);
            msg.put("dato", "Opciones Registradas con Exito");
             httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
             httpstatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }
    
    
    
    @GET
    @Path("/dsopcionrol/{id_opcion}/{id_rol}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionrol(@PathParam("id_rol") int id_rol,@PathParam("id_opcion") int id_opcion ,@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_rol", id_rol);
        param.put("id_opcion", id_opcion);
        param.put("id_empresa", id_empresa);
        return dsopcionrol(param.toString());
    }

    @POST
    @Path("/dsopcionrol")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionrol(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id_rol = jsonObj.getInt("id_rol");
            int id_opcion = jsonObj.getInt("id_opcion");
            int id_empresa = jsonObj.getInt("id_empresa");
            SOpcionRolPK pk =  new SOpcionRolPK();
            pk.setIdOpcion(id_opcion);
            pk.setIdRol(id_rol);
            pk.setIdEmpresa(id_empresa);
            sopcionrolrepository.delete(pk);
            httpstatus = Response.Status.OK;
            msg.put("dato", "Opciones Eliminados con Exito");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }
    
    
    
    
    @GET
    @Path("/dsopcionrolall/{id_rol}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionrolall(@PathParam("id_rol") int id_rol , @PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_rol", id_rol);
        param.put("id_empresa", id_empresa);
      
        return dsopcionrolall(param.toString());
    }

    @POST
    @Path("/dsopcionrolall")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionrolall(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id_rol = jsonObj.getInt("id_rol");
            int id_empresa = jsonObj.getInt("id_empresa");
            sopcionrolrepository.deleteAllOpciones(id_rol,id_empresa);
            httpstatus = Response.Status.OK;
            msg.put("dato", "OK");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    
    
     public SOpcionRolService() {
        sopcionrolrepository = ApplicationContextProvider.getApplicationContext().getBean(SOpcionRolRepository.class);
    }


}
