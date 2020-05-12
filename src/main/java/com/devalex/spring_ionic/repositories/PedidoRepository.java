package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
