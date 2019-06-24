package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.VentaBean;
import com.cibertec.dao.VentaDAO;
import com.cibertec.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{

	@Autowired
	VentaDAO ventaDao;
	
	@Override
	public List<VentaBean> getAll() {
		return ventaDao.getAll();
	}

	@Override
	public boolean insertarVenta(VentaBean bean) {
		return ventaDao.insertarVenta(bean);
	}

	@Override
	public boolean updateVenta(VentaBean bean) {
		return ventaDao.updateVenta(bean);
	}

	@Override
	public boolean deleteVenta(VentaBean bean) {
		return ventaDao.deleteVenta(bean);
	}

	@Override
	public List<CompraBean> getVentaByUsuario(CompraBean bean) {
		return ventaDao.getVentaByUsuario(bean);
	}

}
