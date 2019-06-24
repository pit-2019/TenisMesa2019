package com.cibertec.bean;

import java.util.Arrays;
import java.util.Date;

public class PerfilBean {
	private Integer id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	private String pais;
	private String direccion;
	private String rangoJugador;
	private String ranking;
	private String descripcion;
	private byte[] imageView;
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getRangoJugador() {
		return rangoJugador;
	}
	public void setRangoJugador(String rangoJugador) {
		this.rangoJugador = rangoJugador;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	public byte[] getImageView() {
		return imageView;
	}
	public void setImageView(byte[] imageView) {
		this.imageView = imageView;
	}
	@Override
	public String toString() {
		return "PerfilBean [id=" + id + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", pais=" + pais
				+ ", direccion=" + direccion + ", rangoJugador=" + rangoJugador + ", ranking=" + ranking
				+ ", descripcion=" + descripcion + ", imageView=" + Arrays.toString(imageView) + "]";
	}

	
}
