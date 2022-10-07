package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import Dao.HospedeDAO;
import Modelo.Hospede;
import factory.ConnectionFactory;

public class HospedeController {

	private HospedeDAO intermediador;
	
	public HospedeController() {
		Connection abreConexao = new ConnectionFactory().recuperarConexao();
		this.intermediador = new HospedeDAO(abreConexao);
	}
	
	public void salvarControoller(Hospede recebeRegistroHospedeView) {
		this.intermediador.salvarDAO(recebeRegistroHospedeView);
	}
	
	public List<Hospede> listarController() {
		return this.intermediador.ListarDAO();
	}
	
	public void deletarController(Integer id) {
		this.intermediador.deletarDAO(id);
	}

	public void alterarController(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer idReserva, Integer id ) {
		this.intermediador.alterarDAO(nome, sobrenome, dataNascimento, nacionalidade, telefone, idReserva, id);
	}
}
