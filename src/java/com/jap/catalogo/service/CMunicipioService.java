/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CMunicipio;
import com.jap.entity.CMunicipioPK;
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
@Path("cmunicipio")
public class CMunicipioService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CMunicipioRepository cmunicipiorepository;

    @GET
    @Path("/scmunicipio/{id}/{id_depto}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmunicipio(
            @PathParam("id") String id,
            @PathParam("id_depto") String id_depto,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_depto", id_depto);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return scmunicipio(param.toString(), request);
    }

    @POST
    @Path("/scmunicipio")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmunicipio(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String id_depto = jsonObj.getString("id_depto");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CMunicipio c = new CMunicipio();
            CMunicipioPK pk = new CMunicipioPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdDepto(Integer.parseInt(id_depto));
            c.setDescripcion(descripcion);
            c.setStatus(status);
            c.setCMunicipioPK(pk);
            c.setStatus(status);
            cmunicipiorepository.save(c);
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
    @Path("/dcmunicipio/{id}/{idDepto}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcmunicipio(@PathParam("id") int id, @PathParam("idDepto") int idDepto) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idDepto", idDepto);
        return dcmunicipio(param.toString());
    }

    @POST
    @Path("/dcmunicipio")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcmunicipio(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            CMunicipioPK pk = new CMunicipioPK();
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int idDepto = jsonObj.getInt("idDepto");
            pk.setId(id);
            pk.setIdDepto(idDepto);
            cmunicipiorepository.delete(pk);
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
    @Path("/fcmunicipio")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipio() {
        return fcmunicipioS();
    }

    @POST
    @Path("/fcmunicipio")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipioS() {
        JSONArray jarr = new JSONArray();
        List<JSONObject> ArrJson = new ArrayList<>();
        JSONObject obj = new JSONObject();

        try {

            List<CMunicipio> list = cmunicipiorepository.findByAllOrder();
            for (CMunicipio list1 : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getCMunicipioPK().getId());
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("iddepto", list1.getCDepto().getId());
                objfor.put("descripciondepto", list1.getCDepto().getDescripcion());
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
    @Path("/fcmunicipiobyiddepto/{idDepto}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipiobyiddepto(@PathParam("idDepto") int idDepto) {
        JSONObject param = new JSONObject();
        param.put("idDepto", idDepto);
        return fcmunicipiobyiddepto(param.toString());
    }

    @POST
    @Path("/fcmunicipiobyiddepto")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipiobyiddepto(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            List<JSONObject> ArrJson = new ArrayList<>();
            int idDepto = jsonObj.getInt("idDepto");
            List<CMunicipio> list = cmunicipiorepository.findById(idDepto);
            for (CMunicipio list1 : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getCMunicipioPK().getId());
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("iddepto", list1.getCDepto().getId());
                objfor.put("descripciondepto", list1.getCDepto().getDescripcion());
                objfor.put("status", list1.getStatus());
                ArrJson.add(objfor);
            }
            httpstatus = Response.Status.OK;
            msg.put("dato",  new JSONArray(ArrJson));
        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fcmunicipiobyid/{id}/{idDepto}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipiobyid(@PathParam("id") int id, @PathParam("idDepto") int idDepto) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idDepto", idDepto);
        return fcmunicipiobyid(param.toString());
    }

    @POST
    @Path("/fcmunicipiobyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmunicipiobyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            CMunicipioPK pk = new CMunicipioPK();
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int idDepto = jsonObj.getInt("idDepto");
            pk.setId(id);
            pk.setIdDepto(idDepto);
            CMunicipio c = new CMunicipio();
            c = cmunicipiorepository.findOne(pk);
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
            int id = cmunicipiorepository.nextId();
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CMunicipioService() {
        cmunicipiorepository = ApplicationContextProvider.getApplicationContext().getBean(CMunicipioRepository.class);

    }
}
