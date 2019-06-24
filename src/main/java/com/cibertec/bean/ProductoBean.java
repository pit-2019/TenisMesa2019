package com.cibertec.bean;

import java.math.BigDecimal;
import java.util.Arrays;

public class ProductoBean {
	private Integer id;
	private String descripcion;
	private BigDecimal precio;
	private Integer idCategoria;
	private byte[] imageView;
	private String destacado;
	private String detalle;
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
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public byte[] getImageView() {
		return imageView;
	}
	public void setImageView(byte[] imageView) {
		this.imageView = imageView;
	}
	public String getDestacado() {
		return destacado;
	}
	public void setDestacado(String destacado) {
		this.destacado = destacado;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "ProductoBean [id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ", idCategoria="
				+ idCategoria + ", imageView=" + Arrays.toString(imageView) + ", destacado=" + destacado + ", detalle="
				+ detalle + "]";
	}

	
}
