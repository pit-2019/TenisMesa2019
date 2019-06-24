package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.ComentarioBean;
import com.cibertec.dao.ComentarioDAO;
import com.cibertec.service.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{

	@Autowired
	ComentarioDAO comentarioDao;

	@Override
	public List<ComentarioBean> getAll() {
		return comentarioDao.getAll();
	}

	@Override
	public List<ComentarioBean> getAllbyNoticia(ComentarioBean bean) {
		return comentarioDao.getAllbyNoticia(bean);
	}

	@Override
	public ComentarioBean getComentarioById(ComentarioBean bean) {
		return comentarioDao.getComentarioById(bean);
	}

	@Override
	public boolean insertarComentario(ComentarioBean bean) {
		return comentarioDao.insertarComentario(bean);
	}

	@Override
	public boolean updateComentario(ComentarioBean bean) {
		return comentarioDao.updateComentario(bean);
	}

	@Override
	public boolean deleteComentario(ComentarioBean bean) {
		return comentarioDao.deleteComentario(bean);
	}
	
	
}
