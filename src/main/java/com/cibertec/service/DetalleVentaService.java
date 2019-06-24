package com.cibertec.service;

import java.util.List;

import com.cibertec.bean.DetalleVentaBean;

public interface DetalleVentaService {
	public List<DetalleVentaBean> getAll();
	public boolean insertarDetalleVenta(DetalleVentaBean bean);
	public boolean updateDetalleVenta(DetalleVentaBean bean);
	public boolean deleteDetalleVenta(DetalleVentaBean bean);
}
