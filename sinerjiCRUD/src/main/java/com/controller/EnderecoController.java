package com.controller;

import java.util.List;

import com.model.Endereco;
import com.model.Pessoa;
import com.repository.EnderecoRepository;
import com.repository.PessoaRepository;

import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;

@SuppressWarnings("deprecation")
@ManagedBean(name = "enderecoController")
@ViewScoped
public class EnderecoController {

	private String estado;
	private String cidade;
	private String logradouro;
	private String numero;
	private String cep;
	private Long idPessoa;
	private String nomePessoa;

	private EnderecoRepository enderecoRepository = new EnderecoRepository();
	private PessoaRepository pessoaRepository = new PessoaRepository();

	public List<Pessoa> getListaPessoas() {
		return pessoaRepository.listarPessoas();
	}

	public Pessoa buscarPessoaPorNome(String nome) {
		return pessoaRepository.buscarPorNome(nome);
	}

	public void salvar() {
		Endereco endereco = new Endereco();
		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		endereco.setLogradouro(logradouro);
		endereco.setNumero(numero);
		endereco.setCep(cep);
		endereco.setPessoa(buscarPessoaPorNome(nomePessoa)); 
		enderecoRepository.salvar(endereco);
	}

	public List<Endereco> listarEnderecos() {
		return enderecoRepository.listarEnderecos();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public EnderecoRepository getEnderecoRepository() {
		return enderecoRepository;
	}

	public void setEnderecoRepository(EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	public PessoaRepository getPessoaRepository() {
		return pessoaRepository;
	}

	public void setPessoaRepository(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

}
