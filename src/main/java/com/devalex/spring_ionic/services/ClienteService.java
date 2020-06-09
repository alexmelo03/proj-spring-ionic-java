package com.devalex.spring_ionic.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.devalex.spring_ionic.domain.Cidade;
import com.devalex.spring_ionic.domain.Cliente;
import com.devalex.spring_ionic.domain.Endereco;
import com.devalex.spring_ionic.domain.enums.Perfil;
import com.devalex.spring_ionic.domain.enums.TipoCliente;
import com.devalex.spring_ionic.dto.ClienteDTO;
import com.devalex.spring_ionic.dto.ClienteNewDTO;
import com.devalex.spring_ionic.repositories.ClienteRepository;
import com.devalex.spring_ionic.repositories.EnderecoRepository;
import com.devalex.spring_ionic.security.UserSS;
import com.devalex.spring_ionic.services.exceptions.AuthorizationException;
import com.devalex.spring_ionic.services.exceptions.DataIntegrityException;
import com.devalex.spring_ionic.services.exceptions.ObjectNotFoundException;

@Service 
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
		
	public Cliente buscarPorId(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	@Transactional
	public Cliente inserir(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
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
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente apartirDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.converterParaEnum(objDto.getTipo()),  pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void atualizarDados(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		return s3Service.uploadFile(multipartFile);
	}
	
	
}
