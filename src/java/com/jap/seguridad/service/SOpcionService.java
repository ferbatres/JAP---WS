/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SOpcion;
import com.jap.entity.SOpcionPK;
import com.jap.repository.SOpcionRepository;
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
@Path("sopcion")
public class SOpcionService {
    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    SOpcionRepository sopcionrepository;

    @GET
    @Path("/ssopcion/{id}/{descripcion}/{status}/{id_opc_principal}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcion(
            @PathParam("id") String id,
            @PathParam("descripcion") String descripcion,
            @PathParam("status") String status,
            @PathParam("id_opc_principal") String id_opc_principal,
            @PathParam("id_empresa") String id_empresa,
            
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("descripcion", descripcion);
        param.put("status", status);
        param.put("id_opc_principal", id_opc_principal);
        param.put("id_empresa", id_empresa);
        return ssopcion(param.toString(), request);
    }

    @POST
    @Path("/ssopcion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssopcion(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String descripcion = jsonObj.optString("descripcion");
            String status = jsonObj.optString("status");
            String id_opc_principal = jsonObj.optString("id_opc_principal");
            String id_empresa = jsonObj.optString("id_empresa");
            SOpcion c = new SOpcion();
            SOpcionPK pk = new SOpcionPK();
            pk.setId(Integer.parseInt(id));
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            pk.setIdOpcPrincipal(Integer.parseInt(id_opc_principal));
            c.setDescripcion(descripcion);
            c.setSOpcionPK(pk);
            c.setStatus(status);
           
            sopcionrepository.save(c);
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
    @Path("/dsopcion/{id}/{id_empresa}/{id_opc_principal}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcformadepago(@PathParam("id") int id , @PathParam("id_empresa") int id_empresa , @PathParam("id_opc_principal") int id_opc_principal) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_empresa", id_empresa);
        param.put("id_opc_principal", id_opc_principal);
        return dsopcion(param.toString());
    }

    @POST
    @Path("/dsopcion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsopcion(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_empresa = jsonObj.getInt("id_empresa");
            int id_opc_principal = jsonObj.getInt("id_opc_principal");
            SOpcionPK pk = new SOpcionPK();
            pk.setId(id);
            pk.setIdEmpresa(id_empresa);
            pk.setIdOpcPrincipal(id_opc_principal);
            sopcionrepository.delete(pk);
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
    @Path("/fsopcion/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcion(@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        return fsopcionS(param.toString());
    }

    @POST
    @Path("/fsopcion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionS(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
         List<JSONObject>  ArrJson = new ArrayList<>();
        try {
            List<SOpcion> list = sopcionrepository.findAllBiIdEmpresa(id_empresa);
            for(SOpcion o : list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getSOpcionPK().getId());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("idOpcPrincipal", o.getSOpcionPK().getIdOpcPrincipal());
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
    @Path("/fsopcionbyid/{id}/{id_opc_principal}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionbyid(@PathParam("id") int id,@PathParam("id_opc_principal") int id_opc_principal,@PathParam("id_empresa") int id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_opc_principal", id_opc_principal);
        param.put("id_empresa", id_empresa);
        return fsopcionbyid(param.toString());
    }

    @POST
    @Path("/fsopcionbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsopcionbyid(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            int id_opc_principal = jsonObj.getInt("id_opc_principal");
            int id_empresa = jsonObj.getInt("id_empresa");
            SOpcion c = new SOpcion();
            SOpcionPK pk = new SOpcionPK();
            pk.setId(id);
            pk.setIdEmpresa(id_empresa);
            pk.setIdOpcPrincipal(id_opc_principal);
            c = sopcionrepository.findOne(pk);            
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
            int id  = sopcionrepository.nextId(id_empresa);            
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    
    
    
    @GET
    @Path("/rolconopciones/{id_role}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolconopciones(@PathParam("id_role") int id_role , @PathParam("id_empresa") int id_empresa) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_role", id_role);                 
        jsonObj.put("id_empresa", id_empresa);   
        return rolconopciones(jsonObj.toString());
    }

    @POST
    @Path("/rolconopciones")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolconopciones(String jsonStr) {
       
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray jarr  =  new JSONArray();
        JSONObject objsend = new JSONObject();
        int id = obj.getInt("id_role");
        int id_empresa = obj.getInt("id_empresa");
        try {
            List<SOpcion> list = sopcionrepository.opcionesDelRol(id,id_empresa);
            List<JSONObject>  ArrJson = new ArrayList<>();
            for(SOpcion o : list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getSOpcionPK().getId());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("idOpcPrincipal", o.getSOpcionPK().getIdOpcPrincipal());
                ArrJson.add(objfor);
            }
            jarr = new JSONArray(ArrJson);          
            objsend.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            objsend.put("dato", e.getMessage());

        }
        
        return Response.status(httpstatus).entity(objsend.toString()).build();
    }
    
    
    @GET
    @Path("/rolsinopciones/{id_role}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolsinopciones(@PathParam("id_role") int id_role,@PathParam("id_empresa") int id_empresa) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_role", id_role);                 
        jsonObj.put("id_empresa", id_empresa); 
        return rolsinopciones(jsonObj.toString());
    }

