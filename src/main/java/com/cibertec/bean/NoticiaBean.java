package com.cibertec.bean;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticiaBean {
	private Integer id;
	private Integer idTag;
	private String titulo;
	private String descripcion;
	private Date fechaPublicacion;
	private String comentarioHabilitado;
	private byte[] imagePreview;
	private String descripcionTag;
	private MultipartFile archivo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdTag() {
		return idTag;
	}
	public void setIdTag(Integer idTag) {
		this.idTag = idTag;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public String getComentarioHabilitado() {
		return comentarioHabilitado;
	}
	public void setComentarioHabilitado(String comentarioHabilitado) {
		this.comentarioHabilitado = comentarioHabilitado;
	}
	public byte[] getImagePreview() {
		return imagePreview;
	}
	public void setImagePreview(byte[] imagePreview) {
		this.imagePreview = imagePreview;
	}
	public String getDescripcionTag() {
		return descripcionTag;
	}
	public void setDescripcionTag(String descripcionTag) {
		this.descripcionTag = descripcionTag;
	}
	
	public MultipartFile getArchivo() {
		return archivo;
	}
	public void setArchivo(MultipartFile archivo) {
		this.archivo = archivo;
	}
	@Override
	public String toString() {
		return "NoticiaBean [id=" + id + ", idTag=" + idTag + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", fechaPublicacion=" + fechaPublicacion + ", comentarioHabilitado=" + comentarioHabilitado
				+ ", imagePreview=" + Arrays.toString(imagePreview) + ", descripcionTag=" + descripcionTag
				+ ", archivo=" + archivo + "]";
	}



}
