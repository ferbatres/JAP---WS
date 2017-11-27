/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CCiudad;
import com.jap.repository.CCiudadRepository;
import com.jap.repository.CMunicipioRepository;
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
@Path("cciudad")
public class CCiudadService {
    
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CCiudadRepository cciudadrepository;
    CMunicipioRepository cmunicipiorepository;
    
    
    
    
    @GET
    @Path("/scciudad/{id}/{id_municipio}/{id_depto}/{status}/{descripcion}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(
            @PathParam("id") String id,
            @PathParam("id_municipio") String id_municipio,
            @PathParam("id_depto") String id_depto,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_municipio", id_municipio);
        param.put("id_depto", id_depto);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return scmodelo(param.toString(), request);
    }

    @POST
    @Path("/scciudad")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String id_municipio = jsonObj.getString("id_municipio");
            String id_depto = jsonObj.getString("id_depto");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CCiudad c =  new CCiudad();
           
            c.setId(Integer.parseInt(id));
            c.setIdDepto(Integer.parseInt(id_depto));
            c.setIdMunicipio(Integer.parseInt(id_municipio));
            c.setDescripcion(descripcion);
            c.setStatus(status);
            cciudadrepository.save(c);
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
    @Path("/fcciudad")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacen() {
        return fcalmacenS();
    }
    @POST
    @Path("/fcciudad")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacenS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
         List<JSONObject>  ArrJson = new ArrayList<>();
        try {
            List<CCiudad> list = cciudadrepository.findbyAllorder();
             for (CCiudad list1 : list) {
                 JSONObject objfor = new JSONObject();
                 objfor.put("id", list1.getId());
                 objfor.put("iddepto", list1.getcMunicipio().getCMunicipioPK().getIdDepto());
                 objfor.put("idmunicipio", list1.getcMunicipio().getCMunicipioPK().getId());
                 objfor.put("descripcion", list1.getDescripcion());
                 objfor.put("descripciondepto", list1.getcMunicipio().getCDepto().getDescripcion());
                 objfor.put("descripcionmunicpio", list1.getcMunicipio().getDescripcion());
                 objfor.put("status", list1.getStatus());
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
    @Path("/fcciudadbyiddeptoidmuni/{iddepto}/{idmuni}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcciudadbyiddeptoidmuni(@PathParam("iddepto") int iddepto , @PathParam("idmuni") int idmuni) {
         JSONObject param = new JSONObject();
        param.put("iddepto", iddepto);
        param.put("idmuni", idmuni);
        return fcciudadbyiddeptoidmuni(param.toString());
    }
    @POST
    @Path("/fcciudadbyiddeptoidmuni")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcciudadbyiddeptoidmuni(String jsonStr) {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        JSONObject jsonObj = new JSONObject(jsonStr);
        int iddepto = jsonObj.getInt("iddepto");
        int idmuni = jsonObj.getInt("idmuni");
         List<JSONObject>  ArrJson = new ArrayList<>();
        try {
            List<CCiudad> list = cciudadrepository.findbyDeptoMuni(iddepto, idmuni);
             for (CCiudad list1 : list) {
                 JSONObject objfor = new JSONObject();
                 objfor.put("id", list1.getId());
                 objfor.put("iddepto", list1.getcMunicipio().getCMunicipioPK().getIdDepto());
                 objfor.put("idmunicipio", list1.getcMunicipio().getCMunicipioPK().getId());
                 objfor.put("descripcion", list1.getDescripcion());
                 objfor.put("descripciondepto", list1.getcMunicipio().getCDepto().getDescripcion());
                 objfor.put("descripcionmunicpio", list1.getcMunicipio().getDescripcion());
                 objfor.put("status", list1.getStatus());
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
    @Path("/dcciudad/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcciudad(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dcciudad(param.toString());
    }

    @POST
    @Path("/dcciudad")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcciudad(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            cciudadrepository.delete(id);
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
            int id = cciudadrepository.nextId();
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }


  
      public CCiudadService() {
        cciudadrepository = ApplicationContextProvider.getApplicationContext().getBean(CCiudadRepository.class);
        cmunicipiorepository = ApplicationContextProvider.getApplicationContext().getBean(CMunicipioRepository.class);
    }
    
}
