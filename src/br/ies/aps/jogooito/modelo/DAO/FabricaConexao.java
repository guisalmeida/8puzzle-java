package br.ies.aps.jogooito.modelo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
	
	public static Connection getConexao() {
		try {
			final String url = "jdbc:postgresql://localhost:5432/jogo_oito";
			final String user = "postgres";
			final String password  = "guidev87";
			
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
