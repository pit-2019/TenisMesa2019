package com.cibertec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.bean.TagBean;
import com.cibertec.dao.TagDao;
import com.cibertec.service.TagService;

@Service
public class TagServiceImpl implements TagService{

	@Autowired
	TagDao tagDao;
	
	@Override
	public List<TagBean> getAll() {
		return tagDao.getAll();
	}

	@Override
	public TagBean getTagById(TagBean bean) {
		return tagDao.getTagById(bean);
	}

	@Override
	public boolean insertarTag(TagBean bean) {
		return tagDao.insertarTag(bean);
	}

	@Override
	public boolean updateTag(TagBean bean) {
		return tagDao.updateTag(bean);
	}

	@Override
	public boolean deleteTag(TagBean bean) {
		return tagDao.deleteTag(bean);
	}

	@Override
	public boolean validRepiteRegister(TagBean bean) {
		return tagDao.validRepiteRegister(bean);
	}

}
