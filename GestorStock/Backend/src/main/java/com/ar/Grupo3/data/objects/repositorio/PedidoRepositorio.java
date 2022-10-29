package com.ar.Grupo3.data.objects.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ar.Grupo3.model.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {

}
