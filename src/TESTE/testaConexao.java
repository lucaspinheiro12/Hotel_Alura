package TESTE;

import java.sql.Connection;
import java.sql.SQLException;

import factory.ConnectionFactory;

public class testaConexao {


	public static void main(String[] args) throws SQLException {
		ConnectionFactory abre = new ConnectionFactory();
		Connection conexao = abre.recuperarConexao();
		
		System.out.println("oi deu certo");
		conexao.close();
	}
}
