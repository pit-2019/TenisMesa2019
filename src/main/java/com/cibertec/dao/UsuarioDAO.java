package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.RolBean;
import com.cibertec.bean.UsuarioBean;

public interface UsuarioDAO {
	public boolean insertarUsuario(UsuarioBean bean);
	public List<UsuarioBean> getAll();
	public UsuarioBean getUsuarioById(UsuarioBean bean);
	public boolean updateUsuario(UsuarioBean bean);
	public boolean deleteUsuario(UsuarioBean bean);
	public List<RolBean> getAllRol();
	public UsuarioBean getUsuarioByUsername(UsuarioBean bean);
}
