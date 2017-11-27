/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.catalogo.service;

import com.jap.app.ApplicationContextProvider;
import com.jap.app.RESTConstants;
import com.jap.entity.CProducto;
import com.jap.repository.CProductoRepository;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.springframework.data.repository.query.Param;

/**
 *
 * @author irvin_monterroza
 */
@Path("cproducto")
public class CProductoService {

    HttpServletRequest request;

    Response.Status httpstatus;
    CProductoRepository cproductorepository;

    @GET
    @Path("/scproducto/{id}/{id_marca}/{id_modelo}/"
            + "{descripcion}/{id_categoria}/"
            + "{stock}/{servicio}/{stock_minimo}/{stock_maximo}/"
            + "{suspendido}/{costo_compra}/{costo_fob}/"
            + "{costo_contable}/{ultimo_costo_s_impuesto}/"
            + "{ultimo_costo_c_impuesto}/{costo_prom_s_impuesto}/"
            + "{costo_prom_c_impuesto}/{costo_anterior_c_impuesto}/"
            + "{utilidad1}/{precio1}/{utilidad2}/{precio2}/"
            + "{utildad3}/{precio3}/{oem}/{codigo}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scproducto(
            @PathParam("id") String id,
            @PathParam("id_marca") String id_marca,
            @PathParam("id_modelo") String id_modelo,
            @PathParam("descripcion") String descripcion,
            @PathParam("id_categoria") String id_categoria,
            @PathParam("stock") String stock,
            @PathParam("servicio") String servicio,
            @PathParam("stock_minimo") String stock_minimo,
            @PathParam("stock_maximo") String stock_maximo,
            @PathParam("suspendido") String suspendido,
            @PathParam("costo_compra") String costo_compra,
            @PathParam("costo_fob") String costo_fob,
            @PathParam("costo_contable") String costo_contable,
            @PathParam("ultimo_costo_s_impuesto") String ultimo_costo_s_impuesto,
            @PathParam("ultimo_costo_c_impuesto") String ultimo_costo_c_impuesto,
            @PathParam("costo_prom_s_impuesto") String costo_prom_s_impuesto,
            @PathParam("costo_prom_c_impuesto") String costo_prom_c_impuesto,
            @PathParam("costo_anterior_c_impuesto") String costo_anterior_c_impuesto,
            @PathParam("utilidad1") String utilidad1,
            @PathParam("precio1") String precio1,
            @PathParam("utilidad2") String utilidad2,
            @PathParam("precio2") String precio2,
            @PathParam("utildad3") String utildad3,
            @PathParam("precio3") String precio3,
            @PathParam("oem") String oem,
            @PathParam("codigo") String codigo,
            @PathParam("fecha_recepcion") String fecha_recepcion,
            @Context HttpServletRequest request) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        param.put("id_marca", id_marca);
        param.put("id_modelo", id_modelo);
        param.put("descripcion", descripcion);
        param.put("id_categoria", id_categoria);
        param.put("stock", stock);
        param.put("servicio", servicio);
        param.put("stock_minimo", stock_minimo);
        param.put("stock_maximo", stock_maximo);
        param.put("suspendido", suspendido);
        param.put("costo_compra", costo_compra);
        param.put("costo_fob", costo_fob);
        param.put("costo_contable", costo_contable);
        param.put("ultimo_costo_s_impuesto", ultimo_costo_s_impuesto);
        param.put("ultimo_costo_c_impuesto", ultimo_costo_c_impuesto);
        param.put("costo_prom_s_impuesto", costo_prom_s_impuesto);
        param.put("costo_prom_c_impuesto", costo_prom_c_impuesto);
        param.put("costo_anterior_c_impuesto", costo_anterior_c_impuesto);
        param.put("utilidad1", utilidad1);
        param.put("precio1", precio1);
        param.put("utilidad2", utilidad2);
        param.put("precio2", precio2);
        param.put("utildad3", utildad3);
        param.put("precio3", precio3);
        param.put("oem", oem);
        param.put("codigo", codigo);
        param.put("fecha_recepcion", fecha_recepcion);

