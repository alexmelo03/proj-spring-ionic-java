package com.devalex.spring_ionic;

import java.text.SimpleDateFormat;
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
import com.devalex.spring_ionic.domain.ItemPedido;
import com.devalex.spring_ionic.domain.Pagamento;
import com.devalex.spring_ionic.domain.PagamentoComBoleto;
import com.devalex.spring_ionic.domain.PagamentoComCartao;
import com.devalex.spring_ionic.domain.Pedido;
import com.devalex.spring_ionic.domain.Produto;
import com.devalex.spring_ionic.domain.enums.EstadoPagamento;
import com.devalex.spring_ionic.domain.enums.TipoCliente;
import com.devalex.spring_ionic.repositories.CategoriaRepository;
import com.devalex.spring_ionic.repositories.CidadeRepository;
import com.devalex.spring_ionic.repositories.ClienteRepository;
import com.devalex.spring_ionic.repositories.EnderecoRepository;
import com.devalex.spring_ionic.repositories.EstadoRepository;
import com.devalex.spring_ionic.repositories.ItemPedidoRepository;
import com.devalex.spring_ionic.repositories.PagamentoRepository;
import com.devalex.spring_ionic.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 =  new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 =  new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 =  new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
