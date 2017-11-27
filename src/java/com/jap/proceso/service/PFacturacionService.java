/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.proceso.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.PFacturacion;
import com.jap.entity.PFacturacionPK;
import com.jap.entity.VFacturas;
import com.jap.repository.PFacturacionRepository;
import com.jap.repository.VFacturasRepository;
import com.jap.spec.VFacturasSpecs;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author fernando_batres
 */
@Path("pfacturacion")
public class PFacturacionService {

    @Context
    HttpServletRequest request;

    Response.Status httpstatus;
    PFacturacionRepository pfacturacionrepository;
    VFacturasRepository vfacturasrepository;

    @GET
    @Path("/spfacturacion/{idEmpresa}/{idSucursal}/{id}/{idTipoFactura}/{idCliente}/{direccionCliente}"
            + "/{idCiudad}/{idMunicipio}/{idDepto}/{registroFiscal}/{nit}/{idFormaPago}"
            + "/{idVendedor}/{idCcfGenerar}/{idTaller}/{fechaFactura}/{expediente}/{placa}/{estado}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response spfacturacion(
            @PathParam("idEmpresa") String idEmpresa,
            @PathParam("idSucursal") String idSucursal,
            @PathParam("id") String id,
            @PathParam("idTipoFactura") String idTipoFactura,
            @PathParam("idCliente") String idCliente,
            @PathParam("direccionCliente") String direccionCliente,
            @PathParam("idCiudad") String idCiudad,
            @PathParam("idMunicipio") String idMunicipio,
            @PathParam("idDepto") String idDepto,
            @PathParam("registroFiscal") String registroFiscal,
            @PathParam("nit") String nit,
            @PathParam("idFormaPago") String idFormaPago,
            @PathParam("idVendedor") String idVendedor,
            @PathParam("idCcfGenerar") String idCcfGenerar,
            @PathParam("fechaFactura") String fechaFactura,
            @PathParam("idTaller") String idTaller,
            @PathParam("expediente") String expediente,
            @PathParam("placa") String placa,
            @PathParam("estado") String estado,
            @Context HttpServletRequest request) throws Exception {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        param.put("id", id);
        param.put("idTipoFactura", idTipoFactura);
        param.put("idCliente", idCliente);
        param.put("direccionCliente", direccionCliente);
        param.put("idCiudad", idCiudad);
        param.put("idMunicipio", idMunicipio);
        param.put("idDepto", idDepto);
        param.put("registroFiscal", registroFiscal);
        param.put("nit", nit);
        param.put("idFormaPago", idFormaPago);
        param.put("idVendedor", idVendedor);
        param.put("idCcfGenerar", idCcfGenerar);
        param.put("idTaller", idTaller);
        param.put("fechaFactura", fechaFactura);
        param.put("expediente", expediente);
        param.put("placa", placa);
        param.put("estado", estado);
        return spfacturacion(param.toString(), request);
    }

    @POST
    @Path("/spfacturacion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response spfacturacion(String jsonStr, @Context HttpServletRequest request) throws Exception {
        JSONObject msg = new JSONObject();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String idEmpresa = jsonObj.getString("idEmpresa");
            String idSucursal = jsonObj.getString("idSucursal");
            String id = jsonObj.getString("id");
            String idTipoFactura = jsonObj.getString("idTipoFactura");
            String idCliente = jsonObj.getString("idCliente");
            String direccionCliente = jsonObj.getString("direccionCliente");
            String idCiudad = jsonObj.getString("idCiudad");
            String idMunicipio = jsonObj.getString("idMunicipio");
            String idDepto = jsonObj.getString("idDepto");
            String registroFiscal = jsonObj.getString("registroFiscal");
            String nit = jsonObj.getString("nit");
            String idFormaPago = jsonObj.getString("idFormaPago");
            String idVendedor = jsonObj.getString("idVendedor");
            String idCcfGenerar = jsonObj.getString("idCcfGenerar");
            String idTaller = jsonObj.getString("idTaller");

            Date fechaFactura = (Date) formatter.parse(jsonObj.getString("fechaFactura"));
            String expediente = jsonObj.getString("expediente");
            String placa = jsonObj.getString("placa");
            String estado = jsonObj.getString("estado");
            PFacturacion c = new PFacturacion();
            PFacturacionPK pk = new PFacturacionPK();
            pk.setId(id);
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdSucursal(Integer.parseInt(idSucursal));
            c.setDireccionCliente(direccionCliente);
            c.setIdTipoFactura(Integer.parseInt(idTipoFactura));
            c.setIdCliente(Integer.parseInt(idCliente));
            c.setEstado(estado);
            if (!idCiudad.equals("")) {
                c.setIdCiudad(Integer.parseInt(idCiudad));
            }
            c.setIdMunicipio(Integer.parseInt(idMunicipio));
            c.setIdDepto(Integer.parseInt(idDepto));
            c.setIdFormaPago(Integer.parseInt(idFormaPago));
            if (!idVendedor.equals("")) {
                c.setIdVendedor(Integer.parseInt(idVendedor));
            }
            if (!idTaller.equals("")) {
                c.setIdTaller(Integer.parseInt(idTaller));
            }
            c.setFechaFactura(fechaFactura);
            c.setPFacturacionPK(pk);
            pfacturacionrepository.save(c);
            msg.put("dato", "Facturacion Creada con Exito");
            httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
            httpstatus = Response.Status.BAD_REQUEST;
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/dpfacturacion/{idEmpresa}/{idSucursal}/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dpfacturacion(@PathParam("idEmpresa") String idEmpresa, @PathParam("idSucursal") String idSucursal, @PathParam("id") String id) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        param.put("id", id);
        return dpfacturacion(param.toString());
    }

