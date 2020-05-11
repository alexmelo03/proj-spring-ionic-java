package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
