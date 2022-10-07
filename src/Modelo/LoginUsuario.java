package Modelo;



public class LoginUsuario {

	private Integer id;
	private String usuario;
	private String senha;

	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public LoginUsuario(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
