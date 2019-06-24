package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.NoticiaBean;
import com.cibertec.dao.NoticiaDAO;
import com.cibertec.service.NoticiaService;

@Service
public class NoticiaServiceImpl implements NoticiaService{

	@Autowired
	NoticiaDAO noticiaDao;
	
	@Override
	public List<NoticiaBean> getAll() {
		return noticiaDao.getAll();
	}

	@Override
	public List<NoticiaBean> getAllbyTag(NoticiaBean bean) {
		return noticiaDao.getAllbyTag(bean);
	}

	@Override
	public boolean insertarNoticia(NoticiaBean bean) {
		return noticiaDao.insertarNoticia(bean);
	}

	@Override
	public boolean updateNoticia(NoticiaBean bean) {
		return noticiaDao.updateNoticia(bean);
	}

	@Override
	public boolean deleteNoticia(NoticiaBean bean) {
		return noticiaDao.deleteNoticia(bean);
	}

	@Override
	public NoticiaBean getNoticiaById(NoticiaBean bean) {
		return noticiaDao.getNoticiaById(bean);
	}

	@Override
	public List<NoticiaBean> getNoticiaByDescripcion(NoticiaBean bean) {
		return noticiaDao.getNoticiaByDescripcion(bean);
	}

	@Override
	public boolean validRepiteRegister(NoticiaBean bean) {
		return noticiaDao.validRepiteRegister(bean);
	}

}
