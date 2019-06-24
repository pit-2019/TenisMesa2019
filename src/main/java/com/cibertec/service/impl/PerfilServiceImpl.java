package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.PerfilBean;
import com.cibertec.dao.PerfilDAO;
import com.cibertec.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService{

	@Autowired
	PerfilDAO perfilDao;
	
	@Override
	public List<PerfilBean> getAll() {
		return perfilDao.getAll();
	}

	@Override
	public List<PerfilBean> getAllbyTag(PerfilBean bean) {
		return perfilDao.getAllbyTag(bean);
	}

	@Override
	public boolean insertarPerfil(PerfilBean bean) {
		return perfilDao.insertarPerfil(bean);
	}

	@Override
	public boolean updatePerfil(PerfilBean bean) {
		return perfilDao.updatePerfil(bean);
	}

	@Override
	public boolean deletePerfil(PerfilBean bean) {
		return perfilDao.deletePerfil(bean);
	}

	@Override
	public PerfilBean getPerfilById(PerfilBean bean) {
		return perfilDao.getPerfilById(bean);
	}

	@Override
	public boolean validRepiteRegister(PerfilBean bean) {
		return perfilDao.validRepiteRegister(bean);
	}

}
