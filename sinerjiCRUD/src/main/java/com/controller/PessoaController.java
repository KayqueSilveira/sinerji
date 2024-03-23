package com.controller;

import java.util.List;

import com.model.Pessoa;
import com.repository.PessoaRepository;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.ViewScoped;
import jakarta.faces.context.FacesContext;

@SuppressWarnings("deprecation")
@ManagedBean(name = "pessoaController")
@ViewScoped
public class PessoaController {

	private String nome;
	private String nomePessoa;
	private int idade;
	private String sexo;
	private int pessoaId;
	private Pessoa pessoa;

	private PessoaRepository pessoaRepository = new PessoaRepository();

	public void salvar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(this.nome);
		pessoa.setIdade(this.idade);
		pessoa.setSexo(this.sexo);
		pessoaRepository.salvar(pessoa);
	}

	public List<Pessoa> listarPessoas() {
		return pessoaRepository.listarPessoas();
	}

	public String redirecionarParaEdicao(int pessoaId) {
		return "editar-pessoa.xhtml?id=" + pessoaId + "&faces-redirect=true";
	}

	public Pessoa carregarDadosDaPessoa() {
		return pessoaRepository.buscarPorId(pessoaId);
	}

	public void deletePessoa(int id) {
		pessoaRepository.deletaPessoa(id);
	}

	public void salvarEdicao() {
		Pessoa pessoa = new Pessoa();
		pessoa.setId(this.pessoaId);
		pessoa.setNome(this.nome);
		pessoa.setIdade(this.idade);
		pessoa.setSexo(this.sexo);
		System.out.println(pessoa.toString());

		pessoaRepository.editar(pessoa);

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa editada com sucesso!"));
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
