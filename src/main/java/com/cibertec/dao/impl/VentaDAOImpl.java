package com.cibertec.dao.impl;

import java.util.Arrays;
import java.util.Date;
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

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.DetalleVentaBean;
import com.cibertec.bean.VentaBean;
import com.cibertec.dao.VentaDAO;
import com.cibertec.dao.mapper.DetalleVentaMapper;
import com.cibertec.dao.mapper.VentaMapper;
import com.google.gson.Gson;

@Repository
public class VentaDAOImpl implements VentaDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(VentaDAOImpl.class);
	
	@Override
	public List<VentaBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<VentaBean> lista = null;
		try {
			VentaMapper ventaMapper = session.getMapper(VentaMapper.class);
			lista = ventaMapper.getAll();
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
	public boolean insertarVenta(VentaBean bean) {
		boolean paramRetun=false;
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		try {
			VentaMapper ventaMapper = session.getMapper(VentaMapper.class);
			DetalleVentaMapper detalleVentaMapper = session.getMapper(DetalleVentaMapper.class);
			ventaMapper.insertarVenta(bean);		

			Gson gson = new Gson();
			DetalleVentaBean[] beanList =gson.fromJson(bean.getDetalle(), DetalleVentaBean[].class);
			List<DetalleVentaBean> listDetalle = Arrays.asList(beanList);
			if(bean.getId()!=null) {
				for (DetalleVentaBean detalleVentaBean : listDetalle) {
					detalleVentaBean.setIdVenta(bean.getId());
					detalleVentaMapper.insertarDetalleVenta(detalleVentaBean);
				}	
				paramRetun = true;
			}	

			txManager.commit(txStatus);	
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return paramRetun;
	}

	@Override
	public boolean updateVenta(VentaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			VentaMapper ventaMapper = session.getMapper(VentaMapper.class);
			ventaMapper.updateVenta(bean);		
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
	public boolean deleteVenta(VentaBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			VentaMapper ventaMapper = session.getMapper(VentaMapper.class);
			ventaMapper.deleteVenta(bean);		
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
	public List<CompraBean> getVentaByUsuario(CompraBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<CompraBean> lista = null;
		try {
			VentaMapper ventaMapper = session.getMapper(VentaMapper.class);
			lista = ventaMapper.getVentaByUsuario(bean);
			session.flushStatements();
			session.close();
		} catch (Exception e) {
			session.flushStatements();
			session.close();
			LOGGER.info(e.getMessage()+": "+e.getCause());
		}
		return lista;
	}

}
