package com.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import com.model.*;

import com.repository.PessoaRepository;

public class PessoaControllerTest {
    private PessoaController pessoaController;
    private PessoaRepository pessoaRepository;

    @Captor
    private ArgumentCaptor<Pessoa> pessoaCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pessoaRepository = mock(PessoaRepository.class);
        pessoaController = new PessoaController();
        pessoaController.setPessoaRepository(pessoaRepository);
    }

    @Test
    public void testSalvar() {
        // Mock de dados de entrada
        String nome = "Teste";
        int idade = 30;
        String sexo = "M";

        // Chamando o método a ser testado
        pessoaController.setNome(nome);
        pessoaController.setIdade(idade);
        pessoaController.setSexo(sexo);
        pessoaController.salvar();

        // Verificando se o método salvar() do PessoaRepository foi chamado com a Pessoa esperada
        verify(pessoaRepository).salvar(pessoaCaptor.capture());
        Pessoa pessoaSalva = pessoaCaptor.getValue();
        assertEquals(nome, pessoaSalva.getNome());
        assertEquals(idade, pessoaSalva.getIdade());
        assertEquals(sexo, pessoaSalva.getSexo());
    }

    @Test
    public void testListarPessoas() {
        // Mock de lista de pessoas
        List<Pessoa> pessoasMock = new ArrayList<>();
        pessoasMock.add(new Pessoa());
        pessoasMock.add(new Pessoa());

        // Configurando comportamento do mock do PessoaRepository
        when(pessoaRepository.listarPessoas()).thenReturn(pessoasMock);

        // Chamando o método a ser testado
        List<Pessoa> pessoasRetornadas = pessoaController.listarPessoas();

        // Verificando se o método listarPessoas() retorna a lista de pessoas esperada
        assertEquals(pessoasMock, pessoasRetornadas);
    }

    @Test
    public void testDeletePessoa() {
        // Chamando o método a ser testado
        int idPessoa = 1;
        pessoaController.deletePessoa(idPessoa);

        // Verificando se o método deletePessoa() do PessoaRepository foi chamado com o ID da pessoa
        verify(pessoaRepository).deletaPessoa(idPessoa);
    }

    @Test
    public void testSalvarEdicao() {
        // Mock de dados de entrada
        int pessoaId = 1;
        String nome = "Teste";
        int idade = 30;
        String sexo = "M";

        // Chamando o método a ser testado
        pessoaController.setPessoaId(pessoaId);
        pessoaController.setNome(nome);
        pessoaController.setIdade(idade);
        pessoaController.setSexo(sexo);
        pessoaController.salvarEdicao();

        // Verificando se o método editar() do PessoaRepository foi chamado com a Pessoa esperada
        verify(pessoaRepository).editar(pessoaCaptor.capture());
        Pessoa pessoaEditada = pessoaCaptor.getValue();
        assertEquals(pessoaId, pessoaEditada.getId());
        assertEquals(nome, pessoaEditada.getNome());
        assertEquals(idade, pessoaEditada.getIdade());
        assertEquals(sexo, pessoaEditada.getSexo());
    }
}
