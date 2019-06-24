package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.RolBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.dao.UsuarioDAO;
import com.cibertec.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	UsuarioDAO usuarioDao;
	
	@Override
	public boolean insertarUsuario(UsuarioBean bean) {
		return usuarioDao.insertarUsuario(bean);
	}

	@Override
	public List<UsuarioBean> getAll() {
		return usuarioDao.getAll();
	}

	@Override
	public UsuarioBean getUsuarioById(UsuarioBean bean) {
		return usuarioDao.getUsuarioById(bean);
	}

	@Override
	public boolean updateUsuario(UsuarioBean bean) {
		return usuarioDao.updateUsuario(bean);
	}

	@Override
	public boolean deleteUsuario(UsuarioBean bean) {
		return usuarioDao.deleteUsuario(bean);
	}

	@Override
	public List<RolBean> getAllRol() {
		return usuarioDao.getAllRol();
	}

	@Override
	public UsuarioBean getUsuarioByUsername(UsuarioBean bean) {
		return usuarioDao.getUsuarioByUsername(bean);
	}

}
