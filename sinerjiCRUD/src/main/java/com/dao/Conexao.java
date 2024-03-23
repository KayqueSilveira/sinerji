package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String URL = "jdbc:postgresql://localhost/sinerjia";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "1501997";

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Erro ao carregar o driver do PostgreSQL", e);
		}
	}

	public static Connection getConexao() {
		try {
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
		}
	}
}
