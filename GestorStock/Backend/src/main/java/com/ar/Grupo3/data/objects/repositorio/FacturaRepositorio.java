package com.ar.Grupo3.data.objects.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.Grupo3.model.Factura;

public interface FacturaRepositorio extends JpaRepository< Factura, Long> {

}
