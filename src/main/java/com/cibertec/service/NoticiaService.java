package com.cibertec.service;

import java.util.List;

import com.cibertec.bean.NoticiaBean;

public interface NoticiaService {
	public List<NoticiaBean> getAll();
	public List<NoticiaBean> getAllbyTag(NoticiaBean bean);
	public boolean insertarNoticia(NoticiaBean bean);
	public boolean updateNoticia(NoticiaBean bean);
	public boolean deleteNoticia(NoticiaBean bean);
	public NoticiaBean getNoticiaById(NoticiaBean bean);
	public List<NoticiaBean> getNoticiaByDescripcion(NoticiaBean bean);
	public boolean validRepiteRegister(NoticiaBean bean);
}
