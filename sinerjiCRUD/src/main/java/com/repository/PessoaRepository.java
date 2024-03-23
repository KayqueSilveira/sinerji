package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.dao.Conexao;
import com.model.Pessoa;

@Stateless
public class PessoaRepository {

	public boolean verificarNomeExistente(String nome) {
		String sql = "SELECT COUNT(*) FROM pessoa WHERE nome = ?";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, nome);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt(1);
					return count > 0;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return false;
	}

	public void salvar(Pessoa pessoa) {
		if (verificarNomeExistente(pessoa.getNome())) {
			throw new RuntimeException("Já existe uma pessoa com o mesmo nome");
		}
		String sql = "INSERT INTO Pessoa (nome, idade, sexo) VALUES (?, ?, ?)";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getIdade());
			stmt.setString(3, pessoa.getSexo());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pessoa> listarPessoas() {
		List<Pessoa> pessoas = new ArrayList<>();
		String sql = "SELECT * FROM pessoa";

		try (Connection conexao = Conexao.getConexao();
				PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoas.add(pessoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoas;
	}

	public Pessoa buscarPorNome(String nome) {
		String sql = "SELECT * FROM pessoa WHERE nome = ?"; // Corrigindo a consulta SQL

		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setString(1, nome);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("nome"));
					pessoa.setIdade(rs.getInt("idade"));
					pessoa.setSexo(rs.getString("sexo"));
					return pessoa;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public void deletaPessoa(int id) {
		String sql = "DELETE FROM pessoa WHERE id = ?";
		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Pessoa buscarPorId(int id) {
		String sql = "SELECT * FROM pessoa WHERE id = ?";

		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Pessoa pessoa = new Pessoa();
					pessoa.setId(rs.getInt("id"));
					pessoa.setNome(rs.getString("nome"));
					pessoa.setIdade(rs.getInt("idade"));
					pessoa.setSexo(rs.getString("sexo"));
					return pessoa;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	public void editar(Pessoa pessoa) {
		String sql = "UPDATE pessoa SET nome = ?, idade = ?, sexo = ? WHERE id = ?";
		System.out.print(sql);
		try (Connection conexao = Conexao.getConexao(); PreparedStatement stmt = conexao.prepareStatement(sql)) {

			stmt.setString(1, pessoa.getNome());
			stmt.setInt(2, pessoa.getIdade());
			stmt.setString(3, pessoa.getSexo());
			stmt.setInt(4, pessoa.getId());

			int linhasAfetadas = stmt.executeUpdate();
			if (linhasAfetadas == 0) {
				throw new RuntimeException("Nenhuma pessoa encontrada com o ID especificado: " + pessoa.getId());
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao editar pessoa", e);
		}
	}

}
