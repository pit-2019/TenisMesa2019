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

import com.cibertec.bean.RolBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.dao.UsuarioDAO;
import com.cibertec.dao.mapper.UsuarioMapper;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	@Override
	public boolean insertarUsuario(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			usuarioMapper.insertarUsuario(bean);		
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
	public List<UsuarioBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<UsuarioBean> lista = null;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			lista = usuarioMapper.getAll();
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
	public UsuarioBean getUsuarioById(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		UsuarioBean obj = null;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			obj = usuarioMapper.getUsuarioById(bean);
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
	public boolean updateUsuario(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			usuarioMapper.updateUsuario(bean);		
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
	public boolean deleteUsuario(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			usuarioMapper.deleteUsuario(bean);		
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
	public List<RolBean> getAllRol() {
		SqlSession session = sessionFactory.openSession();
		List<RolBean> lista = null;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			lista = usuarioMapper.getAllRol();
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
	public UsuarioBean getUsuarioByUsername(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		UsuarioBean obj = null;
		try {
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);
			obj = usuarioMapper.getUsuarioByUsername(bean);
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return obj;
	}

}
