package com.cibertec.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CompraBean {
	private Integer id;
	private Date fechaCompra;
	private String descripcionCategoria;
	private String descripcionProducto;
	private Integer cantidad;
	private BigDecimal precio;
	private String username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}
	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "CompraBean [id=" + id + ", fechaCompra=" + fechaCompra + ", descripcionCategoria="
				+ descripcionCategoria + ", descripcionProducto=" + descripcionProducto + ", cantidad=" + cantidad
				+ ", precio=" + precio + ", username=" + username + "]";
	}

	
}
