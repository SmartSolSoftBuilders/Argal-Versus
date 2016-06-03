package mx.argal.modelo;

import java.util.Date;

public class Usuario extends BaseModelo {
	
	private Integer idUsuario;
	private String nickUsuario;
	private String passwordUsuario;
	

    public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNickUsuario() {
		return nickUsuario;
	}

	public void setNickUsuario(String nickUsuario) {
		this.nickUsuario = nickUsuario;
	}

	public String getPasswordUsuario() {
		return passwordUsuario;
	}

	public void setPasswordUsuario(String passwordUsuario) {
		this.passwordUsuario = passwordUsuario;
	}

	public Usuario(Integer idUsuario, String nickUsuario, String passwordUsuario) {
    	this.idUsuario=idUsuario;
    	this.nickUsuario=nickUsuario;
    	this.passwordUsuario=passwordUsuario;    	
    }

    public Usuario() {
    }


}
