package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.LoginUsuario;



public class LoginDAO {
	private Connection conexao;
	
	public LoginDAO (Connection conexaoFora) {
		this.conexao = conexaoFora;
	}
	
	public void salvarDAO (LoginUsuario construtor1) {
		try {
			String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, construtor1.getUsuario());
				pstm.setString(2, construtor1.getSenha());
				
			
				pstm.executeUpdate();
			
				try(ResultSet rst = pstm.getGeneratedKeys()){
					while(rst.next()) {
						construtor1.setId(rst.getInt(1));
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public List<LoginUsuario> listar() {
		List<LoginUsuario> loginUsuarios = new ArrayList<LoginUsuario>();
		try {
			String sql = "SELECT usuario, senha FROM usuarios";

			try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmLogin(loginUsuarios , pstm);
			}
			return loginUsuarios ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEmLogin(List<LoginUsuario> loginUsuarios, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				LoginUsuario Logins = new LoginUsuario(rst.getString(1), rst.getString(2));

				loginUsuarios.add(Logins);
			}
		}
	}

}
