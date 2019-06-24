package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.ProductoBean;

public interface ProductoDAO {
	public List<ProductoBean> getAll();
	public List<ProductoBean> getAllbyCategoria(ProductoBean bean);
	public boolean insertarProducto(ProductoBean bean);
	public boolean updateProducto(ProductoBean bean);
	public boolean deleteProducto(ProductoBean bean);
	public ProductoBean getProductoById(ProductoBean bean);
	public List<ProductoBean> getAllbyDescripcion(ProductoBean bean);
	public boolean validRepiteRegister(ProductoBean bean);
}
