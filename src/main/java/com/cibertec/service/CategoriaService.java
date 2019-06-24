package com.cibertec.service;

import java.util.List;

import com.cibertec.bean.CategoriaBean;

public interface CategoriaService {
	public List<CategoriaBean> getAll();
	public CategoriaBean getCategoriaById(CategoriaBean bean);
	public boolean insertarCategoria(CategoriaBean bean);
	public boolean updateCategoria(CategoriaBean bean);
	public boolean deleteCategoria(CategoriaBean bean);
	public boolean validRepiteRegister(CategoriaBean bean);
}
