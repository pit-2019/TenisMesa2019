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

import com.cibertec.bean.ComentarioBean;
import com.cibertec.bean.NoticiaBean;
import com.cibertec.dao.ComentarioDAO;
import com.cibertec.dao.mapper.ComentarioMapper;
import com.cibertec.dao.mapper.NoticiaMapper;

@Repository
public class ComentarioDAOImpl implements ComentarioDAO{
	
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(ComentarioDAOImpl.class);
	
	@Override
	public List<ComentarioBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<ComentarioBean> lista = null;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			lista = comentarioMapper.getAll();
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
	public List<ComentarioBean> getAllbyNoticia(ComentarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<ComentarioBean> lista = null;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			lista = comentarioMapper.getAllbyNoticia(bean);
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
	public ComentarioBean getComentarioById(ComentarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		ComentarioBean obj = null;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			obj = comentarioMapper.getComentarioById(bean);
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
	public boolean insertarComentario(ComentarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			comentarioMapper.insertarComentario(bean);		
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
	public boolean updateComentario(ComentarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			comentarioMapper.updateComentario(bean);		
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
	public boolean deleteComentario(ComentarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ComentarioMapper comentarioMapper = session.getMapper(ComentarioMapper.class);
			comentarioMapper.deleteComentario(bean);		
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

}
