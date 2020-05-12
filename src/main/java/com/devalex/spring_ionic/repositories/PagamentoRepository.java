package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
