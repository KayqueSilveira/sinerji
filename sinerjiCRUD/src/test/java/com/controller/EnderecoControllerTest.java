package com.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import com.model.Endereco;
import com.model.Pessoa;
import com.repository.EnderecoRepository;
import com.repository.PessoaRepository;

public class EnderecoControllerTest {

	private EnderecoController enderecoController;
	private EnderecoRepository enderecoRepository;
	private PessoaRepository pessoaRepository;

	@Before
	public void setUp() {
		enderecoRepository = mock(EnderecoRepository.class);
		pessoaRepository = mock(PessoaRepository.class);
		enderecoController = new EnderecoController();
		enderecoController.setEnderecoRepository(enderecoRepository);
		enderecoController.setPessoaRepository(pessoaRepository);
	}

	@Test
	public void testSalvar() {
	    // Mock de dados de entrada
	    String estado = "Estado Teste";
	    String cidade = "Cidade Teste";
	    String logradouro = "Logradouro Teste";
	    String numero = "123";
	    String cep = "12345-678";
	    String nomePessoa = "Nome Teste";

	    // Mock de objeto Pessoa retornado pelo método buscarPessoaPorNome()
	    Pessoa pessoaMock = new Pessoa();
	    pessoaMock.setId(1);
	    pessoaMock.setNome(nomePessoa);

	    // Configurando comportamento do mock do PessoaRepository
	    when(pessoaRepository.buscarPorNome(nomePessoa)).thenReturn(pessoaMock);

	    // Chamando o método a ser testado
	    enderecoController.setEstado(estado);
	    enderecoController.setCidade(cidade);
	    enderecoController.setLogradouro(logradouro);
	    enderecoController.setNumero(numero);
	    enderecoController.setCep(cep);
	    enderecoController.setNomePessoa(nomePessoa);
	    enderecoController.salvar();

	    // Verificando se o método salvar() do EnderecoRepository foi chamado com o Endereco esperado
	    verify(enderecoRepository).salvar(argThat(new ArgumentMatcher<Endereco>() {
	        @Override
	        public boolean matches(Endereco argument) {
	            return argument.getEstado().equals(estado) &&
	                   argument.getCidade().equals(cidade) &&
	                   argument.getLogradouro().equals(logradouro) &&
	                   argument.getNumero().equals(numero) &&
	                   argument.getCep().equals(cep) &&
	                   argument.getPessoa().equals(pessoaMock);
	        }
	    }));
	}


	@Test
	public void testListarEnderecos() {
		// Mock de lista de endereços
		List<Endereco> enderecosMock = new ArrayList<>();
		enderecosMock.add(new Endereco());
		enderecosMock.add(new Endereco());

		// Configurando comportamento do mock do EnderecoRepository
		when(enderecoRepository.listarEnderecos()).thenReturn(enderecosMock);

		// Chamando o método a ser testado
		List<Endereco> enderecosRetornados = enderecoController.listarEnderecos();

		// Verificando se o método listarEnderecos() retorna a lista de endereços
		// esperada
		assertEquals(enderecosMock, enderecosRetornados);
	}
}
