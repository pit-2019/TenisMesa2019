package com.cibertec.dao;

import java.util.List;

import com.cibertec.bean.TagBean;

public interface TagDao {
	public List<TagBean> getAll();
	public TagBean getTagById(TagBean bean);
	public boolean insertarTag(TagBean bean);
	public boolean updateTag(TagBean bean);
	public boolean deleteTag(TagBean bean);
	public boolean validRepiteRegister(TagBean bean);
}
