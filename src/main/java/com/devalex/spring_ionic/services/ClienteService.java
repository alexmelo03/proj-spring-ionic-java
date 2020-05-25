package com.devalex.spring_ionic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.devalex.spring_ionic.domain.Cliente;
import com.devalex.spring_ionic.dto.ClienteDTO;
import com.devalex.spring_ionic.repositories.ClienteRepository;
import com.devalex.spring_ionic.services.exceptions.DataIntegrityException;
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
	
	public Cliente atualizar(Cliente obj) {
		Cliente newObj = buscarPorId(obj.getId());
		atualizarDados(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		buscarPorId(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}
	
	public List<Cliente> buscarPorTodos() {
		return repo.findAll();
	}
	
	public Page<Cliente> buscaPorPagina(Integer pagina, Integer linhasPorPagina, String direcao, String ordemPor) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordemPor);
		return repo.findAll(pageRequest);
	}
	
	public Cliente apartirDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void atualizarDados(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
