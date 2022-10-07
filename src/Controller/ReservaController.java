package Controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import Dao.ReservaDAO;
import Modelo.Reservas;
import factory.ConnectionFactory;

public class ReservaController {

	private ReservaDAO intermediador;

	public ReservaController() {
		Connection abreConexao = new ConnectionFactory().recuperarConexao();
		this.intermediador = new ReservaDAO(abreConexao);
	}
	
	public void salvarControoller(Reservas recebeReservasView) {
		this.intermediador.salvarDAO(recebeReservasView);
	}
	
	public List<Reservas> listarController() {
		return this.intermediador.ListarDAO();
	}
	
	public void deletarController(Integer id) {
		this.intermediador.deletarDAO(id);
	}
	
	public void alterarController( String dataEntrada, String dataSaida, String valor, String formaPagamento, Integer id ) {
		this.intermediador.alterarDAO(dataEntrada, dataSaida, valor, formaPagamento, id);
	}
}
