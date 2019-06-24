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

import com.cibertec.bean.NoticiaBean;
import com.cibertec.bean.TagBean;
import com.cibertec.dao.NoticiaDAO;
import com.cibertec.dao.mapper.NoticiaMapper;
import com.cibertec.dao.mapper.TagMapper;

@Repository
public class NoticiaDAOImpl implements NoticiaDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(NoticiaDAOImpl.class);
	
	@Override
	public List<NoticiaBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<NoticiaBean> lista = null;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			lista = noticiaMapper.getAll();
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
	public List<NoticiaBean> getAllbyTag(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<NoticiaBean> lista = null;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			lista = noticiaMapper.getAllbyTag(bean);
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
	public boolean insertarNoticia(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			noticiaMapper.insertarNoticia(bean);		
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
	public boolean updateNoticia(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			noticiaMapper.updateNoticia(bean);		
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
	public boolean deleteNoticia(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			noticiaMapper.deleteComentarioNoticia(bean);
			noticiaMapper.deleteNoticia(bean);	
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
	public NoticiaBean getNoticiaById(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		NoticiaBean obj = null;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			obj = noticiaMapper.getNoticiaById(bean);
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
	public List<NoticiaBean> getNoticiaByDescripcion(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<NoticiaBean> lista = null;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			lista = noticiaMapper.getNoticiaByDescripcion(bean);
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
	public boolean validRepiteRegister(NoticiaBean bean) {
		SqlSession session = sessionFactory.openSession();
		boolean result = false;
		try {
			NoticiaMapper noticiaMapper = session.getMapper(NoticiaMapper.class);
			NoticiaBean obj = noticiaMapper.validRepiteRegister(bean);
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
