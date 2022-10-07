package TESTE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Dao.LoginDAO;
import Modelo.LoginUsuario;
import factory.ConnectionFactory;



public class Testaadicao {
	public static void main(String[] args) throws SQLException {
		try(Connection abreConexao = new ConnectionFactory().recuperarConexao()){
			
			LoginDAO login1 = new LoginDAO(abreConexao);
															//listar	
			List<LoginUsuario> listaDelogin = login1.listar();
			
				for(LoginUsuario listaDelogin1 : listaDelogin) {
					String usuario = "lucas";
					String senha = "lucas";
					System.out.println(listaDelogin1.getUsuario());
					System.out.println(listaDelogin1.getSenha());
					if( listaDelogin1.getUsuario().toString().equals(usuario)) {
						System.out.println("deu certo");
						return;
					}else {
						System.out.println("nao deu");
						System.out.println(usuario+ "---"+ senha);
					}
			}
			
//			listaDelogin.forEach(ct -> {
//				System.out.println("USUARIO: " + ct.getUsuario() +"| SENHA: " + ct.getSenha());
//				String usuario = "lucas";
//				String senha = "lucas";
//				if( ct.getUsuario().toString().equals(usuario)) {
//					System.out.println("deu certo");
//				}else {
//					System.out.println("nao deu");
//					System.out.println(usuario+ "---"+ senha);
//				}
//				
//				});
			}
		}
}
