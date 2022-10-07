package Modelo;

import java.sql.Date;

public class Reservas {
	
	private Integer id;
	private String DataEntrada;
	private String DataSaida;
	private String Valor;
	private String FormaPagamento;
	
	
	public Reservas(String dataEntrada, String dataSaida, String valor, String formaPagamento) {
		super();
		DataEntrada = dataEntrada;
		DataSaida = dataSaida;
		Valor = valor;
		FormaPagamento = formaPagamento;
		// essa serve para pegar os dados
	}
	

	public Reservas(Integer id, String dataEntrada, String dataSaida, String valor, String formaPagamento) {
		this.id = id;
		DataEntrada = dataEntrada;
		DataSaida = dataSaida;
		Valor = valor;
		FormaPagamento = formaPagamento;
	}



	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDataEntrada() {
		return DataEntrada;
	}
	
	public String getDataSaida() {
		return DataSaida;
	}
	
	public String getValor() {
		return Valor;
	}
	
	public String getFormaPagamento() {
		return FormaPagamento;
	}
	

	
}
