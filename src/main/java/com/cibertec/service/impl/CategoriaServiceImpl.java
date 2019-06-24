package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.CategoriaBean;
import com.cibertec.dao.CategoriaDAO;
import com.cibertec.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	CategoriaDAO categoriaDao;
	
	@Override
	public List<CategoriaBean> getAll() {
		return categoriaDao.getAll();
	}

	@Override
	public CategoriaBean getCategoriaById(CategoriaBean bean) {
		return categoriaDao.getCategoriaById(bean);
	}

	@Override
	public boolean insertarCategoria(CategoriaBean bean) {
		return categoriaDao.insertarCategoria(bean);
	}

	@Override
	public boolean updateCategoria(CategoriaBean bean) {
		return categoriaDao.updateCategoria(bean);
	}

	@Override
	public boolean deleteCategoria(CategoriaBean bean) {
		return categoriaDao.deleteCategoria(bean);
	}

	@Override
	public boolean validRepiteRegister(CategoriaBean bean) {
		return categoriaDao.validRepiteRegister(bean);
	}

}
