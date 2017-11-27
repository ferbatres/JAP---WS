/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.proceso.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.CEstilo;
import com.jap.entity.PFactDetail;
import com.jap.entity.PFactDetailPK;
import com.jap.repository.PFactDetailRepository;
import java.math.BigDecimal;
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
@Path("pfacturaciondetail")
public class PFactDetailService {
    
    @Context
    HttpServletRequest request;
    
    Response.Status httpstatus;
    PFactDetailRepository factdetailrepository;
    
    @GET
    @Path("/sfactdetail/{idEmpresa}/{idAlmacen}/{idFactura}/{idProd}/{qty}/{precioUnitario}/{descuento}/{status}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sfactdetail(
            @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idAlmacen") String idAlmacen,
            @PathParam("idFactura") String idFactura,
            @PathParam("idProd") String idProd,
            @PathParam("qty") String qty,
            @PathParam("precioUnitario") String precioUnitario,
            @PathParam("descuento") String descuento,
            @PathParam("status") String status,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("idAlmacen", idAlmacen);
        param.put("idFactura", idFactura);
        param.put("idProd", idProd);
        param.put("qty", qty);
        param.put("precioUnitario", precioUnitario);
        param.put("descuento", descuento);
        param.put("status", status);
        
        return sfactdetail(param.toString(), request);
    }
    
    @POST
    @Path("/sfactdetail")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response sfactdetail(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idAlmacen = jsonObj.optString("idAlmacen");
            String idFactura = jsonObj.optString("idFactura");
            String idProd = jsonObj.optString("idProd");
            String qty = jsonObj.optString("qty");
            String precioUnitario = jsonObj.optString("precioUnitario");
            String descuento = jsonObj.optString("descuento");
            String status = jsonObj.optString("status");
            PFactDetail det = new PFactDetail();
            PFactDetailPK pk = new PFactDetailPK();
            pk.setIdAlmacen(Integer.parseInt(idAlmacen));
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdFactura(idFactura);
            pk.setIdProd(Integer.parseInt(idProd));
            det.setPFactDetailPK(pk);
            if (descuento != null && !descuento.isEmpty()) {
                det.setDescuento(BigDecimal.valueOf(Double.parseDouble(descuento)));                
            } else {
                det.setDescuento(BigDecimal.ZERO);
            }            
            det.setPrecioUnitario(BigDecimal.valueOf(Double.parseDouble(precioUnitario)));
            det.setQty(BigDecimal.valueOf(Double.parseDouble(qty)));
            det.setStatus(status);
            
            factdetailrepository.save(det);
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
    @Path("/dfactdetail/{idEmpresa}/{idAlmacen}/{idFactura}/{idProd}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dfactdetail(
            @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idAlmacen") String idAlmacen,
            @PathParam("idFactura") String idFactura,
            @PathParam("idProd") String idProd) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("idAlmacen", idAlmacen);
        param.put("idFactura", idFactura);
        param.put("idProd", idProd);
        return dfactdetail(param.toString());
    }
    
    @POST
    @Path("/dfactdetail")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dfactdetail(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idAlmacen = jsonObj.optString("idAlmacen");
            String idFactura = jsonObj.optString("idFactura");
            String idProd = jsonObj.optString("idProd");
            PFactDetailPK pk = new PFactDetailPK();
            pk.setIdAlmacen(Integer.parseInt(idAlmacen));
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdFactura(idFactura);
            pk.setIdProd(Integer.parseInt(idProd));
            factdetailrepository.delete(pk);
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
    @Path("/ffactdetail")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ffactdetail() {
        return ffactdetailS();
    }
    
    @POST
    @Path("/ffactdetail")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response ffactdetailS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            List<PFactDetail> list = factdetailrepository.findAll();
            List<JSONObject> ArrJson = new ArrayList<>();
            for (PFactDetail o : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("idEmpresa", o.getPFactDetailPK().getIdEmpresa());
                objfor.put("idAlmacen", o.getPFactDetailPK().getIdAlmacen());
                objfor.put("idFactura", o.getPFactDetailPK().getIdFactura());
                objfor.put("idProd", o.getPFactDetailPK().getIdProd());
                objfor.put("qty", o.getQty());
                objfor.put("precioUnitario", o.getPrecioUnitario());
                objfor.put("descuento", o.getDescuento());
                objfor.put("status", o.getStatus());
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
    
    public PFactDetailService() {
        factdetailrepository = ApplicationContextProvider.getApplicationContext().getBean(PFactDetailRepository.class);
    }
    
}
