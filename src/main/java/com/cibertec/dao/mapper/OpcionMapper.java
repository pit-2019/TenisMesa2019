package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cibertec.bean.OpcionBean;
import com.cibertec.bean.UsuarioBean;

@Mapper
public interface OpcionMapper {
	@Select("select o.id,o.descripcion,o.path,o.orden,o.id_padre as idPadre from rol_opcion ro " + 
			"inner join rol r " + 
			"on r.id = ro.id_rol " + 
			"inner join opcion o " + 
			"on o.id = ro.id_opcion " + 
			"inner join usuario u " + 
			"on u.id_rol = r.id " + 
			"where u.username=#{username} and password=#{password} ")
	public List<OpcionBean> getAllByUsername(UsuarioBean bean);
}
