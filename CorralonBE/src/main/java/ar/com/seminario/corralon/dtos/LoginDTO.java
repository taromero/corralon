package ar.com.seminario.corralon.dtos;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {
	private Long alias;
	private String password;
	private Boolean validado;
	
	
	public Long getAlias() {
		return alias;
	}
	public void setAlias(Long alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getValidado() {
		return validado;
	}
	public void setValidado(Boolean validado) {
		this.validado = validado;
	}
}
