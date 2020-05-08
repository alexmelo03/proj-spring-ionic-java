package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
