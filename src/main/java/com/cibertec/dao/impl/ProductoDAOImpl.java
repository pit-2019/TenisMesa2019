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

import com.cibertec.bean.CompraBean;
import com.cibertec.bean.DetalleVentaBean;
import com.cibertec.bean.ProductoBean;
import com.cibertec.bean.TagBean;
import com.cibertec.bean.VentaBean;
import com.cibertec.dao.ProductoDAO;
import com.cibertec.dao.mapper.ProductoMapper;
import com.cibertec.dao.mapper.TagMapper;

@Repository
public class ProductoDAOImpl implements ProductoDAO{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(ProductoDAOImpl.class);
	
	@Override
	public List<ProductoBean> getAll() {
		SqlSession session = sessionFactory.openSession();
		List<ProductoBean> lista = null;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			lista = productoMapper.getAll();
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
	public List<ProductoBean> getAllbyCategoria(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<ProductoBean> lista = null;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			lista = productoMapper.getAllbyCategoria(bean);
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
	public boolean insertarProducto(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			productoMapper.insertarProducto(bean);		
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
	public boolean updateProducto(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			productoMapper.updateProducto(bean);		
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
	public boolean deleteProducto(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		TransactionDefinition txDef = new DefaultTransactionDefinition();
		TransactionStatus txStatus = txManager.getTransaction(txDef);
		boolean success = false;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			List<VentaBean> listaVenta = productoMapper.getVentaProducto(bean);
			
			for (VentaBean ventaBean : listaVenta) {
				List<DetalleVentaBean> listaDetalle = productoMapper.getVentaDetalleProducto(ventaBean);
				for (DetalleVentaBean detalleVentaBean : listaDetalle) {
					LOGGER.info(bean.toString());
					LOGGER.info("DEL DETALLE VENTA PRODUCTO");
					productoMapper.deleteDetalleVentaProducto(detalleVentaBean);				
				}			
			}
			
			LOGGER.info(bean.toString());
			LOGGER.info("DEL PRODUCTO");
			productoMapper.deleteProducto(bean);	
			
			for (VentaBean ventaBean : listaVenta) {
				LOGGER.info(ventaBean.toString());
				LOGGER.info("DEL VENTA");
				productoMapper.deleteVentaProducto(ventaBean);
			}
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
	public ProductoBean getProductoById(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		ProductoBean obj = null;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			obj = productoMapper.getProductoById(bean);
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
	public List<ProductoBean> getAllbyDescripcion(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<ProductoBean> lista = null;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			lista = productoMapper.getAllbyDescripcion(bean);
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
	public boolean validRepiteRegister(ProductoBean bean) {
		SqlSession session = sessionFactory.openSession();
		boolean result = false;
		try {
			ProductoMapper productoMapper = session.getMapper(ProductoMapper.class);
			ProductoBean obj = productoMapper.validRepiteRegister(bean);
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
