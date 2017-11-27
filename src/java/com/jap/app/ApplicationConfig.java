/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.app;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author irvin_monterroza
 */
@javax.ws.rs.ApplicationPath("jap")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.jap.catalogo.service.CAlmacenService.class);
        resources.add(com.jap.catalogo.service.CCategoriaService.class);
        resources.add(com.jap.catalogo.service.CCiudadService.class);
        resources.add(com.jap.catalogo.service.CClienteService.class);
        resources.add(com.jap.catalogo.service.CDeptoService.class);
        resources.add(com.jap.catalogo.service.CEmpleadoService.class);
        resources.add(com.jap.catalogo.service.CEmpresaService.class);
        resources.add(com.jap.catalogo.service.CEstiloService.class);
        resources.add(com.jap.catalogo.service.CFormaPagoService.class);
        resources.add(com.jap.catalogo.service.CGiroService.class);
        resources.add(com.jap.catalogo.service.CMarcaService.class);
        resources.add(com.jap.catalogo.service.CModeloService.class);
        resources.add(com.jap.catalogo.service.CMunicipioService.class);
        resources.add(com.jap.catalogo.service.CProductoService.class);
        resources.add(com.jap.catalogo.service.CProveedorService.class);
        resources.add(com.jap.catalogo.service.CTallerService.class);
        resources.add(com.jap.catalogo.service.CTipoDocProveedorService.class);
        resources.add(com.jap.catalogo.service.CTipoDocService.class);
        resources.add(com.jap.catalogo.service.CTipoFacturaService.class);
        resources.add(com.jap.catalogo.service.CTipoProductoService.class);
        resources.add(com.jap.catalogo.service.CTipoVehiculoService.class);
        resources.add(com.jap.catalogo.service.PParametrosService.class);
        resources.add(com.jap.proceso.service.PFactDetailService.class);
        resources.add(com.jap.proceso.service.PFacturacionService.class);
        resources.add(com.jap.proceso.service.PProdTiendaService.class);
        resources.add(com.jap.seguridad.service.SOpcionPrincipalService.class);
        resources.add(com.jap.seguridad.service.SOpcionRolService.class);
        resources.add(com.jap.seguridad.service.SOpcionService.class);
        resources.add(com.jap.seguridad.service.SRolService.class);
        resources.add(com.jap.seguridad.service.SRolUsuarioService.class);
        resources.add(com.jap.seguridad.service.SUsuarioService.class);
    }
}
