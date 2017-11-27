/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.RESTConstants;
import com.jap.app.ApplicationContextProvider;
import com.jap.entity.CCiudad;
import com.jap.entity.CCliente;
import com.jap.entity.CDepto;
import com.jap.entity.CMunicipio;
import com.jap.entity.CMunicipioPK;
import com.jap.repository.CCiudadRepository;
import com.jap.repository.CClienteRepository;
import com.jap.repository.CDeptoRepository;
import com.jap.repository.CMunicipioRepository;
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
@Path("ccliente")
public class CClienteService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    CClienteRepository cclientepiorepository;
    CDeptoRepository cdeptorepository;
    CMunicipioRepository cmunicipiorepository;
    CCiudadRepository cciudadrepository;

    @GET
    @Path("/scliente/{id}/{nombre}/{direccion}/{idDepto}/{idMunicipio}/{idCiudad}/{registroFiscal}/{nit}/{giro}/{telefono1}/{telefono2}/{fax}/{limiteDeCredito}/{email}/{comentarios}/{percepcion}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(
            @PathParam("id") String id,
            @PathParam("nombre") String nombre,
            @PathParam("direccion") String direccion,
            @PathParam("idDepto") String idDepto,
            @PathParam("idMunicipio") String idMunicipio,
            @PathParam("idCiudad") String idCiudad,
            @PathParam("registroFiscal") String registroFiscal,
            @PathParam("nit") String nit,
            @PathParam("giro") String giro,
            @PathParam("telefono1") String telefono1,
            @PathParam("telefono2") String telefono2,
            @PathParam("fax") String fax,
            @PathParam("limiteDeCredito") String limiteDeCredito,
            @PathParam("email") String email,
            @PathParam("comentarios") String comentarios,
            @PathParam("percepcion") String percepcion,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("nombre", nombre);
        param.put("direccion", direccion);
        param.put("idDepto", idDepto);
        param.put("idMunicipio", idMunicipio);
        param.put("idCiudad", idCiudad);
        param.put("registroFiscal", registroFiscal);
        param.put("nit", nit);
        param.put("giro", giro);
        param.put("telefono1", telefono1);
        param.put("telefono2", telefono2);
        param.put("fax", fax);
        param.put("limiteDeCredito", limiteDeCredito);
        param.put("email", email);
        param.put("comentarios", comentarios);
        param.put("percepcion", percepcion);
        return scmodelo(param.toString(), request);
    }

    @POST
    @Path("/scliente")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scmodelo(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String nombre = jsonObj.getString("nombre");
            String direccion = jsonObj.getString("direccion");
            String idDepto = jsonObj.optString("idDepto");
            String idMunicipio = jsonObj.optString("idMunicipio");
            String idCiudad = jsonObj.optString("idCiudad");
            String registroFiscal = jsonObj.optString("registroFiscal");
            String nit = jsonObj.optString("nit");
            String giro = jsonObj.optString("giro");
            String telefono1 = jsonObj.optString("telefono1");
            String telefono2 = jsonObj.optString("telefono2");
            String fax = jsonObj.optString("fax");
            String limiteDeCredito = jsonObj.optString("limiteDeCredito");
            String email = jsonObj.optString("email");
            String comentarios = jsonObj.optString("comentarios");
            String percepcion = jsonObj.optString("percepcion");

            CCliente c = new CCliente();
            if (!idMunicipio.equals("")) {
                c.setIdMunicipio(Integer.parseInt(idMunicipio));
            }
            if (!idDepto.equals("")) {
                c.setIdDepto(Integer.parseInt(idDepto));
            }
            if (!idCiudad.equals("")) {
                c.setIdCiudad(Integer.parseInt(idCiudad));
            }
            c.setGiro(giro);
            c.setId(Integer.parseInt(id));
            c.setNombre(nombre);
            c.setDireccion(direccion);
            c.setRegistroFiscal(registroFiscal);
            c.setNit(nit);
            c.setTelefono1(telefono1);
            c.setTelefono2(telefono2);
            c.setFax(fax);
            c.setLimiteCredito(BigDecimal.valueOf(Long.parseLong((limiteDeCredito.equals("") ? "0" : limiteDeCredito))));
            c.setEmail(email);
            c.setComentarios(comentarios);
            c.setPercepcion(true);
            cclientepiorepository.save(c);
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
    @Path("/fccliente")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fccliente() {
        return fcclienteS();
    }

    @POST
    @Path("/fccliente")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcclienteS() {
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        CMunicipio muni = new CMunicipio();
        CCiudad ciud = new CCiudad();

        CMunicipioPK pkmuni = new CMunicipioPK();
        CDepto dep = new CDepto();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
            List<CCliente> list = cclientepiorepository.findByAllOrder();
            for (CCliente list1 : list) {
                JSONObject objfor = new JSONObject();

                if (list1.getIdDepto() != null) {
                    dep = cdeptorepository.findOne(list1.getIdDepto());
                }
                if (list1.getIdDepto() != null && list1.getIdMunicipio() != null) {
                    pkmuni.setId(list1.getIdMunicipio());
                    pkmuni.setIdDepto(list1.getIdDepto());
                    muni = cmunicipiorepository.findOne(pkmuni);
                }
                if (list1.getIdCiudad() != null) {
                    ciud = cciudadrepository.findOne(list1.getIdCiudad());
                }
                objfor.put("id", list1.getId());
                objfor.put("nombre", list1.getNombre());
                objfor.put("direccion", list1.getDireccion());
                objfor.put("idDepto", (dep.getId() != null ? dep.getId() : ""));
                objfor.put("descripcionDepto", (dep.getId() != null ? dep.getDescripcion() : ""));
                objfor.put("idMunicipio", (muni.getCMunicipioPK() != null ? muni.getCMunicipioPK().getId() : ""));
                objfor.put("descripcionMunicipio", (muni.getCMunicipioPK() != null ? muni.getDescripcion() : ""));
                objfor.put("idCiudad", (ciud.getId() != null ? ciud.getId() : ""));
                objfor.put("descripcionCiudad", (ciud.getId() != null ? ciud.getDescripcion() : ""));
                objfor.put("giro", list1.getGiro());// (list1.getGiro()!=null?list1.getGiro().getId():""));
                objfor.put("descripcionGiro", "N/A");// (list1.getGiro()!=null?list1.getGiro().getDescripcion():""));

                objfor.put("registroFiscal", list1.getRegistroFiscal());
                objfor.put("nit", list1.getNit());

                objfor.put("telefono1", list1.getTelefono1());
                objfor.put("telefono2", list1.getTelefono2());
                objfor.put("fax", list1.getFax());
                objfor.put("limiteDeCredito", list1.getLimiteCredito());
                objfor.put("email", list1.getEmail());
                objfor.put("comentarios", list1.getComentarios());
                objfor.put("percepcion", list1.getPercepcion());
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
    @Path("/dccliente/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dccliente(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dccliente(param.toString());
    }

    @POST
    @Path("/dccliente")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dccliente(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            cclientepiorepository.delete(id);
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
            int id = cclientepiorepository.nextId();
            obj.put("dato", id);
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

    public CClienteService() {
        cdeptorepository = ApplicationContextProvider.getApplicationContext().getBean(CDeptoRepository.class);
        cmunicipiorepository = ApplicationContextProvider.getApplicationContext().getBean(CMunicipioRepository.class);
        cciudadrepository = ApplicationContextProvider.getApplicationContext().getBean(CCiudadRepository.class);
        cclientepiorepository = ApplicationContextProvider.getApplicationContext().getBean(CClienteRepository.class);

    }

}
