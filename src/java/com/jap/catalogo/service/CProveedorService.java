/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CMarca;
import com.jap.entity.CProveedor;
import com.jap.repository.CProveedorRepository;
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
@Path("cproveedor")
public class CProveedorService {
    
    @Context
    HttpServletRequest request;
    
    Response.Status httpstatus;
    CProveedorRepository cproveedorrepository;
    
    @GET
    @Path("/scproveedor/{id}/{descripcion}/{tel1}/{tel2}/{direccion}/{prov_moto}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scproveedor(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("tel1") String tel1,
            @PathParam("tel2") String tel2,
            @PathParam("direccion") String direccion,
            @PathParam("prov_moto") String prov_moto,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("tel1", tel1);
        param.put("tel2", tel2);
        param.put("direccion", direccion);
        param.put("prov_moto", prov_moto);
        param.put("status", status);
        return scproveedor(param.toString(), request);
    }
    
    @POST
    @Path("/scproveedor")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scproveedor(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String tel1 = jsonObj.optString("tel1");
            String tel2 = jsonObj.optString("tel2");
            String direccion = jsonObj.optString("direccion");
            String prov_moto = jsonObj.optString("prov_moto");            
            String status = jsonObj.optString("status");
            CProveedor c = new CProveedor();
            c.setDescripcion(descripcion);
            c.setTel1(tel1);
            c.setTel2(tel2);
            c.setDireccion(direccion);
            c.setProvMoto(prov_moto);
            c.setId(Integer.parseInt(id));
            c.setStatus(status);
            cproveedorrepository.save(c);
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
    @Path("/dcproveedor/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcproveedor(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dcproveedor(param.toString());
    }
    
    @POST
    @Path("/dcproveedor")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcproveedor(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            cproveedorrepository.delete(id);
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
    @Path("/fcproveedor")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproveedor() {
        return fcproveedorS();
    }
    
    @POST
    @Path("/fcproveedor")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproveedorS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<CProveedor> list = cproveedorrepository.findByAllOrder();
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
    @Path("/fcproveedorbyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproveedorbyid(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return fcproveedorbyid(param.toString());
    }
    
    @POST
    @Path("/fcproveedorbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproveedorbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            CProveedor c = new CProveedor();
            c = cproveedorrepository.findOne(id);
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
            int id  = cproveedorrepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }
    
    public CProveedorService() {
        cproveedorrepository = ApplicationContextProvider.getApplicationContext().getBean(CProveedorRepository.class);
    }
}
