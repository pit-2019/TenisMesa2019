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

import com.cibertec.bean.PerfilBean;
import com.cibertec.bean.TagBean;
import com.cibertec.dao.PerfilDAO;
import com.cibertec.dao.mapper.PerfilMapper;
import com.cibertec.dao.mapper.TagMapper;

@Repository
public class PerfilDAOImpl implements PerfilDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(PerfilDAOImpl.class);
	
	@Override
	public List<PerfilBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<PerfilBean> lista = null;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			lista = perfilMapper.getAll();
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
	public List<PerfilBean> getAllbyTag(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<PerfilBean> lista = null;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			lista = perfilMapper.getAllbyDescripcion(bean);
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
	public boolean insertarPerfil(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			perfilMapper.insertarPerfil(bean);		
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
	public boolean updatePerfil(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			perfilMapper.updatePerfil(bean);		
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
	public boolean deletePerfil(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			perfilMapper.deletePerfil(bean);		
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
	public PerfilBean getPerfilById(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			bean = perfilMapper.getPerfilById(bean);
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return bean;
	}

	@Override
	public boolean validRepiteRegister(PerfilBean bean) {
		SqlSession session = sessionFactory.openSession();
		boolean result = false;
		try {
			PerfilMapper perfilMapper = session.getMapper(PerfilMapper.class);
			PerfilBean obj = perfilMapper.validRepiteRegister(bean);
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
