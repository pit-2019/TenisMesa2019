package com.cibertec.bean;

public class UsuarioBean {
	private Integer id;
	private String username;
	private String password;
	private Integer idRol;
	private String descripcionRol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	@Override
	public String toString() {
		return "UsuarioBean [id=" + id + ", username=" + username + ", password=" + password + ", idRol=" + idRol
				+ ", descripcionRol=" + descripcionRol + "]";
	}




}
