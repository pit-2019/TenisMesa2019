package com.cibertec.service;

import java.util.List;

import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.UsuarioBean;

public interface OpcionService {
	public List<OpcionBean> getAllByUsername(UsuarioBean bean);
}
