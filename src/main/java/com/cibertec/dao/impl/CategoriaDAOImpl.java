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

import com.cibertec.bean.CategoriaBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.TagBean;
import com.cibertec.dao.CategoriaDAO;
import com.cibertec.dao.mapper.CategoriaMapper;
import com.cibertec.dao.mapper.ProductoMapper;
import com.cibertec.dao.mapper.TagMapper;

@Repository
public class CategoriaDAOImpl  implements CategoriaDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(CategoriaDAOImpl.class);
	
	@Override
	public List<CategoriaBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<CategoriaBean> lista = null;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			lista = categoriaMapper.getAll();
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
	public CategoriaBean getCategoriaById(CategoriaBean bean) {
		SqlSession session = sessionFactory.openSession();
		CategoriaBean obj = null;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			obj = categoriaMapper.getCategoriaById(bean);
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
	public boolean insertarCategoria(CategoriaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			categoriaMapper.insertarCategoria(bean);		
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
	public boolean updateCategoria(CategoriaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			categoriaMapper.updateCategoria(bean);		
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
	public boolean deleteCategoria(CategoriaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			categoriaMapper.deleteCategoria(bean);		
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
	public boolean validRepiteRegister(CategoriaBean bean) {
		SqlSession session = sessionFactory.openSession();
		boolean result = false;
		try {
			CategoriaMapper categoriaMapper = session.getMapper(CategoriaMapper.class);
			CategoriaBean obj = categoriaMapper.validRepiteRegister(bean);
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
