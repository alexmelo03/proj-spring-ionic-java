package com.devalex.spring_ionic.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Cliente;
import com.devalex.spring_ionic.repositories.ClienteRepository;
import com.devalex.spring_ionic.services.exceptions.ObjectNotFoundException;

@Service 
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
		
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	

}
