package com.devalex.spring_ionic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devalex.spring_ionic.domain.Categoria;
import com.devalex.spring_ionic.domain.Cidade;
import com.devalex.spring_ionic.domain.Cliente;
import com.devalex.spring_ionic.domain.Endereco;
import com.devalex.spring_ionic.domain.Estado;
import com.devalex.spring_ionic.domain.Produto;
import com.devalex.spring_ionic.domain.enums.TipoCliente;
import com.devalex.spring_ionic.repositories.CategoriaRepository;
import com.devalex.spring_ionic.repositories.CidadeRepository;
import com.devalex.spring_ionic.repositories.ClienteRepository;
import com.devalex.spring_ionic.repositories.EnderecoRepository;
import com.devalex.spring_ionic.repositories.EstadoRepository;
import com.devalex.spring_ionic.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjSpringIonicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjSpringIonicApplication.class, args);
	}
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 =  new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "68572145145", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("37245698", "968475932"));
		
		Endereco e1 =  new Endereco(null, "Rua Flores", "301", "Apto 132", "Jardim,", "67150-200", cli1, c1);
		Endereco e2 =  new Endereco(null, "Rua Nova", "1002", "Sala 800", "Consolação,", "58950-200", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
	}

}
