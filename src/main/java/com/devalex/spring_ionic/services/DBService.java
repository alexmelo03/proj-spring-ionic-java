package com.devalex.spring_ionic.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.devalex.spring_ionic.domain.enums.Perfil;
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

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	
	public void instantiateDataBase() throws ParseException {
	
	Categoria cat1 = new Categoria(null, "Informática");
	Categoria cat2 = new Categoria(null, "Escritório");
	Categoria cat3 = new Categoria(null, "Cama mesa e banho");
	Categoria cat4 = new Categoria(null, "Eletrônicos");
	Categoria cat5 = new Categoria(null, "Jardinagem");
	Categoria cat6 = new Categoria(null, "Decoração");
	Categoria cat7 = new Categoria(null, "Perfumaria");
	
	
	Produto p1 = new Produto(null, "Computador", 2000.00);
	Produto p2 = new Produto(null, "Impressora", 800.00);
	Produto p3 = new Produto(null, "Mouse", 80.00);
	Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
	Produto p5 = new Produto(null, "Toalha", 50.00);
	Produto p6 = new Produto(null, "Colcha", 200.00);
	Produto p7 = new Produto(null, "TV true color", 1200.00);
	Produto p8 = new Produto(null, "Roçadeira", 800.00);
	Produto p9 = new Produto(null, "Abajour", 100.00);
	Produto p10 = new Produto(null, "Pendente", 180.00);
	Produto p11 = new Produto(null, "Shampoo", 90.00);
	
	cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
	cat2.getProdutos().addAll(Arrays.asList(p2, p4));
	cat3.getProdutos().addAll(Arrays.asList(p5, p6));
	cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
	cat5.getProdutos().addAll(Arrays.asList(p8));
	cat6.getProdutos().addAll(Arrays.asList(p9, p10));
	cat7.getProdutos().addAll(Arrays.asList(p11));

	p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
	p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
	p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
	p4.getCategorias().addAll(Arrays.asList(cat2));
	p5.getCategorias().addAll(Arrays.asList(cat3));
	p6.getCategorias().addAll(Arrays.asList(cat3));
	p7.getCategorias().addAll(Arrays.asList(cat4));
	p8.getCategorias().addAll(Arrays.asList(cat5));
	p9.getCategorias().addAll(Arrays.asList(cat6));
	p10.getCategorias().addAll(Arrays.asList(cat6));
	p11.getCategorias().addAll(Arrays.asList(cat7));	
	
	categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
	produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

	Estado est1 = new Estado(null, "Minas Gerais");
	Estado est2 =  new Estado(null, "São Paulo");
	
	Cidade c1 = new Cidade(null, "Uberlandia", est1);
	Cidade c2 = new Cidade(null, "São Paulo", est2);
	Cidade c3 = new Cidade(null, "Campinas", est2);
	
	estadoRepository.saveAll(Arrays.asList(est1, est2));
	cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	
	Cliente cli1 = new Cliente(null, "Alex Melo", "alexmello79@yahoo.com.br", "68572145145", TipoCliente.PESSOAFISICA, pe.encode("123"));
	cli1.getTelefones().addAll(Arrays.asList("37245698", "968475932"));
	
	Cliente cli2 = new Cliente(null, "Alex Andrade", "alexmello79@hotmail.com", "50260482005", TipoCliente.PESSOAFISICA, pe.encode("1234"));
	cli2.getTelefones().addAll(Arrays.asList("93553321", "34652625"));
	cli2.addPerfil(Perfil.ADMIN);
	
	Endereco e1 =  new Endereco(null, "Rua Flores", "301", "Apto 132", "Jardim,", "67150-200", cli1, c1);
	Endereco e2 =  new Endereco(null, "Rua Nova", "1002", "Sala 800", "Consolação,", "58950-200", cli1, c2);
	Endereco e3 =  new Endereco(null, "Rua Para", "604", "Apto 1002", "Coqueiro,", "67140-200", cli1, c3);
	Endereco e4 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);
	
	cli1.getEnderecos().addAll(Arrays.asList(e1, e2, e3));
	cli2.getEnderecos().addAll(Arrays.asList(e4));
	
	clienteRepository.saveAll(Arrays.asList(cli1, cli2));
	enderecoRepository.saveAll(Arrays.asList(e1, e2, e3, e4));
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
	Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
	Pedido ped3 = new Pedido(null, sdf.parse("15/12/2018 19:105"), cli2, e4);
	
	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
	ped1.setPagamento(pagto1);

	Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
	ped2.setPagamento(pagto2);
	
	Pagamento pagto3 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped3, sdf.parse("15/12/2018 00:00"), null);
	ped3.setPagamento(pagto3);


	cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
	cli2.getPedidos().addAll(Arrays.asList(ped3));

	pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
	pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2, pagto3));
	
	ItemPedido ip1 =  new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
	ItemPedido ip2 =  new ItemPedido(ped1, p3, 0.0, 2, 80.00);
	ItemPedido ip3 =  new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	ItemPedido ip4 =  new ItemPedido(ped3, p7, 200.00, 1, 1200.00);
	
	ped1.getItens().addAll(Arrays.asList(ip1, ip2));
	ped2.getItens().addAll(Arrays.asList(ip3));
	ped3.getItens().addAll(Arrays.asList(ip4));
	
	p1.getItens().addAll(Arrays.asList(ip1));
	p2.getItens().addAll(Arrays.asList(ip3));
	p3.getItens().addAll(Arrays.asList(ip2));
	p7.getItens().addAll(Arrays.asList(ip4));
	
	itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4));
	
	}

}
