package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.dao.OpcionDAO;
import com.cibertec.service.OpcionService;

@Service
public class OpcionServiceImpl implements OpcionService{

	@Autowired
	OpcionDAO opcionDao;
	
	@Override
	public List<OpcionBean> getAllByUsername(UsuarioBean bean) {
		return opcionDao.getAllByUsername(bean);
	}

}
