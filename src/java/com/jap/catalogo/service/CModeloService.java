/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CModelo;
import com.jap.entity.CModeloDTO;
import com.jap.entity.CModeloPK;
import com.jap.repository.CMarcaRepository;
import com.jap.repository.CModeloRepository;
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
@Path("cmodelo")
public class CModeloService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CModeloRepository cmodelorepository;
 

    @GET
    @Path("/scmodelo/{id}/{id_marca}/{descripcion}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(
            @PathParam("id") String id,
            @PathParam("id_marca") String id_marca,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_marca", id_marca);
        param.put("descripcion", descripcion);
        param.put("status", status);
        return scmodelo(param.toString(), request);
    }

    @POST
    @Path("/scmodelo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String id_marca = jsonObj.getString("id_marca");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            CModelo c = new CModelo();
            CModeloPK pk =  new CModeloPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdMarca(Integer.parseInt(id_marca));
            c.setDescripcion(descripcion);
            c.setStatus(status);
            c.setCModeloPK(pk);
            c.setStatus(status);
            cmodelorepository.save(c);
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
    @Path("/dcmodelo/{id}/{idMarca}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcmodelo(@PathParam("id") int id , @PathParam("idMarca") int idMarca) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idMarca", idMarca);
        return dcmodelo(param.toString());
    }

    @POST
    @Path("/dcmodelo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcmodelo(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            CModeloPK   pk =  new CModeloPK();
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int idMarca = jsonObj.getInt("idMarca");
            pk.setId(id);
            pk.setIdMarca(idMarca);
            cmodelorepository.delete(pk);
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
    @Path("/fcmodelo")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodelo() {
        return fcmodeloS();
    }

    @POST
    @Path("/fcmodelo")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodeloS() {
        JSONArray jarr = new JSONArray();
        List<JSONObject>  ArrJson = new ArrayList<JSONObject>();
        JSONObject obj = new JSONObject();
       
        try {

            List<CModelo> list = cmodelorepository.findByAllOrder();
            for (CModelo list1 : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getCModeloPK().getId());
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("idmarca", list1.getCMarca().getId());
                objfor.put("descripcionmarca", list1.getCMarca().getDescripcion());
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
    @Path("/fcmodelobyid/{id}/{idMarca}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodelobyid(@PathParam("id") int id , @PathParam("idMarca") int idMarca) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idMarca", idMarca);
        return fcmodelobyid(param.toString());
    }

    @POST
    @Path("/fcmodelobyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodelobyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            CModeloPK  pk  =   new CModeloPK();
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int idMarca = jsonObj.getInt("idMarca");
            pk.setId(id);
            pk.setIdMarca(idMarca);
            CModelo c = new CModelo();
            c = cmodelorepository.findOne(pk);
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
    @Path("/fcmodelobyidmarca/{idMarca}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodelobyidmarca(@PathParam("idMarca") int idMarca) {
        JSONObject param = new JSONObject();
        param.put("idMarca", idMarca);
        return fcmodelobyidmarca(param.toString());
    }

    @POST
    @Path("/fcmodelobyidmarca")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcmodelobyidmarca(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONArray jarr = new JSONArray();
            List<JSONObject>  ArrJson = new ArrayList<>();
            JSONObject jsonObj = new JSONObject(jsonStr);           
            int idMarca = jsonObj.getInt("idMarca");
            List<CModelo> c = new ArrayList<>();
            c = cmodelorepository.findAllbyIdMarca(idMarca);
            for (CModelo list1 : c) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getCModeloPK().getId());
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("status", list1.getStatus());
                ArrJson.add(objfor);
            }
            jarr = new JSONArray(ArrJson);           
            httpstatus = Response.Status.OK;
            msg.put("dato",jarr);

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
            int id  = cmodelorepository.nextId();            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CModeloService() {
        cmodelorepository = ApplicationContextProvider.getApplicationContext().getBean(CModeloRepository.class);
        
    }
}
