package com.cibertec.bean;

import java.sql.Blob;

public class GaleriaFotosBean {
	private Integer id;
	private Blob foto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Blob getFoto() {
		return foto;
	}
	public void setFoto(Blob foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "GaleriaFotosBean [id=" + id + ", foto=" + foto + "]";
	}
	
}
