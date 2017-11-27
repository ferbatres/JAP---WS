/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CEmpresa;
import com.jap.repository.CEmpresaRepository;
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

@Path("cempresa")
public class CEmpresaService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CEmpresaRepository cempresarepository;

    @GET
    @Path("/scempresa/{id}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scempresa(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return scempresa(param.toString(), request);
    }

    @POST
    @Path("/scempresa")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scempresa(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CEmpresa c = new CEmpresa();
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            c.setStatus(status);
            cempresarepository.save(c);
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
    @Path("/dcempresa/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcformadepago(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dcempresa(param.toString());
    }

    @POST
    @Path("/dcempresa")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcempresa(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            cempresarepository.delete(id);
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
    @Path("/fcempresa")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempresa() {
        return fcempresaS();
    }

    @POST
    @Path("/fcempresa")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempresaS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<CEmpresa> list = cempresarepository.findByAllOrder();
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
    @Path("/fcempresabyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempresabyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fcempresabyid(param.toString());
    }

    @POST
    @Path("/fcempresabyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempresabyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            CEmpresa c = new CEmpresa();
            c = cempresarepository.findOne(id);
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
            int id  = cempresarepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CEmpresaService() {
        cempresarepository = ApplicationContextProvider.getApplicationContext().getBean(CEmpresaRepository.class);
    }

}