    @POST
    @Path("/rolsinopciones")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response rolsinopciones(String jsonStr) {
       
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray jarr  =  new JSONArray();
        JSONObject objsend = new JSONObject();
        int id = obj.getInt("id_role");
        int id_empresa = obj.getInt("id_empresa");
        try {
            List<SOpcion> list = sopcionrepository.opcionesNoRol(id,id_empresa);
            List<JSONObject>  ArrJson = new ArrayList<>();
            for(SOpcion o : list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getSOpcionPK().getId());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("idOpcPrincipal", o.getSOpcionPK().getIdOpcPrincipal());
                ArrJson.add(objfor);
            }
            jarr = new JSONArray(ArrJson);          
            objsend.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            objsend.put("dato", e.getMessage());

        }
          return Response.status(httpstatus).entity(objsend.toString()).build();
    }

    
@GET
    @Path("/opcionesmenu/{id_role}/{id_opc_principal}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response opcionesmenu(@PathParam("id_role") String id_role , @PathParam("id_opc_principal") String id_opc_principal , @PathParam("id_empresa") String id_empresa) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("id_role", id_role);                 
        jsonObj.put("id_opc_principal", id_opc_principal);  
        jsonObj.put("id_empresa", id_empresa);  
        return opcionesmenu(jsonObj.toString());
    }

    @POST
    @Path("/opcionesmenu")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response opcionesmenu(String jsonStr) {
       
        JSONObject obj = new JSONObject(jsonStr);
        JSONArray jarr  =  new JSONArray();
        JSONObject objsend = new JSONObject();
        int id = Integer.parseInt(obj.getString("id_role"));
        int id_opc_principal = obj.getInt("id_opc_principal");
        int id_empresa = obj.getInt("id_empresa");
        try {
            List<SOpcion> list = sopcionrepository.opcionesMenuAll(id,id_opc_principal,id_empresa);
            List<JSONObject>  ArrJson = new ArrayList<>();
            for(SOpcion o : list){
                JSONObject objfor = new JSONObject();
                objfor.put("id", o.getSOpcionPK().getId());
                objfor.put("descripcion", o.getDescripcion());
                objfor.put("status", o.getStatus());
                objfor.put("idOpcPrincipal", o.getSOpcionPK().getIdOpcPrincipal());
                objfor.put("propsUpdate", o.getPropsUpdate());
                objfor.put("propsOnclick", o.getPropsOnclick());
                objfor.put("propsActionlistener", o.getPropsActionlistener());
                ArrJson.add(objfor);
            }
            jarr = new JSONArray(ArrJson);
            objsend.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            objsend.put("dato", e.getMessage());

        }
          return Response.status(httpstatus).entity(objsend.toString()).build();
    }
    
    
    public SOpcionService() {
        sopcionrepository = ApplicationContextProvider.getApplicationContext().getBean(SOpcionRepository.class);
    }

}
