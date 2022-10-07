package Controller;

import java.sql.Connection;

import Dao.LoginDAO;
import Modelo.LoginUsuario;
import factory.ConnectionFactory;

public class LoginController {
	private LoginDAO intermediador;
	
	public LoginController() {
		Connection abreConexao = new ConnectionFactory().recuperarConexao();
		this.intermediador = new LoginDAO(abreConexao);
	}
	
	public void salvarControoller(LoginUsuario recebeRegistroLoginView) {
		this.intermediador.salvarDAO(recebeRegistroLoginView);
	}
}

