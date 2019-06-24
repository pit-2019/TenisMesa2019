package com.cibertec.service;

import java.util.List;

import com.cibertec.bean.PerfilBean;

public interface PerfilService {
	public List<PerfilBean> getAll();
	public List<PerfilBean> getAllbyTag(PerfilBean bean);
	public boolean insertarPerfil(PerfilBean bean);
	public boolean updatePerfil(PerfilBean bean);
	public boolean deletePerfil(PerfilBean bean);
	public PerfilBean getPerfilById(PerfilBean bean);
	public boolean validRepiteRegister(PerfilBean bean);
}
