/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SOpcionPrincipal;
import com.jap.entity.SOpcionPrincipalPK;
import com.jap.repository.SOpcionPrincipalRepository;
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
@Path("sopcionprincipal")
public class SOpcionPrincipalService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    SOpcionPrincipalRepository sopcionprincipalrepository;

    @GET
    @Path("/ssopcionprincipal/{id}/{descripcion}/{status}/{menu_icon}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionprincipal(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @PathParam("menu_icon") String menu_icon,
             @PathParam("id_empresa") String id_empresa,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        param.put("menu_icon", menu_icon);
        param.put("id_empresa", id_empresa);
        return ssopcionprincipal(param.toString(), request);
    }

    @POST
    @Path("/ssopcionprincipal")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcionprincipal(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            String menu_icon = jsonObj.optString("menu_icon");
            String id_empresa = jsonObj.optString("id_empresa");
            SOpcionPrincipal c = new SOpcionPrincipal();
            SOpcionPrincipalPK pk =  new SOpcionPrincipalPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            c.setDescripcion(descripcion);
            c.setSOpcionPrincipalPK(pk);
            c.setStatus(status);
            c.setMenuIcon(menu_icon);
            sopcionprincipalrepository.save(c);
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
    @Path("/dsopcionprincipal/{id}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionprincipal(@PathParam("id") int id , @PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_empresa", id_empresa);
        return dsopcionprincipal(param.toString());
    }

    @POST
    @Path("/dsopcionprincipal")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcionprincipal(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_empresa = jsonObj.getInt("id_empresa");
             SOpcionPrincipalPK pk =  new SOpcionPrincipalPK();
             pk.setId(id);
             pk.setIdEmpresa(id_empresa);            
            sopcionprincipalrepository.delete(pk);
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
    @Path("/fsopcionprincipal/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionprincipal(@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        return fsopcionprincipalS(param.toString());
    }

    @POST
    @Path("/fsopcionprincipal")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionprincipalS(String jsonStr) {
        JSONArray jarr = new JSONArray();
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
         List<JSONObject>  ArrJson = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            List<SOpcionPrincipal> list = sopcionprincipalrepository.findByIdEmpresa(id_empresa);
            for(SOpcionPrincipal o : list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getSOpcionPrincipalPK().getId());
                objfor.put("id_empresa", o.getSOpcionPrincipalPK().getIdEmpresa());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("menuIcon", o.getMenuIcon());
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
    @Path("/fsopcionprincipalbyid/{id}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionprincipalbyid(@PathParam("id") int id , @PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_empresa", id_empresa);
        return fsopcionprincipalbyid(param.toString());
    }

    @POST
    @Path("/fsopcionprincipalbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionprincipalbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_empresa = jsonObj.getInt("id_empresa");
            SOpcionPrincipal c = new SOpcionPrincipal();
            SOpcionPrincipalPK pk  = new SOpcionPrincipalPK();
            pk.setId(id);
            pk.setIdEmpresa(id_empresa);
            c = sopcionprincipalrepository.findOne(pk);
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
    @Path("/nextid/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response nextid(@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();        
        param.put("id_empresa", id_empresa);
        return nextidS(param.toString());
    }

    @POST
    @Path("/nextid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response nextidS(String jsonStr) {
       JSONObject jsonObj = new JSONObject(jsonStr);
       int id_empresa = jsonObj.getInt("id_empresa");
        JSONObject obj = new JSONObject();
        try {
            int id  = sopcionprincipalrepository.nextId(id_empresa);            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    

    public SOpcionPrincipalService() {
        sopcionprincipalrepository = ApplicationContextProvider.getApplicationContext().getBean(SOpcionPrincipalRepository.class);
    }
}
