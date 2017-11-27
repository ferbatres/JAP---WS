/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CTipoDoc;
import com.jap.entity.CTipoFactura;
import com.jap.repository.CTipoDocRepository;
import com.jap.repository.CTipoFacturaRepository;
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

@Path("ctipofactura")
public class CTipoFacturaService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CTipoFacturaRepository ctipofacturarepository;

    @GET
    @Path("/sctipofactura/{id}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sctipodoc(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return sctipofactura(param.toString(), request);
    }

    @POST
    @Path("/sctipofactura")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sctipofactura(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CTipoFactura c = new CTipoFactura();
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            c.setStatus(status);
            ctipofacturarepository.save(c);
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
    @Path("/dctipofactura/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dctipofactura(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dctipofactura(param.toString());
    }

    @POST
    @Path("/dctipofactura")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dctipofactura(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            ctipofacturarepository.delete(id);
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
    @Path("/fctipofactura")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipofactura() {
        return fctipofacturaS();
    }

    @POST
    @Path("/fctipofactura")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipofacturaS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<CTipoFactura> list = ctipofacturarepository.findByAllOrder();
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
    @Path("/fctipofacturabyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipofacturabyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fctipofacturabyid(param.toString());
    }

    @POST
    @Path("/fctipodocbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipofacturabyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            CTipoFactura c = new CTipoFactura();
            c = ctipofacturarepository.findOne(id);
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
            int id  = ctipofacturarepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }
    
    
    public CTipoFacturaService() {
        ctipofacturarepository = ApplicationContextProvider.getApplicationContext().getBean(CTipoFacturaRepository.class);
    }
}
