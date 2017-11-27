/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.PParametros;
import com.jap.repository.PParametrosRepository;
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
@Path("pparamtros")
public class PParametrosService {
    
    @Context
    HttpServletRequest request;
    Response.Status httpstatus;
    PParametrosRepository pparametrospository;
    
    @GET
    @Path("/spparametros/{id}/{descripcion}/{status}/{valor}/{tipoparam}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response spparametros(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @PathParam("valor") String valor,
            @PathParam("tipoparam") String tipoparam,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        param.put("valor", valor);
        param.put("tipoparam", tipoparam);
        return spparametros(param.toString(), request);
    }

    @POST
    @Path("/spparametros")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response spparametros(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            String tipoparam = jsonObj.optString("tipoparam");
            String valor = jsonObj.optString("valor");
            PParametros c = new PParametros();
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            c.setTipoParam(tipoparam);
            c.setValor(valor);
            c.setStatus(status);
            pparametrospository.save(c);
            msg.put("dato", "Datos Guardados con Exito");
             httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
             httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/dpparametros/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dpparametros(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dpparametros(param.toString());
    }

    @POST
    @Path("/dpparametros")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dpparametros(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            pparametrospository.delete(id);
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
    @Path("/fpparamteros")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpparamteros() {
        return fpparamterosS();
    }

    @POST
    @Path("/fpparamteros")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpparamterosS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<PParametros> list = pparametrospository.findAll();
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
    @Path("/fpparamterosbyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpparamterosbyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fpparamterosbyid(param.toString());
    }

    @POST
    @Path("/fpparamterosbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpparamterosbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            PParametros c = new PParametros();
            c = pparametrospository.findOne(id);
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
            int id  = pparametrospository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public PParametrosService() {
        pparametrospository = ApplicationContextProvider.getApplicationContext().getBean(PParametrosRepository.class);
    }

    
}
