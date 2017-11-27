/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jap.spec;

import com.jap.entity.PFacturacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author fernando_batres
 */
public class PFacturacionSpecs {
    
    public static Specification<PFacturacion> isFilteredDocument(final String idEmpresa,final String correlativo,
            final String tipoDocumento, final String formaPago, final Date fechaDesde, final Date fechaHasta,
            final String cliente, final String sucursal, final String vendedor){
        return new Specification<PFacturacion>() {

            @Override
            public Predicate toPredicate(Root<PFacturacion> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (idEmpresa != null && !idEmpresa.isEmpty()) {
                    predicates.add(cb.equal(root.get("pFacturacionPK").get("idEmpresa"), idEmpresa));
                }
                if (correlativo != null && !correlativo.isEmpty()) {
                    predicates.add(cb.equal(root.get("pFacturacionPK").get("id"), correlativo));
                }
                if (tipoDocumento != null && !tipoDocumento.isEmpty()) {
                    predicates.add(cb.equal(root.get("idTipoFactura"), tipoDocumento));
                }
                if (formaPago != null && !formaPago.isEmpty()) {
                    predicates.add(cb.equal(root.get("idFormaPago"), formaPago));
                }
                if (fechaDesde != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("fechaFactura"), fechaDesde));
                }
                if (fechaHasta != null) {
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("fechaFactura"), fechaHasta));
                }
                if (cliente != null && !cliente.isEmpty()) {
                    predicates.add(cb.equal(root.get("idCliente"), cliente));
                }
                if (sucursal != null && !sucursal.isEmpty()) {
                    predicates.add(cb.equal(root.get("pFacturacionPK").get("idSucursal"), sucursal));
                }
                if (vendedor != null && !vendedor.isEmpty()) {
                    predicates.add(cb.equal(root.get("idVendedor"), vendedor));
                }
                return cb.and(predicates.toArray(new Predicate[] {}));
            }
        };
    }
    
}
