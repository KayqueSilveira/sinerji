package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.dao.Conexao;
import com.model.Endereco;
import com.model.Pessoa;

@Stateless
public class EnderecoRepository {

	public void salvar(Endereco endereco) {
		String sql = "INSERT INTO endereco (estado, cidade, logradouro, numero, cep, id_pessoa) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, endereco.getEstado());
			stmt.setString(2, endereco.getCidade());
			stmt.setString(3, endereco.getLogradouro());
			stmt.setString(4, endereco.getNumero());
			stmt.setString(5, endereco.getCep());
			stmt.setInt(6, endereco.getPessoa().getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Endereco> listarEnderecos() {
		List<Endereco> enderecos = new ArrayList<>();
		String sql = "SELECT * FROM endereco";

		try (Connection conexao = Conexao.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("id"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setLogradouro(rs.getString("logradouro"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCep(rs.getString("cep"));

				int idPessoa = rs.getInt("id_pessoa");
				PessoaRepository pessoaRepository = new PessoaRepository();
				Pessoa pessoa = pessoaRepository.buscarPorId(idPessoa);
				endereco.setPessoa(pessoa);

				enderecos.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return enderecos;
	}

}
