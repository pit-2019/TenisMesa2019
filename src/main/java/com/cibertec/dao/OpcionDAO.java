package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.UsuarioBean;

public interface OpcionDAO {
	public List<OpcionBean> getAllByUsername(UsuarioBean bean);
}
