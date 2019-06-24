package com.cibertec.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.cibertec.bean.TagBean;
import com.cibertec.dao.TagDao;
import com.cibertec.dao.mapper.TagMapper;

@Repository
public class TagDaoImpl implements TagDao{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	Logger LOGGER = LoggerFactory.getLogger(TagDaoImpl.class);
	
	@Override
	public List<TagBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<TagBean> lista = null;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			lista = tagMapper.getAll();
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return lista;
	}

	@Override
	public TagBean getTagById(TagBean bean) {
		SqlSession session = sessionFactory.openSession();
		TagBean obj = null;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			obj = tagMapper.getTagById(bean);
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return obj;
	}

	@Override
	public boolean insertarTag(TagBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			tagMapper.insertarTag(bean);		
			txManager.commit(txStatus);	
			session.flushStatements();
			session.close();
			success = true;
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return success;
	}

	@Override
	public boolean updateTag(TagBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			tagMapper.updateTag(bean);		
			txManager.commit(txStatus);	
			session.flushStatements();
			session.close();
			success = true;
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return success;
	}

	@Override
	public boolean deleteTag(TagBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			tagMapper.deleteTag(bean);		
			txManager.commit(txStatus);	
			session.flushStatements();
			session.close();
			success = true;
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return success;
	}

	@Override
	public boolean validRepiteRegister(TagBean bean) {
		SqlSession session = sessionFactory.openSession();
		boolean result = false;
		try {
			TagMapper tagMapper = session.getMapper(TagMapper.class);
			TagBean obj = tagMapper.validRepiteRegister(bean);
			if(obj!=null) {
				result = true;
			}
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return result;
	}

}
