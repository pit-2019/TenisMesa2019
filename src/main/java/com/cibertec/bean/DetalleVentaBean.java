package com.cibertec.bean;

import java.math.BigDecimal;

public class DetalleVentaBean {
	private Integer id;
	private Integer idProducto;
	private Integer idVenta;
	private Integer cantidad;
	private BigDecimal precio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
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
	@Override
	public String toString() {
		return "DetalleVentaBean [id=" + id + ", idProducto=" + idProducto + ", idVenta=" + idVenta + ", cantidad="
				+ cantidad + ", precio=" + precio + "]";
	}

	
}
