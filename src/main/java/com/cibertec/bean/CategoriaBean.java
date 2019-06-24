package com.cibertec.bean;

public class CategoriaBean {
	private Integer id;
	private String descripcion;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "CategoriaBean [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}
