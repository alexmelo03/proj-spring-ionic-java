package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
