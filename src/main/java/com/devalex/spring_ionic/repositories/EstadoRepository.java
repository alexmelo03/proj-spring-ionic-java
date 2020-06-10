package com.devalex.spring_ionic.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.devalex.spring_ionic.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
