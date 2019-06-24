package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.ProductoBean;
import com.cibertec.dao.ProductoDAO;
import com.cibertec.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
	ProductoDAO productoDao;
	
	@Override
	public List<ProductoBean> getAll() {
		return productoDao.getAll();
	}

	@Override
	public List<ProductoBean> getAllbyCategoria(ProductoBean bean) {
		return productoDao.getAllbyCategoria(bean);
	}

	@Override
	public boolean insertarProducto(ProductoBean bean) {
		return productoDao.insertarProducto(bean);
	}

	@Override
	public boolean updateProducto(ProductoBean bean) {
		return productoDao.updateProducto(bean);
	}

	@Override
	public boolean deleteProducto(ProductoBean bean) {
		return productoDao.deleteProducto(bean);
	}

	@Override
	public ProductoBean getProductoById(ProductoBean bean) {
		return productoDao.getProductoById(bean);
	}

	@Override
	public List<ProductoBean> getAllbyDescripcion(ProductoBean bean) {
		return productoDao.getAllbyDescripcion(bean);
	}

	@Override
	public boolean validRepiteRegister(ProductoBean bean) {
		return productoDao.validRepiteRegister(bean);
	}

}
