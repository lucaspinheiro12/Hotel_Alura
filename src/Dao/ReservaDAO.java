package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Reservas;

public class ReservaDAO {

	private Connection conexao;
	
	public ReservaDAO(Connection conexaoDeFora ) {
		this.conexao = conexaoDeFora;
	}
	
	public void salvarDAO (Reservas construtor1) {
		try {
			String sql = "INSERT INTO reservas (DataEntrada, DataSaida, Valor, FormaPagamento) VALUES (?, ?, ?, ?)";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, construtor1.getDataEntrada());	
				pstm.setString(2, construtor1.getDataSaida());
				pstm.setString(3, construtor1.getValor());
				pstm.setString(4, construtor1.getFormaPagamento());
			
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
	
	public List<Reservas> ListarDAO() {
		List<Reservas> ReservasCadastradas = new ArrayList<Reservas>();
		try {
			String sql = "SELECT * FROM reservas";

			try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmTabelaReservas(ReservasCadastradas , pstm);
			}
			return ReservasCadastradas ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterarDAO( String dataEntrada, String dataSaida, String valor, String formaPagamento, Integer id ){
		try {
			String sql = "UPDATE reservas SET DataEntrada = ?, DataSaida = ?, Valor = ?, FormaPagamento = ? WHERE id = ?";
			try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
					
				pstm.setString(1, dataEntrada);
				pstm.setString(2, dataSaida);
				pstm.setString(3, valor);
				pstm.setString(4, formaPagamento);
				pstm.setInt(5, id);
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletarDAO(Integer id) {
		try {
			try (PreparedStatement pstm = conexao.prepareStatement("DELETE FROM reservas WHERE ID = ?")) {
				pstm.setInt(1, id);
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEmTabelaReservas(List<Reservas> ReservasCadastradas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Reservas reserva = new Reservas(rst.getInt(1), rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5));

				ReservasCadastradas.add(reserva);
			}
		}
	}
}
