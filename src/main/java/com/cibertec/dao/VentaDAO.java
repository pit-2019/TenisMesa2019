package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.VentaBean;

public interface VentaDAO {
	public List<VentaBean> getAll();
	public boolean insertarVenta(VentaBean bean);
	public boolean updateVenta(VentaBean bean);
	public boolean deleteVenta(VentaBean bean);
	public List<CompraBean> getVentaByUsuario(CompraBean bean);
}