        return scproducto(param.toString(), request);
    }

    @POST
    @Path("/scproducto")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response scproducto(String jsonStr, @Context HttpServletRequest request) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            String id = jsonObj.getString("id");
            String id_marca = jsonObj.getString("id_marca");
            String id_modelo = jsonObj.getString("id_modelo");
            String descripcion = jsonObj.getString("descripcion");
            String id_categoria = jsonObj.getString("id_categoria");
            String stock = jsonObj.getString("stock");
            String servicio = jsonObj.getString("servicio");// verificar son valores boolean
            String stock_minimo = jsonObj.getString("stock_minimo");
            String stock_maximo = jsonObj.getString("stock_maximo");
            String suspendido = jsonObj.getString("suspendido"); // verificar son valores boolean
            String costo_compra = jsonObj.getString("costo_compra");
            String costo_fob = jsonObj.getString("costo_fob");
            String costo_contable = jsonObj.getString("costo_contable");
            String ultimo_costo_s_impuesto = jsonObj.getString("ultimo_costo_s_impuesto");
            String ultimo_costo_c_impuesto = jsonObj.getString("ultimo_costo_c_impuesto");
            String costo_prom_s_impuesto = jsonObj.getString("costo_prom_s_impuesto");
            String costo_prom_c_impuesto = jsonObj.getString("costo_prom_c_impuesto");
            String costo_anterior_c_impuesto = jsonObj.getString("costo_anterior_c_impuesto");
            String utilidad1 = jsonObj.getString("utilidad1");
            String precio1 = jsonObj.getString("precio1");
            String utilidad2 = jsonObj.getString("utilidad2");
            String precio2 = jsonObj.getString("precio2");
            String utildad3 = jsonObj.getString("utildad3");
            String precio3 = jsonObj.getString("precio3");
            String oem = jsonObj.getString("oem");
            String codigo = jsonObj.getString("codigo");
            SimpleDateFormat dfd = new SimpleDateFormat("dd/MM/yyyy");
            String fecha_recepcion = jsonObj.getString("fecha_recepcion");
            Date fecha = dfd.parse(fecha_recepcion);

            CProducto c = new CProducto();
            c.setCostoAnteriorCImpuesto(BigDecimal.valueOf(Long.parseLong((!costo_anterior_c_impuesto.equals("") ? costo_anterior_c_impuesto : "0"))));
            c.setCostoCompra(BigDecimal.valueOf(Long.parseLong((!costo_compra.equals("") ? costo_compra : "0"))));
            c.setCostoContable(BigDecimal.valueOf(Long.parseLong((!costo_contable.equals("") ? costo_contable : "0"))));
            c.setCostoFob(BigDecimal.valueOf(Long.parseLong((!costo_fob.equals("") ? costo_fob : "0"))));
            c.setCostoPromCImpuesto(BigDecimal.valueOf(Long.parseLong((!costo_prom_c_impuesto.equals("") ? costo_prom_c_impuesto : "0"))));
            c.setCostoPromSImpuesto(BigDecimal.valueOf(Long.parseLong((!costo_prom_s_impuesto.equals("") ? costo_prom_s_impuesto : "0"))));
            c.setDescripcion(descripcion);
            c.setId(Integer.parseInt(id));
            if (!id_categoria.equals("")) {
                c.setIdcategoria(Integer.parseInt(id_categoria));
            }
            if (!id_marca.equals("")) {
                c.setIdmarca(Integer.parseInt(id_marca));
            }
            if (!id_modelo.equals("")) {
                c.setIdmodelo(Integer.parseInt(id_modelo));
            }
            c.setOem((!oem.equals("") ? oem : " "));
            c.setPrecio1(BigDecimal.valueOf(Long.parseLong((!precio1.equals("") ? precio1 : "0"))));
            c.setPrecio2(BigDecimal.valueOf(Long.parseLong((!precio2.equals("") ? precio2 : "0"))));
            c.setPrecio3(BigDecimal.valueOf(Long.parseLong((!precio3.equals("") ? precio3 : "0"))));
            c.setServicio(Boolean.TRUE);
            c.setStock(Integer.parseInt((!stock.equals("") ? stock : "0")));
            c.setStockMaximo(Integer.parseInt((!stock_maximo.equals("") ? stock_maximo : "0")));
            c.setStockMinimo(Integer.parseInt((!stock_minimo.equals("") ? stock_minimo : "0")));
            c.setSuspendido(Boolean.TRUE);
            c.setUltimoCostoCImpuesto(BigDecimal.valueOf(Long.parseLong((!ultimo_costo_c_impuesto.equals("") ? ultimo_costo_c_impuesto : "0"))));
            c.setUltimoCostoSImpuesto(BigDecimal.valueOf(Long.parseLong((!ultimo_costo_s_impuesto.equals("") ? ultimo_costo_s_impuesto : "0"))));
            c.setUtildad3(BigDecimal.valueOf(Long.parseLong((!utildad3.equals("") ? utildad3 : "0"))));
            c.setUtilidad1(BigDecimal.valueOf(Long.parseLong((!utilidad1.equals("") ? utilidad1 : "0"))));
            c.setUtilidad2(BigDecimal.valueOf(Long.parseLong((!utilidad2.equals("") ? utilidad2 : "0"))));
            c.setCodigo(codigo);
            c.setFechaRecepcion(fecha);
            cproductorepository.save(c);
            msg.put("dato", "Producto Registrado con Exito");
            httpstatus = Response.Status.OK;

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            msg.put("dato", s.getMessage());
            httpstatus = Response.Status.BAD_REQUEST;
        } catch (ParseException ex) {
            Logger.getLogger(CProductoService.class.getName()).log(Level.SEVERE, null, ex);
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
            int id = cproductorepository.nextId();
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
    @Path("/dcproducto/{id}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcproducto(@PathParam("id") int id) {
        JSONObject param = new JSONObject();
        param.put("id", id);
        return dcproducto(param.toString());
    }

    @POST
    @Path("/dcproducto")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response dcproducto(String jsonStr) {
        JSONObject msg = new JSONObject();
        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            int id = jsonObj.getInt("id");
            cproductorepository.delete(id);
            httpstatus = Response.Status.OK;
            msg.put("dato", "Producto Eliminados con Exito");

        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }

        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fcproducto")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproducto() {
        return fcproductoS();
    }

    @POST
    @Path("/fcproducto")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproductoS() {
        JSONArray jarr = new JSONArray();
        List<JSONObject> ArrJson = new ArrayList<>();
        JSONObject obj = new JSONObject();
        try {
            List<CProducto> list = cproductorepository.findByAllOrder();
            for (CProducto list1 : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getId());
                objfor.put("id_marca", (list1.getCmarca() != null ? list1.getCmarca().getId() : ""));
                objfor.put("id_modelo", (list1.getCmodelo() != null ? list1.getCmodelo().getCModeloPK().getId() : ""));
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("id_categoria", (list1.getCcategoria() != null ? list1.getCcategoria().getId() : ""));
                objfor.put("stock", list1.getStock());
                objfor.put("servicio", list1.getServicio());
                objfor.put("stock_minimo", list1.getStockMinimo());
                objfor.put("stock_maximo", list1.getStockMaximo());
                objfor.put("suspendido", list1.getSuspendido());
                objfor.put("costo_compra", list1.getCostoCompra());
                objfor.put("costo_fob", list1.getCostoFob());
                objfor.put("costo_contable", list1.getCostoContable());
                objfor.put("ultimo_costo_s_impuesto", list1.getUltimoCostoSImpuesto());
                objfor.put("ultimo_costo_c_impuesto", list1.getUltimoCostoCImpuesto());
                objfor.put("costo_prom_s_impuesto", list1.getCostoPromSImpuesto());
                objfor.put("costo_prom_c_impuesto", list1.getCostoPromCImpuesto());
                objfor.put("costo_anterior_c_impuesto", list1.getCostoAnteriorCImpuesto());
                objfor.put("utilidad1", list1.getUtilidad1());
                objfor.put("precio1", list1.getPrecio1());
                objfor.put("utilidad2", list1.getUtilidad2());
                objfor.put("precio2", list1.getPrecio2());
                objfor.put("utildad3", list1.getUtildad3());
                objfor.put("precio3", list1.getPrecio3());
                objfor.put("desc_categoria", (list1.getCcategoria() != null ? list1.getCcategoria().getDescripcion() : ""));
                objfor.put("desc_marca", (list1.getCmarca() != null ? list1.getCmarca().getDescripcion() : ""));
                objfor.put("desc_modelo", (list1.getCmodelo() != null ? list1.getCmodelo().getDescripcion() : ""));
                objfor.put("oem", list1.getOem());
                objfor.put("codigo", list1.getCodigo());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                objfor.put("fecha_recepcion", df.format(list1.getFechaRecepcion()));
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
    @Path("/fcproductoarticulos")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproductoArticulos() {
        return fcproductoArticulosS();
    }

    @POST
    @Path("/fcproductoarticulos")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproductoArticulosS() {
        JSONObject msg = new JSONObject();
        try {
            
            List<JSONObject> listJson = new ArrayList<>();
            JSONArray jarr = new JSONArray();
            List<Object[]> o = cproductorepository.getAllArticulos();
            for (int i = 0; i < o.size(); i++) {
                Object[] articulo = o.get(i);
                JSONObject json = new JSONObject();
                json.put("id", String.valueOf(articulo[0]));
                json.put("codigo", String.valueOf(articulo[1]));
                json.put("descripcion", String.valueOf(articulo[2]));
                json.put("marca", String.valueOf(articulo[3]));
                json.put("modelo", String.valueOf(articulo[4]));
                json.put("precio", String.valueOf(articulo[5]));
                json.put("stock", String.valueOf(articulo[6]));
                json.put("sucursal", String.valueOf(articulo[7]));
                listJson.add(json);
            }
            jarr = new JSONArray(listJson);
            msg.put("dato", jarr);
            httpstatus = Response.Status.OK;
        } catch (JSONException | NumberFormatException s) {
            s.printStackTrace();
            httpstatus = Response.Status.BAD_REQUEST;
            msg.put("dato", s.getMessage());
        }
        return Response.status(httpstatus).entity(msg.toString()).build();
    }

    @GET
    @Path("/fcproductobyempresaalmacen/{id_empresa}/{id_almacen}")
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproductobyempresaalmacen(@PathParam("id_empresa") int id_empresa, @PathParam("id_almacen") int id_almacen) {
        JSONObject param = new JSONObject();
        param.put("idEmpresa", id_empresa);
        param.put("idAlmacen", id_almacen);
        return fcproductobyempresaalmacen(param.toString());
    }

    @POST
    @Path("/fcproductobyempresaalmacen")
    @Consumes(RESTConstants.APPLICATION_JSON_UTF8)
    @Produces(RESTConstants.APPLICATION_JSON_UTF8)
    public Response fcproductobyempresaalmacen(String jsonStr) {
        JSONArray jarr = new JSONArray();
        List<JSONObject> ArrJson = new ArrayList<>();
        JSONObject obj = new JSONObject();
        JSONObject jsonObj = new JSONObject(jsonStr);
        int idEmpresa = jsonObj.getInt("idEmpresa");
        int idAlmacen = jsonObj.getInt("idAlmacen");
        try {
            List<CProducto> list = cproductorepository.findAllProductByAlmacenEmpresa(idEmpresa, idAlmacen);
            for (CProducto list1 : list) {
                JSONObject objfor = new JSONObject();
                objfor.put("id", list1.getId());
                objfor.put("id_marca", (list1.getCmarca() != null ? list1.getCmarca().getId() : ""));
                objfor.put("id_modelo", (list1.getCmodelo() != null ? list1.getCmodelo().getCModeloPK().getId() : ""));
                objfor.put("descripcion", list1.getDescripcion());
                objfor.put("id_categoria", (list1.getCcategoria() != null ? list1.getCcategoria().getId() : ""));
                objfor.put("stock", list1.getStock());
                objfor.put("servicio", list1.getServicio());
                objfor.put("stock_minimo", list1.getStockMinimo());
                objfor.put("stock_maximo", list1.getStockMaximo());
                objfor.put("suspendido", list1.getSuspendido());
                objfor.put("costo_compra", list1.getCostoCompra());
                objfor.put("costo_fob", list1.getCostoFob());
                objfor.put("costo_contable", list1.getCostoContable());
                objfor.put("ultimo_costo_s_impuesto", list1.getUltimoCostoSImpuesto());
                objfor.put("ultimo_costo_c_impuesto", list1.getUltimoCostoCImpuesto());
                objfor.put("costo_prom_s_impuesto", list1.getCostoPromSImpuesto());
                objfor.put("costo_prom_c_impuesto", list1.getCostoPromCImpuesto());
                objfor.put("costo_anterior_c_impuesto", list1.getCostoAnteriorCImpuesto());
                objfor.put("utilidad1", list1.getUtilidad1());
                objfor.put("precio1", list1.getPrecio1());
                objfor.put("utilidad2", list1.getUtilidad2());
                objfor.put("precio2", list1.getPrecio2());
                objfor.put("utildad3", list1.getUtildad3());
                objfor.put("precio3", list1.getPrecio3());
                objfor.put("desc_categoria", (list1.getCcategoria() != null ? list1.getCcategoria().getDescripcion() : ""));
                objfor.put("desc_marca", (list1.getCmarca() != null ? list1.getCmarca().getDescripcion() : ""));
                objfor.put("desc_modelo", (list1.getCmodelo() != null ? list1.getCmodelo().getDescripcion() : ""));
                objfor.put("oem", list1.getOem());
                objfor.put("codigo", list1.getCodigo());
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                objfor.put("fecha_recepcion", df.format(list1.getFechaRecepcion()));
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

    public CProductoService() {
        cproductorepository = ApplicationContextProvider.getApplicationContext().getBean(CProductoRepository.class);
    }
}
