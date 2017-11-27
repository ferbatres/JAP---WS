/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CFormaPago;
import com.jap.entity.CTipoVehiculo;
import com.jap.repository.CTipoVehiculoRepository;
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
@Path("ctipovehiculo")
public class CTipoVehiculoService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CTipoVehiculoRepository ctipovehiculorepository;

    @GET
    @Path("/sctipovehiculo/{id}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sctipovehiculo(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return sctipovehiculo(param.toString(), request);
    }

    @POST
    @Path("/sctipovehiculo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sctipovehiculo(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CTipoVehiculo c = new CTipoVehiculo();
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            c.setStatus(status);
            ctipovehiculorepository.save(c);
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
    @Path("/dctipovehiculo/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dctipovehiculo(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dctipovehiculo(param.toString());
    }

    @POST
    @Path("/dctipovehiculo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dctipovehiculo(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            ctipovehiculorepository.delete(id);
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
    @Path("/fctipovehiculo")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipovehiculo() {
        return fctipovehiculoS();
    }

    @POST
    @Path("/fctipovehiculo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipovehiculoS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<CTipoVehiculo> list = ctipovehiculorepository.findByAllOrder();
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
    @Path("/fctipovehiculobyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipovehiculobyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fctipovehiculobyid(param.toString());
    }

    @POST
    @Path("/fctipovehiculobyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fctipovehiculobyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            CTipoVehiculo c = new CTipoVehiculo();
            c = ctipovehiculorepository.findOne(id);
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
            int id  = ctipovehiculorepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }
    
    
    public CTipoVehiculoService() {
        ctipovehiculorepository = ApplicationContextProvider.getApplicationContext().getBean(CTipoVehiculoRepository.class);
    }

}
