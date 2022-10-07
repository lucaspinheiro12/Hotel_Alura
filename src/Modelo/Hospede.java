package Modelo;


public class Hospede {
	
	private Integer id ;
	private String Nome ;
	private String Sobrenome ;
	private String DataNacimento ;
	private String Nacionalidade ;
	private String Telefone;
	private Integer idReserva;
	
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public String getSobrenome() {
		return Sobrenome;
	}
	
	public String getDataNacimento() {
		return DataNacimento;
	}
	
	public String getNacionalidade() {
		return Nacionalidade;
	}
	
	public String getTelefone() {
		return Telefone;
	}
	
	public Integer getIdReserva() {
		return idReserva;
	}
	
	public Hospede(String nome, String sobrenome, String dataNacimento, String nacionalidade, String telefone, Integer idreserva) {
		super();
		Nome = nome;
		Sobrenome = sobrenome;
		DataNacimento = dataNacimento;
		Nacionalidade = nacionalidade;
		Telefone = telefone;
		idReserva = idreserva;
	}

	public Hospede(Integer id, String nome, String sobrenome, String dataNacimento, String nacionalidade, String telefone,
			Integer idreserva) {
		this.id = id;
		this.Nome = nome;
		this.Sobrenome = sobrenome;
		DataNacimento = dataNacimento;
		Nacionalidade = nacionalidade;
		Telefone = telefone;
		idReserva = idreserva;
	}
		
}
