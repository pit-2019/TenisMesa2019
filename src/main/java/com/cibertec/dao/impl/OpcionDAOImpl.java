package com.cibertec.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.UsuarioBean;
import com.cibertec.dao.OpcionDAO;
import com.cibertec.dao.mapper.OpcionMapper;

@Repository
public class OpcionDAOImpl implements OpcionDAO{
	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	private PlatformTransactionManager txManager;
	
	private Logger LOGGER = LoggerFactory.getLogger(OpcionDAO.class);
	
	@Override
	public List<OpcionBean> getAllByUsername(UsuarioBean bean) {
		SqlSession session = sessionFactory.openSession();
		List<OpcionBean> lista = null;
		try {
			OpcionMapper opcionMapper = session.getMapper(OpcionMapper.class);
			lista = opcionMapper.getAllByUsername(bean);
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
