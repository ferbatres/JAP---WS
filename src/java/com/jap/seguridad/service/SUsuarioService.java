/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.seguridad.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.SUsuario;
import com.jap.entity.SUsuarioPK;
import com.jap.repository.SUsuarioRepository;
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
@Path("susuario")
public class SUsuarioService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    SUsuarioRepository susuariorepository;

    @GET
    @Path("/ssusuario/{usr}/{clave}/{nombre}/{email}/{status}/{id_empresa}/{id_empleado}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssusuario(
            @PathParam("usr") String usr,
            @PathParam("clave") String clave,
            @PathParam("nombre") String nombre,
            @PathParam("email") String email,
            @PathParam("status") String status,
            @PathParam("id_empresa") String id_empresa,
            @PathParam("id_empleado") String id_empleado,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("clave", clave);
        param.put("nombre", nombre);
        param.put("email", email);
        param.put("status", status);
        param.put("id_empresa", id_empresa);
        param.put("id_empleado", id_empleado);
        return ssusuario(param.toString(), request);
    }

    @POST
    @Path("/ssusuario")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ssusuario(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String usr = jsonObj.getString("usr");
            String clave = jsonObj.optString("clave");
            String nombre = jsonObj.optString("nombre");
            String email = jsonObj.optString("email");
            String status = jsonObj.optString("status");
            String id_empresa = jsonObj.optString("id_empresa");
            String id_empleado = jsonObj.optString("id_empleado");
            SUsuario c = new SUsuario();
            SUsuarioPK pk = new SUsuarioPK();
            c.setClave(clave);
            c.setEmail(email);
            c.setNombre(nombre);
            c.setStatus(status);
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            pk.setUsr(usr);
            pk.setIdEmpleado(Integer.parseInt(id_empleado));
            c.setSUsuarioPK(pk);
            susuariorepository.save(c);
            msg.put("dato", "Usuario Creado con Exito");
            httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
            httpstatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/dsusuario/{usr}/{id_empresa}/{id_empleado}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsusuario(@PathParam("usr") String usr, @PathParam("id_empresa") String id_empresa, @PathParam("id_empleado") String id_empleado) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("id_empresa", id_empresa);
        param.put("id_empleado", id_empleado);
        return dsusuarioS(param.toString());
    }

    @POST
    @Path("/dsusuario")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dsusuarioS(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("usr");
            int id_empresa = jsonObj.getInt("id_empresa");
            String id_empleado = jsonObj.getString("id_empleado");
            SUsuarioPK pk = new SUsuarioPK();
            pk.setUsr(id);
            pk.setIdEmpresa((id_empresa));
            pk.setIdEmpleado(Integer.parseInt(id_empleado));
            susuariorepository.delete(pk);
            httpstatus = Response.Status.OK;
            msg.put("dato", "Usuario Eliminado con Exito");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fsusuario/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuario(@PathParam("id_empresa") String id_empresa) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        return fsusuarioS(param.toString());
    }

    @POST
    @Path("/fsusuario")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuarioS(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
            List<SUsuario> list = susuariorepository.findbyAllIdEmpresa((id_empresa));
            for (SUsuario o : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("usr", o.getSUsuarioPK().getUsr());
                objfor.put("clave", o.getClave());
                objfor.put("status", o.getStatus());
                objfor.put("email", o.getEmail());
                objfor.put("nombre", o.getNombre());
                objfor.put("id_empleado", String.valueOf(o.getSUsuarioPK().getIdEmpleado()));
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
    @Path("/fsusuariobyid/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuariobyid(@PathParam("usr") String usr, @PathParam("id_empresa") String id_empresa) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("id_empresa", id_empresa);
        return fsusuariobyidF(param.toString());
    }

    @POST
    @Path("/fsusuariobyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuariobyidF(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("usr");
            String id_empresa = jsonObj.getString("id_empresa");
            SUsuario c = new SUsuario();
            SUsuarioPK pk = new SUsuarioPK();
            pk.setUsr(id);
            pk.setIdEmpresa(Integer.parseInt(id_empresa));
            c = susuariorepository.findOne(pk);
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
    @Path("/fuserrolopcionesall/{usr}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fuserrolopcionesall(@PathParam("usr") String usr, @PathParam("id_empresa") String id_empresa) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("id_empresa", id_empresa);
        return fuserrolopcionesallS(param.toString());
    }

    @POST
    @Path("/fuserrolopcionesall")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fuserrolopcionesallS(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("usr");
            String id_empresa = jsonObj.getString("id_empresa");
            JSONArray jarr = new JSONArray();
            List<JSONObject> ArrJson = new ArrayList<>();
            List<Object[]> resultList = susuariorepository.RolesOpcionesUsuarioAll(id, Integer.parseInt(id_empresa));
            for (Object[] objy : resultList) {

                JSONObject objfor = new JSONObject();
                objfor.put("descripcionmenuprincipal", objy[0].toString());
                objfor.put("idmenu", objy[1].toString());
                objfor.put("id_rol", objy[2].toString());
                objfor.put("menu_icon", objy[3].toString());

                ArrJson.add(objfor);
            }
            httpstatus = Response.Status.OK;
            jarr = new JSONArray(ArrJson);
            msg.put("dato", jarr);

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fsusuariologin/{usr}/{clave}/{id_empresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuariologin(@PathParam("usr") String usr, @PathParam("clave") String clave, @PathParam("id_empresa") String id_empresa) {
        JSONObject param = new JSONObject();
        param.put("usr", usr);
        param.put("clave", clave);
        param.put("id_empresa", id_empresa);
        return fsusuariologin(param.toString());
    }

    @POST
    @Path("/fsusuariologin")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fsusuariologin(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONObject objfor = new JSONObject();
            String usr = jsonObj.getString("usr");
            String calve = jsonObj.getString("clave");
            int id_empresa = jsonObj.getInt("id_empresa");
            SUsuario c = new SUsuario();
            c = susuariorepository.loging(usr, calve, id_empresa);
            httpstatus = Response.Status.OK;
            if (c != null) {
                objfor.put("usr", c.getSUsuarioPK().getUsr());
                objfor.put("clave", c.getClave());
                objfor.put("status", c.getStatus());
                objfor.put("email", c.getEmail());
                objfor.put("nombre", c.getNombre());
                objfor.put("empresa", c.getSUsuarioPK().getIdEmpresa());
                objfor.put("empleado", c.getSUsuarioPK().getIdEmpleado());
                msg.put("dato", (objfor));
            } else {
                objfor.put("usr", "N/A");
                objfor.put("clave", "N/A");
                objfor.put("status", "N/A");
                objfor.put("email", "N/A");
                objfor.put("nombre", "N/A");
                msg.put("dato", (objfor));
            }

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    public SUsuarioService() {
        susuariorepository = ApplicationContextProvider.getApplicationContext().getBean(SUsuarioRepository.class);
    }
}
