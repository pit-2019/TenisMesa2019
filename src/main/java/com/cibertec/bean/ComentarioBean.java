package com.cibertec.bean;

import java.util.Date;

public class ComentarioBean {
	private Integer id;
	private String nombre;
	private String email;
	private String texto;
	private Integer idNoticia;
	private Date fechaRegistro;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Integer getIdNoticia() {
		return idNoticia;
	}
	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	@Override
	public String toString() {
		return "ComentarioBean [id=" + id + ", nombre=" + nombre + ", email=" + email + ", texto=" + texto
				+ ", idNoticia=" + idNoticia + ", fechaRegistro=" + fechaRegistro + "]";
	}

}
