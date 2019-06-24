package com.cibertec.bean;

public class OpcionBean {
	private Integer id;
	private String descripcion;
	private String path;
	private Integer orden;
	private Integer idPadre;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	@Override
	public String toString() {
		return "OpcionBean [id=" + id + ", descripcion=" + descripcion + ", path=" + path + ", orden=" + orden
				+ ", idPadre=" + idPadre + "]";
	}

	
}
