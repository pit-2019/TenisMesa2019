package com.cibertec.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.cibertec.bean.RolBean;
import com.cibertec.bean.UsuarioBean;


@Mapper
public interface UsuarioMapper {
	
	@Insert("INSERT into usuario(username,password,id_rol) "
			+ "VALUES(#{username}, #{password}, #{idRol})")
	@SelectKey(statement="select last_insert_id() as id",keyProperty="id", resultType=Integer.class, before= false)
	public boolean insertarUsuario(UsuarioBean bean);
	
	@Select("SELECT u.id, u.username, r.descripcion as descripcionRol  from usuario u inner join rol r on u.id_rol = r.id where u.id_rol not in('1')")
	public List<UsuarioBean> getAll();
	
	@Select("SELECT id, descripcion from rol where id not in('1')")
	public List<RolBean> getAllRol();
	
	@Select("SELECT id, username, password, id_rol as idRol from usuario where id=#{id}")
	public UsuarioBean getUsuarioById(UsuarioBean bean);
	
	@Select("SELECT id, username, password, id_rol as idRol from usuario where username=#{username}")
	public UsuarioBean getUsuarioByUsername(UsuarioBean bean);
	
	@Update("UPDATE usuario SET username=#{username}, password=#{password}, id_rol=#{idRol} WHERE id=#{id}")
	public boolean updateUsuario(UsuarioBean bean);
	
	@Delete("DELETE FROM usuario where id=#{id}")
	public boolean deleteUsuario(UsuarioBean bean);
}
