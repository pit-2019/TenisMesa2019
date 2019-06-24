package com.cibertec.bean;

import java.util.List;

public class ResponseListBean<T> {
	private String  estadoRespuesta;
	private String mensajeRespueta;
	private Integer page;
	private Integer total;
	private Integer records;
	private List<T> rows;
	
	public String getEstadoRespuesta() {
		return estadoRespuesta;
	}
	public void setEstadoRespuesta(String estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public String getMensajeRespueta() {
		return mensajeRespueta;
	}
	public void setMensajeRespueta(String mensajeRespueta) {
		this.mensajeRespueta = mensajeRespueta;
	}
	
}
