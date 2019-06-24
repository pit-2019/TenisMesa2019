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

import com.cibertec.bean.DetalleVentaBean;
import com.cibertec.dao.DetalleVentaDAO;
import com.cibertec.dao.mapper.DetalleVentaMapper;

@Repository
public class DetalleVentaDAOImpl implements DetalleVentaDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(DetalleVentaDAOImpl.class);
	
	@Override
	public List<DetalleVentaBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<DetalleVentaBean> lista = null;
		try {
			DetalleVentaMapper detalleVentaMapper = session.getMapper(DetalleVentaMapper.class);
			lista = detalleVentaMapper.getAll();
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
	public boolean insertarDetalleVenta(DetalleVentaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			DetalleVentaMapper detalleVentaMapper = session.getMapper(DetalleVentaMapper.class);
			detalleVentaMapper.insertarDetalleVenta(bean);		
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
	public boolean updateDetalleVenta(DetalleVentaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			DetalleVentaMapper detalleVentaMapper = session.getMapper(DetalleVentaMapper.class);
			detalleVentaMapper.updateDetalleVenta(bean);		
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
	public boolean deleteDetalleVenta(DetalleVentaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			DetalleVentaMapper detalleVentaMapper = session.getMapper(DetalleVentaMapper.class);
			detalleVentaMapper.deleteDetalleVenta(bean);		
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
