package com.devalex.spring_ionic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devalex.spring_ionic.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