    @POST
    @Path("/dpfacturacion")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dpfacturacion(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("usr");
            String idSucursal = jsonObj.getString("idSucursal");
            String idEmpresa = jsonObj.getString("idEmpresa");
            PFacturacionPK pk = new PFacturacionPK();
            pk.setId(id);
            pk.setIdEmpresa(Integer.parseInt(idEmpresa));
            pk.setIdSucursal(Integer.parseInt(idSucursal));
            pfacturacionrepository.delete(pk);
            httpstatus = Response.Status.OK;
            msg.put("dato", "Factura Eliminada con Exito");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }
        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fpfacturacionbyid/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyid(@PathParam("idEmpresa") String idEmpresa, @PathParam("idSucursal") String idSucursal,
            @PathParam("id") String id, @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("idSucursal", idSucursal);
        param.put("id", id);
        return fpfacturacionbyidS(param.toString(), request);
    }

    @POST
    @Path("/fpfacturacionbyid")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyidS(String jsonStr, @Context HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        String id = jsonObj.getString("id");
        String idSucursal = jsonObj.getString("idSucursal");
        String idEmpresa = jsonObj.getString("idEmpresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
            List<PFacturacion> list = pfacturacionrepository.findFacturacionById(Integer.parseInt(id), Integer.parseInt(idEmpresa), Integer.parseInt(idSucursal));
            for (PFacturacion o : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("idEmpresa", o.getPFacturacionPK().getIdEmpresa());
                objfor.put("idSucursal", o.getPFacturacionPK().getIdSucursal());
                objfor.put("id", o.getPFacturacionPK().getId());
                objfor.put("idTipoFactura", o.getIdTipoFactura());
                objfor.put("idCliente", o.getIdCliente());
                objfor.put("direccionCliente", o.getDireccionCliente());
                objfor.put("idCiudad", o.getIdCiudad());
                objfor.put("idMunicipio", o.getIdMunicipio());
                objfor.put("idDepto", o.getIdDepto());
                objfor.put("registroFiscal", o.getRegistroFiscal());
                objfor.put("nit", o.getNit());
                objfor.put("idFormaPago", o.getIdFormaPago());
                objfor.put("idVendedor", o.getIdVendedor());
                objfor.put("idCcfGenerar", o.getIdCcfGenerar());
                objfor.put("idTaller", o.getIdTaller());
                objfor.put("expediente", o.getExpediente());
                objfor.put("placa", o.getPlaca());
                objfor.put("estado", o.getEstado());
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
    @Path("/fpfacturacionbyEmpresa/{idEmpresa}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyEmpresa(@PathParam("idEmpresa") String idEmpresa, @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        return fpfacturacionbyEmpresaS(param.toString(), request);
    }

    @POST
    @Path("/fpfacturacionbyEmpresa")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyEmpresaS(String jsonStr, @Context HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        String idEmpresa = jsonObj.getString("idEmpresa");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
            List<PFacturacion> list = pfacturacionrepository.findAllFactura(Integer.parseInt(idEmpresa));
            for (PFacturacion o : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("idEmpresa", o.getPFacturacionPK().getIdEmpresa());
                objfor.put("idSucursal", o.getPFacturacionPK().getIdSucursal());
                objfor.put("id", o.getPFacturacionPK().getId());
                objfor.put("idTipoFactura", o.getIdTipoFactura());
                objfor.put("idCliente", o.getIdCliente());
                objfor.put("direccionCliente", o.getDireccionCliente());
                objfor.put("idCiudad", o.getIdCiudad());
                objfor.put("idMunicipio", o.getIdMunicipio());
                objfor.put("idDepto", o.getIdDepto());
                objfor.put("registroFiscal", o.getRegistroFiscal());
                objfor.put("nit", o.getNit());
                objfor.put("idFormaPago", o.getIdFormaPago());
                objfor.put("idVendedor", o.getIdVendedor());
                objfor.put("idCcfGenerar", o.getIdCcfGenerar());
                objfor.put("idTaller", o.getIdTaller());
                objfor.put("expediente", o.getExpediente());
                objfor.put("placa", o.getPlaca());
                objfor.put("estado", o.getEstado());
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
    @Path("/fpfacturacionbyFilters/{idEmpresa}/{correlativo}"
            + "/{tipoDocumento}/{formaPago}/{fechaDesde}"
            + "/{fechaHasta}/{cliente}/{sucursal}/{vendedor}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyFilters(@PathParam("idEmpresa") String idEmpresa,
            @PathParam("correlativo") String correlativo,
            @PathParam("tipoDocumento") String tipoDocumento,
            @PathParam("formaPago") String formaPago,
            @PathParam("fechaDesde") String fechaDesde,
            @PathParam("fechaHasta") String fechaHasta,
            @PathParam("cliente") String cliente,
            @PathParam("sucursal") String sucursal,
            @PathParam("vendedor") String vendedor,
            @Context HttpServletRequest request) throws Exception {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", idEmpresa);
        param.put("correlativo", correlativo);
        param.put("tipoDocumento", tipoDocumento);
        param.put("formaPago", formaPago);
        param.put("fechaDesde", fechaDesde);
        param.put("fechaHasta", fechaHasta);
        param.put("cliente", cliente);
        param.put("sucursal", sucursal);
        param.put("vendedor", vendedor);
        System.out.println("parametros entrada Service" + param.toString());
        return fpfacturacionbyFiltersS(param.toString(), request);
    }

    @POST
    @Path("/fpfacturacionbyFilters")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fpfacturacionbyFiltersS(String jsonStr, @Context HttpServletRequest request) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
        JSONObject jsonObj = new JSONObject(jsonStr);
        Date fechaDesde = null;
        Date fechaHasta = null;
        String idEmpresa = jsonObj.getString("idEmpresa");
        String correlativo = jsonObj.getString("correlativo");
        String tipoDocumento = jsonObj.getString("tipoDocumento");
        String formaPago = jsonObj.getString("formaPago");
        if (jsonObj.getString("fechaDesde") != null && !jsonObj.getString("fechaDesde").isEmpty()) {
            fechaDesde = (Date) formatter.parse(jsonObj.getString("fechaDesde"));
        }
        if (jsonObj.getString("fechaHasta") != null && !jsonObj.getString("fechaHasta").isEmpty()) {
            fechaHasta = (Date) formatter.parse(jsonObj.getString("fechaHasta"));
        }
        String cliente = jsonObj.getString("cliente");
        String sucursal = jsonObj.getString("sucursal");
        String vendedor = jsonObj.getString("vendedor");
        JSONArray jarr = new JSONArray();
        JSONObject obj = new JSONObject();
        List<JSONObject> ArrJson = new ArrayList<>();
        try {
//            List<VFacturasRepository> list = pfacturacionrepository.findAll(PFacturacionSpecs.isFilteredDocument(idEmpresa, correlativo, tipoDocumento, formaPago, fechaDesde, fechaHasta, cliente, sucursal, vendedor));
            List<VFacturas> list = vfacturasrepository.findAll(VFacturasSpecs.isFilteredDocument(idEmpresa, correlativo, tipoDocumento, formaPago, fechaDesde, fechaHasta, cliente, sucursal, vendedor));
            System.out.println("list.size" + list.size());
            for (VFacturas o : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("empresa", o.getEmpresa());
                objfor.put("idSucursal", o.getIdSucursal());
                objfor.put("sucursal", o.getSucursal());
                objfor.put("correlativo", o.getCorrelativo());
                objfor.put("idTipoDocumento", o.getIdTipoDocumento());
                objfor.put("tipoDocumento", o.getTipoDocumento());
                objfor.put("fecha", formatter.format(o.getFecha()));
                objfor.put("idCliente", o.getIdCliente());
                objfor.put("cliente", o.getCliente());
                objfor.put("total", o.getTotal());
                objfor.put("idFormaPago", o.getIdFormaPago());
                objfor.put("formaPago", o.getFormaPago());
                objfor.put("idVendedor", o.getIdVendedor());
                objfor.put("vendedor", o.getVendedor());
                objfor.put("estado", o.getEstado());
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
            int id = pfacturacionrepository.nextId(id_empresa);
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
    @Path("/actualizarfactura/{id_empresa}/{id_almacen}/{id_documento}/{estado}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response actualizarFactura(@PathParam("id_empresa") int id_empresa,
            @PathParam("id_almacen") int id_almacen,
            @PathParam("id_documento") int id_documento,
            @PathParam("estado") String estado) {
        JSONObject param = new JSONObject();
        param.put("id_empresa", id_empresa);
        param.put("id_almacen", id_almacen);
        param.put("id_documento", id_documento);
        param.put("estado", estado);
        return actualizarFactura(param.toString());
    }

    @POST
    @Path("/actualizarfactura")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response actualizarFactura(String jsonStr) {
        JSONObject jsonObj = new JSONObject(jsonStr);
        int id_empresa = jsonObj.getInt("id_empresa");
        int id_almacen = jsonObj.getInt("id_almacen");
        String id_documento = jsonObj.get("id_documento").toString();
        String estado = jsonObj.getString("estado");
        JSONObject obj = new JSONObject();
        try {

            PFacturacionPK pk = new PFacturacionPK();
            pk.setId(id_documento);
            pk.setIdEmpresa(id_empresa);
            pk.setIdSucursal(id_almacen);
            PFacturacion factura = pfacturacionrepository.findOne(pk);
            factura.setEstado(estado);
            pfacturacionrepository.save(factura);
            obj.put("dato", "Actualizacion Correcta");
            httpstatus = Response.Status.OK;
        } catch (Exception e) {
            e.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            obj.put("dato", e.getMessage());

        }
        return Response.status(httpstatus).entity(obj.toString()).build();
    }

//    @GET
//    @Path("/descargarinventario/{id}/{qty}")
//    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
//    public Response descargarInventario(@PathParam("id") int id,
//            @PathParam("qty") int qty) {
//        JSONObject param = new JSONObject();
//        param.put("id", id);
//        param.put("qty", qty);
//        return descargarInventario(param.toString());
//    }
//
//    @POST
//    @Path("/descargarinventario")
//    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
//    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
//    public Response descargarInventario(String jsonStr) {
//        JSONObject jsonObj = new JSONObject(jsonStr);
//        int id = jsonObj.getInt("id");
//        int qty = jsonObj.getInt("qty");
//        JSONObject obj = new JSONObject();
//        try {
//
//            PFacturacionPK pk = new PFacturacionPK();
//            pk.setId(id_documento);
//            pk.setIdEmpresa(id_empresa);
//            pk.setIdSucursal(id_almacen);
//            PFacturacion factura = pfacturacionrepository.findOne(pk);
//            factura.setEstado(estado);
//            pfacturacionrepository.save(factura);
//            obj.put("dato", "Actualizacion Correcta");
//            httpstatus = Response.Status.OK;
//        } catch (Exception e) {
//            e.printStackTrace();
//            httpstatus = Response.Status.BAD_REQUEST;
//            obj.put("dato", e.getMessage());
//
//        }
//        return Response.status(httpstatus).entity(obj.toString()).build();
//    }

    public PFacturacionService() {
        pfacturacionrepository = ApplicationContextProvider.getApplicationContext().getBean(PFacturacionRepository.class);
        vfacturasrepository = ApplicationContextProvider.getApplicationContext().getBean(VFacturasRepository.class);
    }
}
