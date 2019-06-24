package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.ComentarioBean;

public interface ComentarioDAO {
	public List<ComentarioBean> getAll();
	public List<ComentarioBean> getAllbyNoticia(ComentarioBean bean);
	public ComentarioBean getComentarioById(ComentarioBean bean);
	public boolean insertarComentario(ComentarioBean bean);
	public boolean updateComentario(ComentarioBean bean);
	public boolean deleteComentario(ComentarioBean bean);
}
