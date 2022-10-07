/**
 * 
 */
package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Modelo.Hospede;

/**
 * @author Samsung
 *
 */
public class HospedeDAO {
	
	private Connection conexao;
	
	public HospedeDAO(Connection conexaoFora) {
		this.conexao = conexaoFora;
	}
	
	public void salvarDAO (Hospede construtor1) {
		try {
			String sql = "INSERT INTO hospedes (Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone , IdReserva) VALUES (?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pstm = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
				pstm.setString(1, construtor1.getNome());
				pstm.setString(2, construtor1.getSobrenome());
				pstm.setString(3, construtor1.getDataNacimento());
				pstm.setString(4, construtor1.getNacionalidade());
				pstm.setString(5, construtor1.getTelefone());
				pstm.setInt(6, construtor1.getIdReserva());
				
			
				pstm.execute();
			
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

	public List<Hospede> ListarDAO() {
		List<Hospede> hospedesCadastradas = new ArrayList<Hospede>();
		try {
			String sql = "SELECT * FROM hospedes";

			try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmTabelaHospedes(hospedesCadastradas , pstm);
			}
			return hospedesCadastradas ;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterarDAO( String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer idReserva, Integer id ){
		try {
			String sql = "UPDATE hospedes SET Nome = ?, Sobrenome = ?, DataNascimento = ?, Nacionalidade = ?, Telefone = ? , IdReserva = ? WHERE id = ?";
			try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
					
				pstm.setString(1, nome);
				pstm.setString(2, sobrenome);
				pstm.setString(3, dataNascimento);
				pstm.setString(4, nacionalidade);
				pstm.setString(5, telefone);
				pstm.setInt(6, idReserva);
				pstm.setInt(7, id);
				pstm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletarDAO(Integer id) {
		try {
			try (PreparedStatement stm = conexao.prepareStatement("DELETE FROM hospedes WHERE ID = ?")) {
				stm.setInt(1, id);
				stm.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEmTabelaHospedes(List<Hospede> HospedesCadastradas, PreparedStatement pstm) throws SQLException {
		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				Hospede hospedesTable = new Hospede(rst.getInt(1), rst.getString(2),rst.getString(3), rst.getString(4), 
						rst.getString(5), rst.getString(6), rst.getInt(7));

				HospedesCadastradas.add(hospedesTable);
			}
		}
	}

}
