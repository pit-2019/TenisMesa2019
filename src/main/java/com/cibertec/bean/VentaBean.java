package com.cibertec.bean;

import java.math.BigDecimal;
import java.util.Date;

public class VentaBean {
	private Integer id;
	private BigDecimal montoTotal;
	private Date fechaRegistro;
	private String usuarioRegistro;
	private String detalle;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	@Override
	public String toString() {
		return "VentaBean [id=" + id + ", montoTotal=" + montoTotal + ", fechaRegistro=" + fechaRegistro
				+ ", usuarioRegistro=" + usuarioRegistro + ", detalle=" + detalle + "]";
	}

	
	
}
