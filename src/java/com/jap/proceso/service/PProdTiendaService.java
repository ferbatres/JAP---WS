/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.proceso.service;

import com.jap.app.RESTConstants;
import com.jap.entity.PProdTienda;
import com.jap.repository.PProdTiendaRepository;
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
import org.json.JSONObject;

/**
 *
 * @author fernando_batres
 */
@Path("pprodtienda")
public class PProdTiendaService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    PProdTiendaRepository pProdTiendaRepository;

    @GET
    @Path("/fpprodtienda/{id_empresa}/{id_almacen}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpprodtienda(@PathParam("id_empresa") int id_empresa,
            @PathParam("id_almacen") int id_almacen) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        param.put("id_almacen", id_almacen);
        return fpprodtienda(param.toString());
    }

    @POST
    @Path("/fpprodtienda")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpprodtienda(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
        int id_almacen = jsonObj.getInt("id_almacen");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
            List<PProdTienda> list = pProdTiendaRepository.findAllProductByAlmacenEmpresa(id_empresa, id_almacen);
            for (PProdTienda o : list) {
                JSONObject objfor = new JSONObject();
//                objfor.put("id", o.getCAlmacenPK().getId());
//                objfor.put("id_empresa", o.getCAlmacenPK().getIdEmpresa());
//                objfor.put("descripcion", o.getDescripcion());
//                objfor.put("status", o.getStatus());
//                objfor.put("direccion1", o.getDireccion1());
//                objfor.put("direccion2", o.getDireccion2());
//                objfor.put("tel1", o.getTel1());
//                objfor.put("tel2", o.getTel2());
//                objfor.put("transferible", o.getTransferible());
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
}
