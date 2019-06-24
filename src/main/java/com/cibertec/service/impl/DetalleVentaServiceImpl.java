package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.DetalleVentaBean;
import com.cibertec.dao.DetalleVentaDAO;
import com.cibertec.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService{

	@Autowired
	DetalleVentaDAO detalleVentaDao;
	
	@Override
	public List<DetalleVentaBean> getAll() {
		return detalleVentaDao.getAll();
	}

	@Override
	public boolean insertarDetalleVenta(DetalleVentaBean bean) {
		return detalleVentaDao.insertarDetalleVenta(bean);
	}

	@Override
	public boolean updateDetalleVenta(DetalleVentaBean bean) {
		return detalleVentaDao.updateDetalleVenta(bean);
	}

	@Override
	public boolean deleteDetalleVenta(DetalleVentaBean bean) {
		return detalleVentaDao.deleteDetalleVenta(bean);
	}

}
