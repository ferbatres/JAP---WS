/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CEmpleado;
import com.jap.entity.CEmpleadoPK;
import com.jap.repository.CEmpleadoRepository;
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
@Path("cempleado")
public class CEmpleadoService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CEmpleadoRepository cempleadorepository;

    @GET
    @Path("/scempleado/{id}/{idEmpresa}/{idSucursal}/{nombre}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scempleado(
            @PathParam("id") String id,
            @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idSucursal") String idSucursal,
            @PathParam("nombre") String nombre,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        param.put("nombre", nombre);
        param.put("status", status);
        return scempleado(param.toString(), request);
    }

    @POST
    @Path("/scempleado")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scempleado(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idSucursal = jsonObj.getString("idSucursal");
            String nombre = jsonObj.optString("nombre");
            String status = jsonObj.optString("status");
            CEmpleado c = new CEmpleado();
            CEmpleadoPK pk = new CEmpleadoPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdSucursal(Integer.parseInt(idSucursal));
            c.setNombre(nombre);
            c.setStatus(status);
            c.setCEmpleadoPK(pk);
            cempleadorepository.save(c);
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
    @Path("/dcempleado/{id}/{idEmpresa}/{idSucursal}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcempleado(@PathParam("id") String id, @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idSucursal") String idSucursal, @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        return dcempleado(param.toString(), request);
    }

    @POST
    @Path("/dcempleado")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcempleado(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            CEmpleadoPK pk = new CEmpleadoPK();
            String id = jsonObj.getString("id");
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idSucursal = jsonObj.getString("idSucursal");
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdSucursal(Integer.parseInt(idSucursal));
            cempleadorepository.delete(pk);
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
    @Path("/fcempleado")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleado(@Context HttpServletRequest request) {
        return fcempleadoS(request);
    }

    @POST
    @Path("/fcempleado")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleadoS(@Context HttpServletRequest request) {
        JSONArray jarr = new JSONArray();
        List<JSONObject> listJson = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            List<CEmpleado> list = cempleadorepository.findByAllOrder();
            for(CEmpleado listEmp : list){
                JSONObject jsonFor = new JSONObject();
                jsonFor.put("id", listEmp.getCEmpleadoPK().getId());
                jsonFor.put("IdEmpresa", listEmp.getCEmpleadoPK().getIdEmpresa());
                jsonFor.put("IdSucursal", listEmp.getCEmpleadoPK().getIdSucursal());
                jsonFor.put("nombre", listEmp.getNombre());
                jsonFor.put("status", listEmp.getStatus());
                listJson.add(jsonFor);
            }
            jarr = new JSONArray(listJson);
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
    @Path("/fcempleadosuc/{id}/{idEmpresa}/{idUsuario}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleadosuc(@PathParam("id") String id, @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idUsuario") String idUsuario) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idEmpresa", idEmpresa);
        param.put("idUsuario", idUsuario);
        return fcempleadosuc(param.toString());
    }
    @POST
    @Path("/fcempleadosuc")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleadosuc(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONObject json = new JSONObject();
            List<JSONObject> listJson = new ArrayList<>();
            JSONArray jarr = new JSONArray();            
            String id = jsonObj.getString("id");
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idUsuario = jsonObj.getString("idUsuario");
            List<Object[]> o = cempleadorepository.getEmpleadoSuc(idEmpresa,id,idUsuario);
            for(Object[] obj : o){                
                json.put("nombre", String.valueOf(obj[0]));
                json.put("idSucursal",String.valueOf(obj[1]));
                json.put("idEmpr",String.valueOf(obj[2]));
                json.put("sucursal",String.valueOf(obj[3]));
                json.put("telefono",String.valueOf(obj[4]));
                json.put("direccion",String.valueOf(obj[5]));
            }           
            msg.put("dato", json);
            httpstatus = Response.Status.OK;
        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }
        return Response.status(httpstatus).entity(msg.toString()).build();
    }
    

    @GET
    @Path("/fcempleadobyid/{id}/{idEmpresa}/{idSucursal}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleadobyid(@PathParam("id") String id, @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idSucursal") String idSucursal) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        return fcempleadobyid(param.toString());
    }

    @POST
    @Path("/fcempleadobyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcempleadobyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            List<JSONObject> jsonList = new ArrayList<>();
            String id = jsonObj.getString("id");
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idSucursal = jsonObj.getString("idSucursal");
            CEmpleado c = new CEmpleado();
            CEmpleadoPK pk = new CEmpleadoPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdSucursal(Integer.parseInt(idSucursal));
            c = cempleadorepository.findOne(pk);
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
    public Response nextid(@Context HttpServletRequest request) {
        return nextidS(request);
    }

    @POST
    @Path("/nextid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response nextidS(@Context HttpServletRequest request) {

        JSONObject obj = new JSONObject();
        try {
            int id = cempleadorepository.nextId();
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CEmpleadoService() {
        cempleadorepository = ApplicationContextProvider.getApplicationContext().getBean(CEmpleadoRepository.class);
    }
}
