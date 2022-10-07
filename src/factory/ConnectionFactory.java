package factory;

import java.sql.Connection;


import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionFactory {

	public DataSource conexao;

	public ConnectionFactory() {
		ComboPooledDataSource conexaoCombo = new ComboPooledDataSource();
		conexaoCombo.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=UTC");
		conexaoCombo.setUser("root");
		conexaoCombo.setPassword("lucas232587");

		this.conexao = conexaoCombo;
	}

	public Connection recuperarConexao() {
		try {
			return this.conexao.getConnection();
		}catch( Exception e) {
			throw new RuntimeException(e);
		}
	}
}
