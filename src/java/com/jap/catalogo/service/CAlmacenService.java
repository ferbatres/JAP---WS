/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CAlmacen;
import com.jap.entity.CAlmacenPK;
import com.jap.repository.CAlmacenRepository;
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

@Path("calmacen")
public class CAlmacenService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CAlmacenRepository calmacenrepository;

    @GET
    @Path("/scalmacen/{id}/{descripcion}/{tel1}/{tel2}/{direccion1}/{direccion2}/{transferible}/{status}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scalmacen(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("tel1") String tel1,
            @PathParam("tel2") String tel2,
            @PathParam("direccion1") String direccion1,
            @PathParam("direccion2") String direccion2,
            @PathParam("transferible") String transferible,
            @PathParam("status") String status,
            @PathParam("id_empresa") String id_empresa,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("tel1", tel1);
        param.put("tel2", tel2);
        param.put("direccion1", direccion1);
        param.put("direccion2", direccion2);
        param.put("transferible", transferible);
        param.put("status", status);
        param.put("id_empresa", id_empresa);
        return scalmacen(param.toString(), request);
    }

    @POST
    @Path("/scalmacen")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scalmacen(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String tel1 = jsonObj.optString("tel1");
            String tel2 = jsonObj.optString("tel2");
            String direccion1 = jsonObj.optString("direccion1");
            String direccion2 = jsonObj.optString("direccion2");
            String transferible = jsonObj.optString("transferible");
            String status = jsonObj.optString("status");
            String id_empresa = jsonObj.optString("id_empresa");
            CAlmacenPK pk =  new CAlmacenPK();
            CAlmacen c = new CAlmacen();
            c.setDescripcion(descripcion);
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            c.setCAlmacenPK(pk);
            c.setDireccion1(direccion1);
            c.setDireccion2(direccion2);
            c.setTel1(tel1);
            c.setTel2(tel2);
            c.setTransferible(transferible);
            c.setStatus(status);
            calmacenrepository.save(c);
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
    @Path("/dcalmacen/{id}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcalmacen(@PathParam("id") int id , @PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_empresa", id_empresa);
        return dcalmacen(param.toString());
    }

    @POST
    @Path("/dcalmacen")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcalmacen(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_empresa = jsonObj.getInt("id_empresa");
            CAlmacenPK pk  =  new CAlmacenPK();
            pk.setId(id);
            pk.setIdEmpresa(id_empresa);            
            calmacenrepository.delete(pk);
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
    @Path("/fcalmacen/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacen(@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        return fcalmacenS(param.toString());
    }

    @POST
    @Path("/fcalmacen")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacenS(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject>  ArrJson = new ArrayList<>();
        try {
            List<CAlmacen> list = calmacenrepository.findByAllIdEmpresa(id_empresa);
            for(CAlmacen o :list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getCAlmacenPK().getId());
                objfor.put("id_empresa", o.getCAlmacenPK().getIdEmpresa());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("direccion1", o.getDireccion1());
                objfor.put("direccion2", o.getDireccion2());
                objfor.put("tel1", o.getTel1());
                objfor.put("tel2", o.getTel2());
                objfor.put("transferible", o.getTransferible());            
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
    @Path("/fcalmacenbyid/{id}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacenbyid(@PathParam("id") int id,@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_empresa", id_empresa);
        return fcalmacenbyid(param.toString());
    }

    @POST
    @Path("/fcalmacenbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcalmacenbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_empresa = jsonObj.getInt("id_empresa");
            CAlmacenPK pk =  new CAlmacenPK();
            pk.setId(id);
            pk.setIdEmpresa(id_empresa);
            CAlmacen c = new CAlmacen();
            c = calmacenrepository.findOne(pk);
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
            int id  = calmacenrepository.nextId(id_empresa);            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CAlmacenService() {
        calmacenrepository = ApplicationContextProvider.getApplicationContext().getBean(CAlmacenRepository.class);
    }

}
